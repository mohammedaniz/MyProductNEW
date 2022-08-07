package com.store.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.store.models.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUT {
	
	@Test
	public void saveProductService_whenValidProductIsPassed_receiveSavedProduct() {
		
	}
	
	public Product newProduct() {
		Product p = new Product();
		p.setDescription("test Description");
		p.setPrice(100.5);
		p.setProductName("LG TV");
		return p;
	}

}
