package com.bankoutsidein;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryShould {

    private final TransactionRepository transactionRepository = new TransactionRepository();

    @Test
    void addDeposit() {

        // arrange
        int amount = 200;
        String expectedDate = "13/01/2012";

        // act
        transactionRepository.add(new Transaction(expectedDate, amount));

        // assert
        assertTransactionCreated(amount, expectedDate);
    }

    @Test
    void addWithdrawal() {

        // arrange
        int amount = 100;
        String expectedDate = "14/01/2012";

        // act
        transactionRepository.add(new Transaction(expectedDate, -amount));

        // assert
        assertTransactionCreated(-amount, expectedDate);
    }

    private void assertTransactionCreated(int amount, String expectedDate) {
        assertEquals(1, transactionRepository.getTransactions().size());
        Transaction transaction = transactionRepository.getTransactions().get(0);
        assertEquals(amount, transaction.getAmount());
        assertEquals(expectedDate, transaction.getDate());
    }
}