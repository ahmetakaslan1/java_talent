package com.javaornek.soru.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();

	@Column(nullable = false, unique = true)
	private String orderCode;

	@Column(nullable = false)
	private BigDecimal totalPrice = BigDecimal.ZERO;

	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }

	public List<OrderItem> getItems() { return items; }
	public void setItems(List<OrderItem> items) { this.items = items; }

	public String getOrderCode() { return orderCode; }
	public void setOrderCode(String orderCode) { this.orderCode = orderCode; }

	public BigDecimal getTotalPrice() { return totalPrice; }
	public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}


