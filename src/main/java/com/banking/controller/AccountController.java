package com.banking.controller;

import com.banking.dto.AccountDto;
import com.banking.dto.CreateAccountRequest;
import com.banking.dto.UpdateAccountRequest;
import com.banking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAllAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccountById(id));

    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id,
                                                    @RequestBody UpdateAccountRequest updateAccountRequest){
        return ResponseEntity.ok(accountService.updateAccount(id,updateAccountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){
        accountService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
