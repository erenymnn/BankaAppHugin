package org.example.model;

public class Accounts {
    private int id;
    private int user_id;
    private double balance;

    public Accounts(int id, int user_id, double balance) {
        this.id = id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }


    public double getBalance() {
        return balance;
    }


}
