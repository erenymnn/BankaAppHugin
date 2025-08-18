package org.example.service;

import org.example.model.Accounts;
import org.example.repository.AccountsRepository;

public class AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Accounts getAccountByUserId(int userId) {
        return accountsRepository.getByUserId(userId);
    }

    public boolean deposit(int userId, double amount) {
        Accounts account = accountsRepository.getByUserId(userId);
        double newBalance = account.getBalance() + amount;
        accountsRepository.updateBalance(userId, newBalance);
        return false;
    }

    public boolean withdraw(int userId, double amount) {
        Accounts account = accountsRepository.getByUserId(userId);
        if (account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            accountsRepository.updateBalance(userId, newBalance);
            return true;
        }
        return false; // Yetersiz bakiye
    }
}
