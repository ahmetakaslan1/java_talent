package com.javaornek.soru.web;

import com.javaornek.soru.domain.Order;
import com.javaornek.soru.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/customer/{customerId}")
	public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
		return orderService.getAllOrdersForCustomer(customerId);
	}

	@GetMapping("/{code}")
	public Order getOrderForCode(@PathVariable String code) {
		return orderService.getOrderForCode(code);
	}

	@PostMapping("/customer/{customerId}/place")
	public Order place(@PathVariable Long customerId) {
		return orderService.placeOrder(customerId);
	}
}


