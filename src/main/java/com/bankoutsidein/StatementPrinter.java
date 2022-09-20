package com.bankoutsidein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatementPrinter {
    Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }
    public void printAll(List<Transaction> allTransactions) {
        List<String> linesWithBalance = createLinesWithBalance(allTransactions);
        Collections.reverse(linesWithBalance);
        console.println("Date || Amount || Balance");
        for (String line : linesWithBalance) {
            console.println(line);
        }
    }

    private static List<String> createLinesWithBalance(List<Transaction> allTransactions) {
        List<String> linesWithBalance = new ArrayList<>();
        int rollingBalance = 0;
        for (Transaction t : allTransactions) {
            rollingBalance += t.getAmount();
            linesWithBalance.add(t.getDate() + " || " + t.getAmount() + " || " + rollingBalance);
        }
        return linesWithBalance;
    }
}
