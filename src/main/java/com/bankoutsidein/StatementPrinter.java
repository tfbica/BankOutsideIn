package com.bankoutsidein;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    public static final String SEPARATOR = " || ";

    private final Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }
    public void printAll(List<Transaction> allTransactions) {
        printHeader();
        printTransactionsInReverse(allTransactions);
    }

    private void printHeader() {
        console.println(formatLine("Date", "Amount", "Balance"));
    }

    private void printTransactionsInReverse(List<Transaction> allTransactions) {
        createLinesWithBalance(allTransactions).stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        l -> { Collections.reverse(l); return l; }))
                .forEach(console::println);
    }

    private static List<String> createLinesWithBalance(List<Transaction> allTransactions) {
        AtomicInteger rollingBalance = new AtomicInteger(0);
        return allTransactions.stream().map(transaction ->
                        formatTransaction(transaction,
                                rollingBalance.addAndGet(transaction.getAmount())))
                .collect(Collectors.toList());
    }

    private static String formatTransaction(Transaction transaction, int rollingBalance) {
        return formatLine(transaction.getDate(),
                String.valueOf(transaction.getAmount()),
                String.valueOf(rollingBalance));
    }

    private static String formatLine(String date, String amount, String rollingBalance) {
        return date + SEPARATOR + amount + SEPARATOR + rollingBalance;
    }
}
