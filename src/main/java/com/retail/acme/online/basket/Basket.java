package com.retail.acme.online.basket;

import com.fasterxml.jackson.annotation.JsonInclude;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class Basket {

	public Basket () {
	}

	public Basket (
		String product, 
		Integer quantity, 
		java.math.BigDecimal price, 
		Integer customerId, 
		String id, 
		String state) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.customerId = customerId;
		this.id = id;
		this.state = state;
	}

	private String product;
	private Integer quantity;
	private java.math.BigDecimal price;
	private Integer customerId;
	private String id;
	private String state;
	public String getProduct() {
		return product;
	}

	public Basket setProduct(String product) {
		this.product = product;
		return this;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public Basket setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}


	public java.math.BigDecimal getPrice() {
		return price;
	}

	public Basket setPrice(java.math.BigDecimal price) {
		this.price = price;
		return this;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public Basket setCustomerId(Integer customerId) {
		this.customerId = customerId;
		return this;
	}


	public String getId() {
		return id;
	}

	public Basket setId(String id) {
		this.id = id;
		return this;
	}


	public String getState() {
		return state;
	}

	public Basket setState(String state) {
		this.state = state;
		return this;
	}

	public String toString() {
		return "Basket ["
		+ " product: " + product
		+ " quantity: " + quantity
		+ " price: " + price
		+ " customerId: " + customerId
		+ " id: " + id
		+ " state: " + state
		+ " ]";
	}
}
