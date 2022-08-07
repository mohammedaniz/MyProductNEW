package com.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.store.entities.ProductE;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductE, Long> {

}
