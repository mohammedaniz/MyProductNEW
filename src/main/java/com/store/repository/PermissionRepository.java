package com.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.store.entities.PermissionE;

@Repository
public interface PermissionRepository extends PagingAndSortingRepository<PermissionE, Long> {

}
