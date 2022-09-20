package com.bankoutsidein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    private final ClockService clockService;

    public TransactionRepository(ClockService clockService) {
        this.clockService = clockService;
    }

    public void addDeposit(int amount) {
        Transaction transaction = new Transaction(clockService.getCurrentDate(), amount);
        transactions.add(transaction);
    }

    public void addWithdrawal(int amount) {
        Transaction transaction = new Transaction(clockService.getCurrentDate(), -amount);
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
