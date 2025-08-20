package org.example.controller;

import org.example.model.Accounts;
import org.example.model.Transactions;
import org.example.model.Users;
import org.example.service.AccountsService;
import org.example.service.TransactionsService;
import org.example.service.UsersService;

import java.util.List;

public class BankController {

    private final UsersService usersService;
    private final AccountsService accountsService;
    private final TransactionsService transactionsService;

    public BankController(UsersService usersService, AccountsService accountsService,
                          TransactionsService transactionsService) {
        this.usersService = usersService;
        this.accountsService = accountsService;
        this.transactionsService = transactionsService;
    }

    public Users login(String username, String password) {
        return usersService.login(username, password);
    }
 // new user
    public boolean register(String username, String password) {
        return usersService.register(username, password);
    }

    public double getBalance(int userId) {
        Accounts account = accountsService.getAccountByUserId(userId);
        return account.getBalance();
    }

    public boolean deposit(int userId, double amount) {
        boolean success = accountsService.deposit(userId, amount);
        if (success) transactionsService.addTransaction(userId, "deposit", amount);
        return success;
    }

    public boolean withdraw(int userId, double amount) {
        boolean success = accountsService.withdraw(userId, amount);
        if (success) transactionsService.addTransaction(userId, "withdraw", amount);
        return success;
    }

    public List<Transactions> getTransactions(int userId) {
        return transactionsService.getTransactions(userId);
    }
    public Users getUserById(int userId) {
        return usersService.getUserById(userId);
    }

    public Accounts getAccount(int userId) {
        return accountsService.getAccountByUserId(userId);
    }
}
