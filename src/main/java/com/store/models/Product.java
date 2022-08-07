package com.store.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {

	private long productId;
	
	private String productName;
	
	private String description;
	
	private double price;
	
	
}
