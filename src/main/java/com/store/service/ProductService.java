package com.store.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.ProductE;
import com.store.models.Product;
import com.store.models.Response;
import com.store.models.dto.ProductDTO;
import com.store.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Response saveProductService(Product product) {
		if(product != null) {
			ProductE entity = this.buildProductEntityService(product, false);
			if(entity != null) {
				ProductE savedProduct = productRepository.save(entity);
				if(savedProduct != null) {
					ProductDTO dto = this.buildProductDTOService(savedProduct);
					return new Response(dto, 200, "Successfully Saved The Product Details");
				}else {
					throw new NullPointerException("Unable to Save Product While Saving Product");
				}
			}else {
				throw new NullPointerException("ProductE Entity is Null");
			}
		}else {
			throw new NullPointerException("Product Object is Null");
		}
	}
	
	public ProductDTO buildProductDTOService(ProductE product) {
		if(product != null) {
			ProductDTO dto = new ProductDTO();
			dto.setDescription(product.getDescription());
			dto.setPrice(product.getPrice());
			dto.setProductId(product.getProductId());
			dto.setProductName(product.getProductName());
			return dto;
		}else {
			throw new NullPointerException("ProductE Entity Object is Null");
		}
	}
	public ProductE buildProductEntityService(Product product, boolean isUpdate) {
		if(product != null) {
			ProductE p = new ProductE();
			p.setDescription(product.getDescription());
			p.setPrice(new BigDecimal(product.getPrice()));
			p.setProductName(product.getProductName());
			
			if(isUpdate && product.getProductId() > 0) {
				p.setProductId(product.getProductId());
			}
			
			return p;
			
		}else {
			throw new NullPointerException("Product Object is Null");
		}
	}
	
}
