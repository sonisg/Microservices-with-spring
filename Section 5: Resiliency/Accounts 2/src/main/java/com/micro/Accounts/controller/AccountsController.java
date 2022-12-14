package com.micro.Accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.micro.Accounts.config.AccountsConfig;
import com.micro.Accounts.model.Accounts;
import com.micro.Accounts.model.Cards;
import com.micro.Accounts.model.Customer;
import com.micro.Accounts.model.CustomerDetails;
import com.micro.Accounts.model.Loans;
import com.micro.Accounts.model.Properties;
import com.micro.Accounts.repository.AccountRepository;
import com.micro.Accounts.service.client.CardsFeignClient;
import com.micro.Accounts.service.client.LoansFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Autowired
    AccountsConfig accountsConfig;
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

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
                accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    @PostMapping("/myCustomerDetails")
    //@CircuitBreaker(name = "detailsForCustomerSupportApp",fallbackMethod="myCustomerDetailsFallBack")
    @Retry(name = "retryForCustomerDetails",fallbackMethod="myCustomerDetailsFallBack" )
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(customer);
        List<Cards> cards = cardsFeignClient.getCardDetails(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);

        return customerDetails;
    }

    private CustomerDetails myCustomerDetailsFallBack(Customer customer, Throwable t) {
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        return customerDetails;

    }

    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to EazyBank";
    }

    private String sayHelloFallback(Throwable t) {
        return "Hi, Welcome to EazyBank";
    }

}
