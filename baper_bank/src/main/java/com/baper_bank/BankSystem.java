package com.baper_bank;

import java.beans.Customizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class BankSystem {
    User currentUser;
    Account currentAccount;
    String currentUserRole;

    List<User> customers = new ArrayList<User>();

    List<User> admins = new ArrayList<User>();
    List<Account> accounts = new ArrayList<Account>();
    List<Transaction> transactions = new ArrayList<Transaction>();

    protected int addUser() {
        int lastCustomerId = 1;
        if (customers.size() > 0)
            lastCustomerId = this.customers.get(this.customers.size() - 1).userId;

        Scanner sc = new Scanner(System.in);
        User newUser = new User();
        System.out.println("Account holder Name: ");
        newUser.name = sc.nextLine();

        System.out.println("Account holder Email: ");
        newUser.email = sc.nextLine();

        System.out.println("Account holder Password: ");
        newUser.password = sc.nextLine();

        System.out.println("Account holder Address: ");
        newUser.address = sc.nextLine();

        System.out.println("Account holder Phone: ");
        newUser.phoneNumber = sc.nextLine();

        newUser.userId = lastCustomerId + 1;

        customers.add(newUser);
        return newUser.userId;
    }

    public void removeCustomer(int userId) {
        for (int i = 0; i < customers.size(); i++) {
            if (userId == customers.get(i).userId) {
                customers.remove(i);
                System.out.println("Customer with id: " + userId + " removed successfully");
                return;
            }
        }
        System.out.println("Customer with id: " + userId + " does not exist");
    }

    public void addAccount() {
        int userId = this.addUser();
        int lastAN = 1;
        if (accounts.size() > 0)
            lastAN = this.accounts.get(this.accounts.size() - 1).getAccountNumber() + 1;

        Account ac = new Account();
        ac.setAccountNumber(lastAN);
        ac.setUserId(userId);
        accounts.add(ac);

        System.out.println("Account created successfully\n");
        System.out.println("A/N: " + lastAN + "\n\n");
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

        currentAccount.setBalance(currentAccount.getBalance() + amount);
        System.out.println("\nAmount " + amount + " has been deposited\n");
        IOUtil.pressEnterKey();
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter amount: ");
        double amount = sc.nextDouble();

        System.out.println("\nEnter password: ");
        String password = sc.next();

        if (!currentUser.password.equals(password)) {
            System.out.println("\nInvalid password");
            IOUtil.pressEnterKey();
            return;
        }

        if (amount < 0) {
            System.out.println("\nAmount must be positive number");
            return;
        }

        if (currentAccount.getBalance() < amount) {
            System.out.println("\nYou don't have sufficient balance\n");
            IOUtil.pressEnterKey();
            return;
        }

        currentAccount.setBalance(currentAccount.getBalance() - amount);
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
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == senderAN)
                sender = accounts.get(i);

            if (accounts.get(i).getAccountNumber() == receiverAN)
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
        IOUtil.pressEnterKey();
    }

    public void showAllAccounts() {
        System.out.println("-------All Accounts--------");
        for (int i = 0; i < accounts.size(); i++) {
            Account ac = accounts.get(i);
            for (int j = 0; j < customers.size(); j++) {
                if (ac.getUserId() == customers.get(j).userId) {

                    System.out.print("| " +
                            ac.getAccountNumber() + "  " +
                            customers.get(j).name + "  " +
                            customers.get(j).address + "  " + ac.getBalance() + " |\n");
                    break;
                }
            }

        }
        System.out.println("--------------------------");
        IOUtil.pressEnterKey();

    }

    public void showMenu() {
        System.out.println("\n\n-------------Welcome to Baper Bank------------------");
        System.out.println("Balance: " + this.currentAccount.getBalance());
        System.out.println("\n1. Create account");
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

        System.out.println("------------------------------------\n");

    }

    public void checkBalance() {

        for (int i = 0; i < accounts.size(); i++) {
            if (currentAccount.getAccountNumber() == accounts.get(i).getAccountNumber()) {
                System.out.print("Balance: " + accounts.get(i).getBalance() + "\n");
            }
        }
        IOUtil.pressEnterKey();
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Login as---------");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("3. Super Admin");
        System.out.println("\nPlease enter to perform: ");

        int que = sc.nextInt();
        if (!(que > 0 && que < 3)) {
            System.out.println("\nPlease enter valid number: ");
            this.login();
        }

        System.out.println("\nEnter email: ");
        String email = sc.next();
        System.out.println("\nEnter password: ");
        String password = sc.next();

        if (que == 1) {
            User customer = null;
            int index = 0;

            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).email.equals(email)) {
                    customer = customers.get(i);
                    index = i;
                }
            }

            if (customer == null) {
                System.out.println("Invalid email!\n");
                return false;
            }
            if (!customer.password.equals(password)) {
                System.out.println("Invalid password!\n");
                return false;
            }

            this.currentUser = customer;
            this.currentUserRole = "CUSTOMER";
            this.currentAccount = accounts.get(index);
            return true;
        }
        if (que == 2) {
            User admin = null;

            for (User ad : admins) {
                if (ad.email == email) {
                    admin = ad;
                }
            }

            if (admin == null) {
                System.out.println("Invalid email!\n");
                return false;
            }
            if (admin.password != password) {
                System.out.println("Invalid password!\n");
                return false;
            }

            this.currentUser = admin;
            this.currentUserRole = "ADMIN";
            IOUtil.pressEnterKey();
            return true;
        }
        return false;
    }
}
