package com.bankoutsidein;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceShould {

    AccountService accountService;
    @Mock TransactionRepository transactionRepository;

    @Mock private StatementPrinter statementPrinter;

    @Mock private ClockService clockService;

    @BeforeEach
    public void initialise() {
        accountService = new AccountService(transactionRepository, statementPrinter, clockService);
    }

    @Test
    void deposit() {
        int amount = 100;
        String expectedDate = "10/01/2012";
        when(clockService.getCurrentDate()).thenReturn(expectedDate);

        accountService.deposit(amount);

        verify(transactionRepository).add(new Transaction(expectedDate, amount));
    }

    @Test
    void withdraw() {
        int amount = 500;
        String expectedDate = "10/01/2012";
        when(clockService.getCurrentDate()).thenReturn(expectedDate);

        accountService.withdraw(amount);

        verify(transactionRepository).add(new Transaction(expectedDate, -amount));
    }

    @Test
    void printStatement() {

        List<Transaction> allTransactions = new ArrayList<>();

        when(transactionRepository.getTransactions()).thenReturn(allTransactions);

        accountService.printStatement();

        verify(statementPrinter).printAll(allTransactions);
    }
}