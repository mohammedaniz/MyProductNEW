package com.store.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.store.entities.UserE;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserE, Long> {

	public Optional<UserE> findByEmailId(String emailId);
}
