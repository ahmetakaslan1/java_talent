package com.javaornek.soru.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"cart_id", "course_listing_id"})
})
public class CartItem extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_listing_id", nullable = false)
	private CourseListing courseListing;

	@Column(nullable = false)
	private BigDecimal price;

	public Cart getCart() { return cart; }
	public void setCart(Cart cart) { this.cart = cart; }

	public CourseListing getCourseListing() { return courseListing; }
	public void setCourseListing(CourseListing courseListing) { this.courseListing = courseListing; }

	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }
}


