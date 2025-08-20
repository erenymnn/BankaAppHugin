package org.example.service;

import org.example.model.Transactions;
import org.example.model.Accounts;
import org.example.repository.TransactionsRepository;
import org.example.repository.AccountsRepository;

import java.sql.Timestamp;
import java.util.List;

public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final AccountsRepository accountsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository,
        AccountsRepository accountsRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountsRepository = accountsRepository;
    }

    public void addTransaction(int userId, String type, double amount) {
        Accounts account = accountsRepository.getByUserId(userId);
        if (account != null) {
            Transactions transaction = new Transactions(
                    0,
                    account.getId(),
                    type,
                    amount,
                    new Timestamp(System.currentTimeMillis())
            );
            transactionsRepository.insert(transaction);
        }
    }

    public List<Transactions> getTransactions(int userId) {
        Accounts account = accountsRepository.getByUserId(userId);
        if (account != null) {
            return transactionsRepository.getByAccountId(account.getId());
        }
        return List.of();
    }
}
