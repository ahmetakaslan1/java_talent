package com.javaornek.soru.web;

import com.javaornek.soru.domain.Cart;
import com.javaornek.soru.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/{customerId}")
	public Cart getCart(@PathVariable Long customerId) {
		return cartService.getCart(customerId);
	}

	@PostMapping("/{customerId}/add")
	public Cart addCourse(@PathVariable Long customerId, @RequestBody Map<String, Long> req) {
		return cartService.addCourseToCart(customerId, req.get("listingId"));
	}

	@PostMapping("/{customerId}/remove")
	public Cart removeCourse(@PathVariable Long customerId, @RequestBody Map<String, Long> req) {
		return cartService.removeCourseFromCart(customerId, req.get("listingId"));
	}

	@PostMapping("/{customerId}/empty")
	public ResponseEntity<Cart> empty(@PathVariable Long customerId) {
		return ResponseEntity.ok(cartService.emptyCart(customerId));
	}

	@PostMapping("/{customerId}/recalculate")
	public Cart recalc(@PathVariable Long customerId) {
		return cartService.updateCartTotals(customerId);
	}
}


