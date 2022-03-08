package com.banking.service;

import com.banking.dto.AccountDto;
import com.banking.dto.AccountDtoConverter;
import com.banking.dto.CreateAccountRequest;
import com.banking.model.Account;
import com.banking.model.City;
import com.banking.model.Currency;
import com.banking.model.Customer;
import com.banking.repository.AccountRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class AccountServiceTest {

    private AccountService accountService;

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;


    @Before
    public void setup()throws Exception{
         accountRepository= Mockito.mock(AccountRepository.class);
         customerService=Mockito.mock((CustomerService.class));
         accountDtoConverter=Mockito.mock(AccountDtoConverter.class);
         accountService=new AccountService(accountDtoConverter, accountRepository, customerService);
    }

    @Test
    public void whenCreateAccountCalledWithValidRequest_itShouldReturnValidAccountDto()throws Exception{
        CreateAccountRequest createAccountRequest=generateCreateAccountRequest();
        Customer customer=generateCustomer();
        Account account=generateAccount(createAccountRequest);
        AccountDto accountDto=generateAccountDto();

        Mockito.when(customerService.getCustomerById("12345")).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Mockito.when(accountDtoConverter.converter(account)).thenReturn(accountDto);

        AccountDto result=accountService.createAccount(createAccountRequest);

        Assertions.assertEquals(result,accountDto);
        Mockito.verify(customerService.getCustomerById("12345"));
        Mockito.verify(accountRepository.save(account));
        Mockito.verify(accountDtoConverter.converter(account));


    }

    private CreateAccountRequest generateCreateAccountRequest(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.Baku);
        createAccountRequest.setCurrency(Currency.USD);
        return createAccountRequest;
    }

    private Customer generateCustomer() {
        return Customer.builder()
                .id("12345")
                .address("Adres")
                .city(City.Baku)
                .dateOfBirth(1998)
                .name("Muratcan")
                .build();
    }

    private Account generateAccount(CreateAccountRequest accountRequest) {
        return Account.builder()
                .id(accountRequest.getId())
                .balance(accountRequest.getBalance())
                .currency(accountRequest.getCurrency())
                .customerId(accountRequest.getCustomerId())
                .city(accountRequest.getCity())
                .build();
    }

    private AccountDto generateAccountDto() {
        return AccountDto.builder()
                .id("1234")
                .customerId("12345")
                .currency(Currency.USD)
                .balance(100.0)
                .build();
    }




}
