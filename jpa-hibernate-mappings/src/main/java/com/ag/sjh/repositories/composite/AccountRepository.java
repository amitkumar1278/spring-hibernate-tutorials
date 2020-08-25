package com.ag.sjh.repositories.composite;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.domains.composite.Account;
import com.ag.sjh.domains.composite.AccountId;

public interface AccountRepository extends CrudRepository<Account, AccountId> {
	
	 List<Account> findByAccountType(String accountType);

}
