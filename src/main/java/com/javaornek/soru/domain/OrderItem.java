package com.javaornek.soru.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_listing_id", nullable = false)
	private CourseListing courseListing;

	@Column(nullable = false)
	private BigDecimal price;

	public Order getOrder() { return order; }
	public void setOrder(Order order) { this.order = order; }

	public CourseListing getCourseListing() { return courseListing; }
	public void setCourseListing(CourseListing courseListing) { this.courseListing = courseListing; }

	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }
}


