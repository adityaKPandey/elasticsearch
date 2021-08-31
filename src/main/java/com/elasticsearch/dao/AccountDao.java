package com.elasticsearch.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.elasticsearch.entity.Account;

public interface AccountDao {

	List<Account> searchByEmployerAgeState(String employer , Integer age , String state , Pageable page) ;
	
}
