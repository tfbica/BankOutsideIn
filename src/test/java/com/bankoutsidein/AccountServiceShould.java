package com.bankoutsidein;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceShould {

    AccountService accountService;
    @Mock TransactionRepository transactionRepository;
    @BeforeEach
    public void initialise() {
        accountService = new AccountService(transactionRepository);
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
    }
}