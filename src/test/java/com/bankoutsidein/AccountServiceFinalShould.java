package com.bankoutsidein;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceFinalShould {

    @Mock
    ClockService clockService;
    @Mock
    Console console;
    AccountService accountService;

    @BeforeEach
    void initialise() {
        StatementPrinter statementPrinter = new StatementPrinter(console);
        TransactionRepository transactionRepository = new TransactionRepository();
        accountService = new AccountService(transactionRepository, statementPrinter, clockService);
    }

    @Test
    void print_statement_for_transactions() {

        when(clockService.getCurrentDate()).thenReturn(
                "10/01/2012",
                "13/01/2012",
                "14/01/2012");

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);

        accountService.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).println("Date || Amount || Balance");
        inOrder.verify(console).println("14/01/2012 || -500 || 2500");
        inOrder.verify(console).println("13/01/2012 || 2000 || 3000");
        inOrder.verify(console).println("10/01/2012 || 1000 || 1000");
    }


}
