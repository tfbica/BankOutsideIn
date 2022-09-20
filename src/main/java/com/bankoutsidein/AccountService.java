package com.bankoutsidein;

import java.util.List;

public class AccountService {
    private final TransactionRepository transactionRepository;

    private final StatementPrinter statementPrinter;

    private final ClockService clockService;

    public AccountService(TransactionRepository transactionRepository,
                          StatementPrinter statementPrinter,
                          ClockService clockService) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
        this.clockService = clockService;
    }

    public void deposit(int amount) {
        transactionRepository.add(new Transaction(clockService.getCurrentDate(), amount));

    }

    public void withdraw(int amount) {
        transactionRepository.add(new Transaction(clockService.getCurrentDate(), -amount));
    }

    public void printStatement() {

        List<Transaction> transactions = transactionRepository.getTransactions();
        statementPrinter.printAll(transactions);

    }
}
