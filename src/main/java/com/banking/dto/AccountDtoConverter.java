package com.banking.dto;

import com.banking.model.Account;
import com.banking.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto converter(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .customerId(account.getCustomerId())
                .build();


    }
}
