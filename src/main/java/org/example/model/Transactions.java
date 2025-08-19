package org.example.model;

import java.sql.Timestamp;

public class Transactions {
    private int id;
    private int account_id;
    private String type;
    private double amount;
    private Timestamp createdAt;

    public Transactions(int id, int account_id, String type, double amount, Timestamp createdAt) {
        this.id = id;
        this.account_id = account_id;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getType() {
        return type;
    }


    public double getAmount() {
        return amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

}
