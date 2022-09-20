package com.bankoutsidein;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeEach
    public void initialise() {
        accountService = new AccountService(transactionRepository, statementPrinter);
    }

    @Test
    void deposit() {
        int amount = 100;
        accountService.deposit(amount);

        verify(transactionRepository).addDeposit(amount);
    }

    @Test
    void withdraw() {
        int amount = 500;
        accountService.withdraw(amount);

        verify(transactionRepository).addWithdrawal(amount);
    }

    @Test
    void printStatement() {

        List<Transaction> allTransactions = new ArrayList<>();

        when(transactionRepository.getTransactions()).thenReturn(allTransactions);

        accountService.printStatement();

        verify(statementPrinter).printAll(allTransactions);
    }
}