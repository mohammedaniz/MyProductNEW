package com.store.models.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	private long productId;
	
	private String productName;
	
	private String description;
	
	private BigDecimal price;
}
