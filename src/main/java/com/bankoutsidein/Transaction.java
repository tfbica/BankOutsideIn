package com.bankoutsidein;

public class Transaction {

    private final int amount;
    private final String date;

    public Transaction(String date, int amount) {
        this.amount = amount;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
