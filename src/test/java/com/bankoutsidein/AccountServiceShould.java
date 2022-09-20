package com.bankoutsidein;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class AccountServiceShould {

    @Mock Console console;

    @Test
    void print_statement_for_transactions() {

        AccountService accountService = new AccountService(console);

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(-500);

        accountService.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).println("Date       || Amount || Balance");
        inOrder.verify(console).println("14/01/2012 || -500   || 2500");
        inOrder.verify(console).println("13/01/2012 || 2000   || 3000");
        inOrder.verify(console).println("10/01/2012 || 1000   || 1000");
    }

}
