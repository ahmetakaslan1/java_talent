package com.javaornek.soru.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false, unique = true)
	private Customer customer;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();

	@Column(nullable = false)
	private BigDecimal totalPrice = BigDecimal.ZERO;

	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }

	public List<CartItem> getItems() { return items; }
	public void setItems(List<CartItem> items) { this.items = items; }

	public BigDecimal getTotalPrice() { return totalPrice; }
	public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}


