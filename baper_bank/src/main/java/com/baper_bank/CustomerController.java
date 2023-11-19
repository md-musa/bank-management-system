package com.baper_bank;

import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class CustomerController {

    public void checkBalance() {
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            if (BankSystem.currentAccount.getAccountNumber() == BankSystem.accounts.get(i).getAccountNumber()) {
                System.out.print("Balance: " + BankSystem.accounts.get(i).getBalance() + "\n");
            }
        }
        IOUtil.pressEnterKey();
    }

    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("\nAmount must be positive number");
            IOUtil.pressEnterKey();
            return;
        }

        BankSystem.currentAccount.setBalance(BankSystem.currentAccount.getBalance() + amount);
        System.out.println("\nAmount " + amount + " has been deposited\n");
        IOUtil.pressEnterKey();
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        System.out.println("\nEnter password: ");
        String password = sc.next();

        if (!BankSystem.currentUser.password.equals(password)) {
            System.out.println("\nInvalid password");
            IOUtil.pressEnterKey();
            return;
        }

        if (amount < 0) {
            System.out.println("\nAmount must be positive number");
            return;
        }

        if (BankSystem.currentAccount.getBalance() < amount) {
            System.out.println("\nYou don't have sufficient balance\n");
            IOUtil.pressEnterKey();
            return;
        }

        BankSystem.currentAccount.setBalance(BankSystem.currentAccount.getBalance() - amount);
        System.out.println("\nAmount " + amount + " is has been withdrawn");
        IOUtil.pressEnterKey();

        // for (int i = 0; i < accounts.size(); i++) {
        // if (accounts.get(i).getAccountNumber() == currentAccount.getAccountNumber())
        // {
        // Account ac = accounts.get(i);
        // if (ac.getBalance() < amount) {
        // System.out.println("Not have sufficient balance");
        // return;
        // }
        // ac.setBalance(ac.getBalance() - amount);
        // System.out.println("Amount " + amount + " is has been withdrawn\n");
        // IOUtil.pressEnterKey();
        // return;
        // }
        // }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter sender account number: ");
        int senderAN = sc.nextInt();

        System.out.println("\nEnter receiver account number: ");
        int receiverAN = sc.nextInt();

        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Amount must be positive number");
            return;
        }
        Account sender = null;
        Account receiver = null;
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            if (BankSystem.accounts.get(i).getAccountNumber() == senderAN)
                sender = BankSystem.accounts.get(i);

            if (BankSystem.accounts.get(i).getAccountNumber() == receiverAN)
                receiver = BankSystem.accounts.get(i);
        }

        if (sender == null || receiver == null) {
            System.out.println("Please enter valid A/N\n");
            return;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Not have sufficient balance\n");
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transaction tc = new Transaction();
        tc.amount = amount;
        tc.senderAN = senderAN;
        tc.receiverAN = receiverAN;
        BankSystem.transactions.add(tc);

        System.out.println(amount + " is transferred successfully to A/N: " + receiverAN);
        IOUtil.pressEnterKey();
    }

}
