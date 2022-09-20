package com.bankoutsidein;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }
    public void printAll(List<Transaction> allTransactions) {
        console.println("Date || Amount || Balance");
        printTransactions(allTransactions);
    }

    private void printTransactions(List<Transaction> allTransactions) {
        List<String> linesWithBalance = createLinesWithBalance(allTransactions);
        Collections.reverse(linesWithBalance);
        linesWithBalance.forEach(line -> console.println(line));
    }

    private static List<String> createLinesWithBalance(List<Transaction> allTransactions) {
        AtomicInteger rollingBalance = new AtomicInteger(0);
        return allTransactions.stream().map(transaction ->
                formatTransactionLine(transaction, rollingBalance.addAndGet(transaction.getAmount())))
                .collect(Collectors.toList());
    }

    private static String formatTransactionLine(Transaction transaction, int rollingBalance) {
        return transaction.getDate()
                + " || " + transaction.getAmount()
                + " || " + rollingBalance;
    }
}
