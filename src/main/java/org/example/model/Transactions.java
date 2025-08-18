package org.example.model;

import java.security.Timestamp;

public class Transactions {
    private  int id;
    private int account_id;
    private String type;
    private double amount;
    private Timestamp createdAd;

    public Transactions(int id, int account_id, String type, double amount, Timestamp createdAd) {
        this.id = id;
        this.account_id = account_id;
        this.type = type;
        this.amount = amount;
        this.createdAd = createdAd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(Timestamp createdAd) {
        this.createdAd = createdAd;
    }
}
