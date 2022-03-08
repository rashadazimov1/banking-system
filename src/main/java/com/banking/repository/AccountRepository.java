package com.banking.repository;

import com.banking.model.Account;

import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account,String> {




}
