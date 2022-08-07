package com.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.store.entities.RoleE;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleE, Long> {

}
