package com.mj.javaapp.Bank_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mj.javaapp.Bank_app.entity.Account;
import com.mj.javaapp.Bank_app.repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService {
    
    @Autowired
    AccountRepository repository;
    
    @Override
    public Account createAccount(Account account) {
        Account account_saved = repository.save(account);
        return account_saved;
    }
    
    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repository.findById(accountNumber);
        if(account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }
        Account account_found = account.get();
        return account_found;
    }
    
    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> ListOfAccounts = repository.findAll();
        return ListOfAccounts;
    }
    
    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repository.findById(accountNumber);
        if(account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double totalBalance = accountPresent.getAccount_balance() + amount;
        accountPresent.setAccount_balance(totalBalance);
        repository.save(accountPresent);
        return accountPresent;
    }
    
    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repository.findById(accountNumber);
        if(account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        
        Double accountBalance = accountPresent.getAccount_balance() - amount;
        accountPresent.setAccount_balance(accountBalance);
        repository.save(accountPresent);
        return accountPresent;
    }
    
    @Override
    public void closeAccount(Long accountNumber) {
    	getAccountDetailsByAccountNumber(accountNumber);
        repository.deleteById(accountNumber);
        
    }
}






