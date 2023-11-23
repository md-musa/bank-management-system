package com.baper_bank;

import java.util.List;
import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class AdminController {

    private int addUser() {
        int lastCustomerId = 1;
        if (BankSystem.customers.size() > 0)
            lastCustomerId = BankSystem.customers.get(BankSystem.customers.size() - 1).userId;

        Scanner sc = new Scanner(System.in);
        User newUser = new User();
        System.out.println("Enter Name: ");
        newUser.name = sc.nextLine();

        System.out.println("Enter Email: ");
        newUser.email = sc.nextLine();

        System.out.println("Enter Password: ");
        newUser.password = sc.nextLine();

        System.out.println("Enter Address: ");
        newUser.address = sc.nextLine();

        System.out.println("Enter Phone: ");
        newUser.phoneNumber = sc.nextLine();

        newUser.userId = lastCustomerId + 1;

        BankSystem.customers.add(newUser);
        return newUser.userId;
    }

    public void addAccount() {
        List<Account> accounts = BankSystem.accounts;
        int userId = this.addUser();
        int lastAN = 1000;
        if (accounts.size() > 0)
            lastAN = accounts.get(accounts.size() - 1).getAccountNumber() + 1;

        Account ac = new Account();
        ac.setAccountNumber(lastAN);
        ac.setUserId(userId);
        accounts.add(ac);

        System.out.println("=> Account has been created successfully\n");
        System.out.println("A/N: " + lastAN + "\n\n");
    }

    public void showAllAccounts() {
        List<Account> accounts = BankSystem.accounts;
        List<User> customers = BankSystem.customers;

        System.out.println("-------All Accounts--------\n");

        System.out.println("|------------|----------------------|-----------------|------------|");
        System.out.printf("| %-10s | %-20s | %-15s | %-10s |\n", "Account #", "Name", "Address", "Balance");
        System.out.println("|------------|----------------------|-----------------|------------|");

        for (int i = 0; i < accounts.size(); i++) {
            Account ac = accounts.get(i);

            for (int j = 0; j < customers.size(); j++) {

                if (ac.getUserId() == customers.get(j).userId) {

                    System.out.printf("| %-10s | %-20s | %-15s | %-10s |\n",
                            ac.getAccountNumber(), customers.get(j).name, customers.get(j).address, ac.getBalance());
                    break;
                }
            }
        }
        System.out.println("|------------|----------------------|-----------------|------------|");
        IOUtil.pressEnterKey();
    }

    public void showAllTransactionHistory() {
        List<Transaction> trns = BankSystem.transactions;

        System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-20s |\n",
                "Transaction ID", "Sender AN", "Receiver AN", "Amount", "Timestamp");
        System.out.println(
                "|-----------------|----------------|----------------|------------|----------------------|");

        for (int i = 0; i < trns.size(); i++) {
            Transaction trn = trns.get(i);

            // Assuming `trn` is an instance of your Transaction class
            System.out.printf("| %-15s | %-15s | %-15s | %-10s | %-20s |\n",
                    trn.transactionId, trn.senderAN, trn.receiverAN, trn.amount,
                    trn.timestamp);
        }
        System.out.println(
                "|-----------------|----------------|----------------|------------|----------------------|\n");

        IOUtil.pressEnterKey();
    }
}
