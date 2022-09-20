package com.bankoutsidein;

public class Main {

    public static void main(String[] args) {

        ClockService clockService = new ClockService();
        Console console = new Console();

        StatementPrinter statementPrinter = new StatementPrinter(console);
        TransactionRepository transactionRepository = new TransactionRepository(clockService);
        AccountService accountService =
                new AccountService(transactionRepository, statementPrinter);

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);

        accountService.printStatement();

    }

}
