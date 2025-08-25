package com.javaornek.soru.web;

import com.javaornek.soru.domain.Customer;
import com.javaornek.soru.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Map<String, String> req) {
		Customer c = customerService.addCustomer(req.get("name"), req.get("email"));
		return ResponseEntity.ok(c);
	}
}


