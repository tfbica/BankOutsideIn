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
        List<String> temp = new ArrayList<>();
        int rollingBalance = 0;
        for (Transaction t : allTransactions) {
            rollingBalance += t.getAmount();
            temp.add(t.getDate() + " || " + t.getAmount() + " || " + rollingBalance);
        }

        console.println("Date || Amount || Balance");

        Collections.reverse(temp);
        for (String line : temp) {
            console.println(line);
        }
    }
}
