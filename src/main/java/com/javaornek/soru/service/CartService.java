package com.javaornek.soru.service;

import com.javaornek.soru.domain.*;
import com.javaornek.soru.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final CourseListingRepository courseListingRepository;
	private final CustomerRepository customerRepository;

	public CartService(CartRepository cartRepository,
					CartItemRepository cartItemRepository,
					CourseListingRepository courseListingRepository,
					CustomerRepository customerRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.courseListingRepository = courseListingRepository;
		this.customerRepository = customerRepository;
	}

	public Cart getCart(Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow();
		return cartRepository.findByCustomer(customer).orElseThrow();
	}

	@Transactional
	public Cart addCourseToCart(Long customerId, Long listingId) {
		Cart cart = getCart(customerId);
		CourseListing listing = courseListingRepository.findById(listingId).orElseThrow();
		if (!Boolean.TRUE.equals(listing.getAvailable())) {
			throw new IllegalStateException("Listing not available for cart");
		}
		cartItemRepository.findByCartAndCourseListing(cart, listing).ifPresent(ci -> {
			throw new IllegalArgumentException("Course already in cart");
		});
		CartItem item = new CartItem();
		item.setCart(cart);
		item.setCourseListing(listing);
		item.setPrice(listing.getPrice());
		cart.getItems().add(item);
		recalculateTotal(cart);
		cartRepository.save(cart);
		return cart;
	}

	@Transactional
	public Cart removeCourseFromCart(Long customerId, Long listingId) {
		Cart cart = getCart(customerId);
		CourseListing listing = courseListingRepository.findById(listingId).orElseThrow();
		CartItem item = cartItemRepository.findByCartAndCourseListing(cart, listing)
				.orElseThrow();
		cart.getItems().remove(item);
		cartItemRepository.delete(item);
		recalculateTotal(cart);
		return cartRepository.save(cart);
	}

	@Transactional
	public Cart emptyCart(Long customerId) {
		Cart cart = getCart(customerId);
		cart.getItems().clear();
		recalculateTotal(cart);
		return cartRepository.save(cart);
	}

	@Transactional
	public Cart updateCartTotals(Long customerId) {
		Cart cart = getCart(customerId);
		recalculateTotal(cart);
		return cartRepository.save(cart);
	}

	private void recalculateTotal(Cart cart) {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem item : cart.getItems()) {
			total = total.add(item.getPrice());
		}
		cart.setTotalPrice(total);
	}
}


