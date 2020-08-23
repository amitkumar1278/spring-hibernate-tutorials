package com.spring.sboot.repositories.composite;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.domains.composite.Account;
import com.spring.sboot.domains.composite.AccountId;

public interface AccountRepository extends CrudRepository<Account, AccountId> {
	
	 List<Account> findByAccountType(String accountType);

}
