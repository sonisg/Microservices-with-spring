package com.micro.Accounts.controller;

import com.micro.Accounts.model.Accounts;
import com.micro.Accounts.model.Customer;
import com.micro.Accounts.repository.AccountRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    private AccountRepository accountRepository;
    @PostMapping("/myAccount")
    private Accounts getAccountDetails(@RequestBody Customer customer){
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        if(accounts != null){
            return accounts;
        }
        else{
            return null;
        }
    }
}
