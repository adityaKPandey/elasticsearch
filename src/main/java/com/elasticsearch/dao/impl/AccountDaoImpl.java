package com.elasticsearch.dao.impl;

import static com.elasticsearch.constants.ElasticSearchQueryConstants.AGE;
import static com.elasticsearch.constants.ElasticSearchQueryConstants.EMPLOYER;
import static com.elasticsearch.constants.ElasticSearchQueryConstants.STATE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;

import com.elasticsearch.dao.AccountDao;
import com.elasticsearch.entity.Account;

@Repository
public class AccountDaoImpl implements AccountDao {


	@Autowired
	private ElasticsearchOperations elasticsearchTemplate ;
	
	@Override
	public List<Account> searchByEmployerAgeState(String employer, Integer age, String state , Pageable page) {
		
		Criteria criteria = new Criteria(EMPLOYER).is(employer).
				and(AGE).is(age).and(STATE).is(state);
		
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
		criteriaQuery.setPageable(page);
		
		
	     
		return elasticsearchTemplate.queryForList(criteriaQuery,Account.class);
	}

}
