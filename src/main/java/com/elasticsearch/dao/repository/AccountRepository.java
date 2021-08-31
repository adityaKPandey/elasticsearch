package com.elasticsearch.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.elasticsearch.entity.Account;

public interface AccountRepository extends ElasticsearchRepository<Account, Integer> {

	Page<Account> findByFirstname(String firstname, Pageable pageable);
	
}
