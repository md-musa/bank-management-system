package com.baper_bank;

import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class CustomerController {
    public void withdraw() {
        Account ca = BankSystem.currentAccount;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("\nPlease enter positive number...");
            return;
        }

        System.out.println("\nEnter password: ");
        String password = sc.next();

        if (!BankSystem.currentUser.password.equals(password)) {
            System.out.println("\nInvalid password!");
            IOUtil.pressEnterKey();
            return;
        }

        if (ca.getBalance() <= amount) {
            System.out.println("\nYou don't have sufficient balance\n");
            IOUtil.pressEnterKey();
            return;
        }

        ca.setBalance(ca.getBalance() - amount);
        System.out.println("\nAmount " + amount + " has been withdrawn");
        IOUtil.pressEnterKey();
    }

    public void deposit() {
        Account ca = BankSystem.currentAccount;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("\nAmount must be positive number");
            IOUtil.pressEnterKey();
            return;
        }

        ca.setBalance(ca.getBalance() + amount);
        System.out.println("\nAmount " + amount + " has been deposited\n");
        IOUtil.pressEnterKey();
    }

    public void sendMoney() {
        Account ca = BankSystem.currentAccount;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter receiver account number: ");
        int receiverAN = sc.nextInt();

        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        System.out.println("\nEnter Password: ");
        String password = sc.next();

        if (amount <= 0) {
            System.out.println("Amount must be positive number");
            return;
        }
        if (amount > ca.getBalance()) {
            System.out.println("You to don't have sufficient balance\n");
            return;
        }

        Account sender = ca;
        Account receiver = null;
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            if (BankSystem.accounts.get(i).getAccountNumber() == receiverAN)
                receiver = BankSystem.accounts.get(i);
        }

        if (sender == null || receiver == null) {
            System.out.println("Please enter valid A/N\n");
            return;
        }

        if (!password.equals(BankSystem.currentUser.password)) {
            System.out.println("Invalid password!");
            IOUtil.pressEnterKey();
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transaction tc = new Transaction();
        tc.amount = amount;
        tc.senderAN = sender.getAccountNumber();
        tc.receiverAN = receiver.getAccountNumber();
        BankSystem.transactions.add(tc);

        System.out.println(amount + " has been transferred successfully to A/N: " + receiverAN);
        IOUtil.pressEnterKey();
    }

    public void showTransactionHistory() {
        System.out.println("--------Your all transactions history--------- ");

        for (int i = 0; i < BankSystem.transactions.size(); i++) {
            Transaction tc = BankSystem.transactions.get(i);
            System.out.println(tc.transactionId + " " + tc.timestamp + " " + tc.amount);

            // System.out.printf("| %-20s | %-10s |\n", "Transaction Id", "Amount");
            // System.out.printf("----------------------------------\n");
            // System.out.printf("| %-20s | %10d |\n", tc.transactionId, tc.amount);
        }

        IOUtil.pressEnterKey();
    }
}
