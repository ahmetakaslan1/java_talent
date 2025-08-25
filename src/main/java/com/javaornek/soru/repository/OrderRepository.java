package com.javaornek.soru.repository;

import com.javaornek.soru.domain.Customer;
import com.javaornek.soru.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findByOrderCode(String orderCode);
	List<Order> findByCustomer(Customer customer);
}


