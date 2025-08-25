package com.javaornek.soru.service;

import com.javaornek.soru.domain.Cart;
import com.javaornek.soru.domain.Customer;
import com.javaornek.soru.repository.CartRepository;
import com.javaornek.soru.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final CartRepository cartRepository;

	public CustomerService(CustomerRepository customerRepository, CartRepository cartRepository) {
		this.customerRepository = customerRepository;
		this.cartRepository = cartRepository;
	}

	@Transactional
	public Customer addCustomer(String name, String email) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		Customer saved = customerRepository.save(customer);

		Cart cart = new Cart();
		cart.setCustomer(saved);
		cartRepository.save(cart);
		return saved;
	}
}


