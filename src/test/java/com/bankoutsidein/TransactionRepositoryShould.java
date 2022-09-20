package com.bankoutsidein;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryShould {

    private TransactionRepository transactionRepository;

    @Mock ClockService clockService;

    @BeforeEach
    void initialise() {
        this.transactionRepository = new TransactionRepository(clockService);
    }

    @Test
    void addDeposit() {

        // arrange
        int amount = 200;
        String expectedDate = "13/01/2012";
        when(clockService.getCurrentDate()).thenReturn(expectedDate);

        // act
        transactionRepository.addDeposit(amount);

        // assert
        assertEquals(1, transactionRepository.getTransactions().size());
        Transaction transaction = transactionRepository.getTransactions().get(0);
        assertEquals(amount, transaction.getAmount());
        assertEquals(expectedDate, transaction.getDate());
    }

    @Test
    void addWithdrawal() {

        // arrange
        int amount = 100;
        String expectedDate = "14/01/2012";
        when(clockService.getCurrentDate()).thenReturn(expectedDate);

        // act
        transactionRepository.addWithdrawal(amount);

        // assert
        assertEquals(1, transactionRepository.getTransactions().size());
        Transaction transaction = transactionRepository.getTransactions().get(0);
        assertEquals(-amount, transaction.getAmount());
        assertEquals(expectedDate, transaction.getDate());
    }
}