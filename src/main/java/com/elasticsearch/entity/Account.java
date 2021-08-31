package com.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="bank" , type="account")
public class Account {

	@JsonProperty("account_number")
	@Id
	//@Field(name="account_number" , value = "account_number")
	private int account_number ; 
	
	//@Column
	//@Field(name="balance")
	private Double balance;
	
	@Field(name="firstname") // @Field annotation is not needed ..
	private String firstname;
	
	@Field(name="lastname")
	private String lastname ;
	
	@Field(name="age")
	private Integer age;
	
	@Field(name="employer")
	private String employer ;
	
	@Field(name="state")
	private String state ;
	

	/*
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	*/
	
	 
}
