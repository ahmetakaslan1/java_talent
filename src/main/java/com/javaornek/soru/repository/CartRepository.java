package com.javaornek.soru.repository;

import com.javaornek.soru.domain.Cart;
import com.javaornek.soru.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findByCustomer(Customer customer);
}


