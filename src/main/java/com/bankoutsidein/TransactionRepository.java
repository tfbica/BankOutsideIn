package com.bankoutsidein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository() {
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
