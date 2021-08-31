package com.elasticsearch.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.dao.AccountDao;
import com.elasticsearch.dao.repository.AccountRepository;
import com.elasticsearch.entity.Account;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/account") //"/v1/schedule"
public class AccountController {

	@Autowired
	private AccountRepository accountRepository ;

	@Autowired
	private AccountDao accountDao ;


	@GetMapping(path = "/firstname/{firstName}",produces=MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation("fetches candidates")
	public List<Account> getAccount(@PathVariable String firstName) throws Exception{

		System.out.println("Firstname:"+firstName);
		Page<Account> accountPages = accountRepository.findByFirstname(firstName, PageRequest.of(0, 10));
		List<Account> accounts =  accountPages.get().collect(Collectors.toList()) ;
		System.out.println("Accounts:"+accounts.toString());
		return accounts;
	}

	@PutMapping(path = "/",produces=MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation("updates account")
	public Account putAccount(@RequestBody Account account) throws Exception{

		System.out.println("Account:"+account.toString());
		account = accountRepository.save(account) ;
		System.out.println("Account saved:" + account);
		return account;
	}

	@DeleteMapping(path = "/{accountNumber}",produces=MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation("deletes account")
	public ResponseEntity<String> deleteAccount( @PathVariable Integer accountNumber) throws Exception{

		System.out.println("Account Number:"+accountNumber);
		accountRepository.deleteById(accountNumber);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}


	//TODO
	/*
	  https://dzone.com/articles/introduction-to-spring-data-elasticsearch-41

	   1. Solve problem of mapping different names in schema and in java POJO entity class : DONE . ES uses fasterxml json so use JsonProperty annotation
	   2. Use Criteria for simple and complex queries DONE
	   3. Bulk/Batch insert from json or csv
	   4. Aggregate queries
	   5. Complete CRUD operations - C R U D DONE  
	 */

	@GetMapping(path = "/firstname/{employer}/{age}/{state}/{page}/{size}",produces=MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation("fetches candidates")
	public List<Account> getAccount(@PathVariable String employer , @PathVariable Integer age , @PathVariable String state,
			@PathVariable Integer page , @PathVariable Integer size) throws Exception{

		System.out.println("State:"+state);
		//Page<Account> accountPages = accountRepository.findByFirstname(firstName, PageRequest.of(0, 10));
		List<Account> accounts =  accountDao.searchByEmployerAgeState(employer, age, state, PageRequest.of(page, size)) ;
		System.out.println("Accounts:"+accounts.toString());
		return accounts;
	}

}
