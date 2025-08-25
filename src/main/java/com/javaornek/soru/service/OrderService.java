package com.javaornek.soru.service;

import com.javaornek.soru.domain.*;
import com.javaornek.soru.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;

	public OrderService(OrderRepository orderRepository,
						CustomerRepository customerRepository,
						CartRepository cartRepository,
						CartItemRepository cartItemRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
	}

	public List<Order> getAllOrdersForCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow();
		return orderRepository.findByCustomer(customer);
	}

	public Order getOrderForCode(String code) {
		return orderRepository.findByOrderCode(code).orElseThrow();
	}

	@Transactional
	public Order placeOrder(Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow();
		Cart cart = cartRepository.findByCustomer(customer).orElseThrow();
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderCode(UUID.randomUUID().toString());
		order.setTotalPrice(BigDecimal.ZERO);
		Order saved = orderRepository.save(order);
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem ci : cart.getItems()) {
			OrderItem oi = new OrderItem();
			oi.setOrder(saved);
			oi.setCourseListing(ci.getCourseListing());
			oi.setPrice(ci.getPrice());
			saved.getItems().add(oi);
			total = total.add(ci.getPrice());
		}
		saved.setTotalPrice(total);
		Order savedWithItems = orderRepository.save(saved);
		// empty cart
		cart.getItems().forEach(cartItemRepository::delete);
		cart.getItems().clear();
		cart.setTotalPrice(BigDecimal.ZERO);
		cartRepository.save(cart);
		return savedWithItems;
	}
}


