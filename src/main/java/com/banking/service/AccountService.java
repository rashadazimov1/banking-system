package com.banking.service;

import com.banking.dto.AccountDto;
import com.banking.dto.AccountDtoConverter;
import com.banking.dto.CreateAccountRequest;
import com.banking.dto.UpdateAccountRequest;
import com.banking.exception.CustomerNotFoundException;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
private final AccountDtoConverter accountDtoConverter;
    private  final AccountRepository accountRepository;
    private  final  CustomerService customerService;



    public AccountService(AccountDtoConverter accountDtoConverter, AccountRepository accountRepository, CustomerService customerService) {
        this.accountDtoConverter = accountDtoConverter;
        this.accountRepository = accountRepository;
        this.customerService = customerService;


    }

    public AccountDto createAccount(CreateAccountRequest accountRequest){
        Customer customer=customerService.getCustomerById(accountRequest.getCustomerId());

        if (customer.getId()==null || customer.getId().trim().equals("")){
            throw new CustomerNotFoundException("Customer not found");
        }
         Account account=Account.builder()
                 .id(accountRequest.getCustomerId())
                 .balance(accountRequest.getBalance())
                 .currency(accountRequest.getCurrency())
                 .city(accountRequest.getCity())
                 .build();

        return accountDtoConverter.converter(accountRepository.save(account));

    }

    public AccountDto updateAccount( String id, UpdateAccountRequest updateAccountRequest){
        Customer customer = customerService.getCustomerById(updateAccountRequest.getCustomerId());
        if (customer.getId().equals("") ||customer.getId() == null) {
            return AccountDto.builder().build();
        }

        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(updateAccountRequest.getBalance());
            account.setCity(updateAccountRequest.getCity());
            account.setCurrency(updateAccountRequest.getCurrency());
            account.setCustomerId(updateAccountRequest.getCustomerId());
            accountRepository.save(account);
        });
        return accountOptional.map(accountDtoConverter::converter).orElse(new AccountDto());
    }

    public List<AccountDto> getAllAccounts(){
        List<Account>accountList= (List<Account>) accountRepository.findAll();
        return accountList.stream().map(accountDtoConverter::converter).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id){
        return accountRepository.findById(id)
                .map(accountDtoConverter::converter)
                .orElse(new AccountDto());
    }
    public  void deleteById(String id){
        accountRepository.deleteById(id);

    }

    public AccountDto withDrawMoney(String id,Double amount){
        Optional<Account>accountOptional=accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if (account.getBalance() > amount){
                account.setBalance(account.getBalance() -amount);
                accountRepository.save(account);
            }else {
                System.out.println("Insufficient funds -> accountId:"+id+"balance:"+account.getBalance()+"amount"+amount) ;
            }
        });
        return accountOptional.map(accountDtoConverter::converter).orElse(new AccountDto());
    }
    public AccountDto addMoney(String id,Double amount){
        Optional<Account> accountOptional=accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(account.getBalance() +amount);
            accountRepository.save(account);
        });
        return accountOptional.map(accountDtoConverter::converter).orElse(new AccountDto());
    }

}
