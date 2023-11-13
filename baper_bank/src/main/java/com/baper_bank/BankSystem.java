package com.baper_bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class BankSystem {

    List<Customer> customers = new ArrayList<Customer>();
    List<Account> accounts = new ArrayList<Account>();
    List<Transaction> transactions = new ArrayList<Transaction>();

    protected int addCustomer() {
        int lastCustomerId = 1;
        if (customers.size() > 0)
            lastCustomerId = this.customers.get(this.customers.size() - 1).customerId;

        Scanner sc = new Scanner(System.in);
        Customer nc = new Customer();
        System.out.println("Account holder Name: ");
        nc.name = sc.nextLine();
        System.out.println("Account holder Address: ");
        nc.address = sc.nextLine();
        System.out.println("Account holder Phone: ");
        nc.phoneNumber = sc.nextLine();
        nc.customerId = lastCustomerId + 1;

        customers.add(nc);
        return nc.customerId;
    }

    public void removeCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customerId == customers.get(i).customerId) {
                customers.remove(i);
                System.out.println("Customer with id: " + customerId + " removed successfully");
                return;
            }
        }
        System.out.println("Customer with id: " + customerId + " does not exist");
    }

    public void addAccount() {
        int customerId = this.addCustomer();
        int lastAN = 1;
        if (accounts.size() > 0)
            lastAN = this.accounts.get(this.accounts.size() - 1).getAccountId() + 1;

        Account ac = new Account();
        ac.setAccountId(lastAN);
        ac.setCustomerId(customerId);
        accounts.add(ac);

        System.out.println("Account created successfully\n");
        System.out.println("A/N: " + lastAN + "\n\n");
    }

    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter account number: ");
        int AN = sc.nextInt();
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Amount must be positive number");
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == AN) {
                Account ac = accounts.get(i);
                ac.setBalance(ac.getBalance() + amount);
                System.out.println("Amount " + amount + " is has been deposited\n");
                return;
            }
        }
        System.out.println("Something went wrong");
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter account number: ");
        int AN = sc.nextInt();
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Amount must be positive number");
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == AN) {
                Account ac = accounts.get(i);
                if (ac.getBalance() < amount) {
                    System.out.println("Not have sufficient balance");
                    return;
                }
                ac.setBalance(ac.getBalance() - amount);
                System.out.println("Amount " + amount + " is has been withdrawn\n");
                return;
            }
        }
        System.out.println("Something went wrong");
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
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == senderAN)
                sender = accounts.get(i);

            if (accounts.get(i).getAccountId() == receiverAN)
                receiver = accounts.get(i);
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
        transactions.add(tc);

        System.out.println(amount + " is transferred successfully to A/N: " + receiverAN);
    }

    public void showAllAccounts() {
        System.out.println("-------All Accounts--------");
        for (int i = 0; i < accounts.size(); i++) {
            Account ac = accounts.get(i);
            for (int j = 0; j < customers.size(); j++) {
                if (ac.getCustomerId() == customers.get(j).customerId) {

                    System.out.print("| " +
                            ac.getAccountId() + "  " +
                            customers.get(j).name + "  " +
                            customers.get(j).address + "  " + ac.getBalance() + " |\n");
                    break;
                }
            }

        }
        System.out.println("--------------------------");

    }

    public void showMenu() {
        System.out.println("-------Welcome to Baper Bank--------");
        System.out.println("1. Create account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer money");
        System.out.println("5. Show all accounts");
        System.out.println("6. Check balance\n");
        System.out.println("Please enter: ");
        Scanner sc = new Scanner(System.in);
        int serial = sc.nextInt();

        if (serial == 1)
            addAccount();
        else if (serial == 2)
            deposit();
        else if (serial == 3)
            withdraw();
        else if (serial == 5)
            showAllAccounts();
        else if (serial == 6)
            checkBalance();

        System.out.println("------------------------------------");

    }

    public void checkBalance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Account number: ");
        int an = sc.nextInt();
        for (int i = 0; i < accounts.size(); i++) {
            if (an == accounts.get(i).getAccountId()) {
                System.out.print("Balance: " + accounts.get(i).getBalance() + "\n");
            }
        }
        IOUtil.pressEnterKey();
    }
}
