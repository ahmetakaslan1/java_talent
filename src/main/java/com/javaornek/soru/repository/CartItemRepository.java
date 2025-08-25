package com.javaornek.soru.repository;

import com.javaornek.soru.domain.Cart;
import com.javaornek.soru.domain.CartItem;
import com.javaornek.soru.domain.CourseListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	Optional<CartItem> findByCartAndCourseListing(Cart cart, CourseListing listing);
}


