package com.bankoutsidein;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class StatementPrinterShould {
    StatementPrinter statementPrinter;
    @Mock
    Console console;

    @BeforeEach
    void initialise() {
        this.statementPrinter = new StatementPrinter(console);
    }

    @Test
    void printAllJustHeader() {
        List<Transaction> transactionList = new ArrayList<>();
        statementPrinter.printAll(transactionList);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).println("Date || Amount || Balance");
    }

    @Test
    void printAll() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("10/01/2012", 1000));
        transactionList.add(new Transaction("13/01/2012", 2000));
        transactionList.add(new Transaction("14/01/2012", -500));

        statementPrinter.printAll(transactionList);

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).println("Date || Amount || Balance");
        inOrder.verify(console).println("14/01/2012 || -500 || 2500");
        inOrder.verify(console).println("13/01/2012 || 2000 || 3000");
        inOrder.verify(console).println("10/01/2012 || 1000 || 1000");
    }



}