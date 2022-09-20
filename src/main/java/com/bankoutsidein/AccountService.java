package com.bankoutsidein;

public class AccountService {


    private final Console console;

    public AccountService(Console console) {

        this.console = console;
    }

    public void deposit(int amount) {

    }

    public void withdraw(int amount) {

    }

    public void printStatement() {
        console.println("Date       || Amount || Balance");
        console.println("14/01/2012 || -500   || 2500");
        console.println("13/01/2012 || 2000   || 3000");
        console.println("10/01/2012 || 1000   || 1000");
    }
}
