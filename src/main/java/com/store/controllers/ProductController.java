package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.models.Product;
import com.store.models.Response;
import com.store.service.ProductService;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "save" , method = RequestMethod.POST)
	public ResponseEntity<Object> saveProductController(@RequestBody Product product) {
		Response response = productService.saveProductService(product);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
