package org.example.service;

import org.example.model.Accounts;
import org.example.repository.AccountsRepository;

import java.util.List;

public class AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }
// sending a request
    public Accounts getAccountByUserId(int userId) {
        return accountsRepository.getByUserId(userId);
    }

    public boolean deposit(int userId, double amount) {
        Accounts account = accountsRepository.getByUserId(userId);
        if (account == null) return false;
        double newBalance = account.getBalance() + amount;
        return accountsRepository.updateBalance(userId, newBalance);
    }

    public boolean withdraw(int userId, double amount) {
        Accounts account = accountsRepository.getByUserId(userId);
        if (account == null || account.getBalance() < amount) return false;
        double newBalance = account.getBalance() - amount;
        return accountsRepository.updateBalance(userId, newBalance);
    }

    public boolean createAccount(int userId, double initialBalance) {
        Accounts account = new Accounts(0, userId, initialBalance);
        return accountsRepository.newAccountsinsert(account);
    }

    public List<Accounts> getAll() {
        return accountsRepository.getAll();
    }
}
