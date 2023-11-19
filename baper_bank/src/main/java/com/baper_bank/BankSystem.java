package com.baper_bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class BankSystem {
    static User currentUser;
    static Account currentAccount;
    static String currentUserRole;

    static List<User> customers = new ArrayList<User>();
    static List<User> admins = new ArrayList<User>();
    static List<Account> accounts = new ArrayList<Account>();
    static List<Transaction> transactions = new ArrayList<Transaction>();

    public void showCustomerMenu() {
        System.out.println("\n\n-------------Welcome to Baper Bank------------------");
        System.out.println("Balance: " + this.currentAccount.getBalance() + "        Role: Customer");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Send money");
        System.out.println("4. Transaction History");
        System.out.println("5. Log out");
        System.out.println("Please enter: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        CustomerController cc = new CustomerController();

        if (choice == 1)
            cc.withdraw();
        else if (choice == 2)
            cc.deposit();
        else if (choice == 3)
            cc.sendMoney();
        else if (choice == 4)
            cc.showTransactionHistory();
        else if (choice == 5)
            this.logOut();
        else {
            System.out.println("\nPlease enter valid number...\n");
        }

        System.out.println("------------------------------------\n");

    }

    public void showAdminMenu() {
        System.out.println("\n\n-------------Welcome to Baper Bank------------------");
        System.out.println("Role: Admin\n");
        System.out.println("1. Create account");
        System.out.println("2. Show all accounts");
        System.out.println("3. All transaction history");
        System.out.println("4. Log out");
        System.out.println("Please enter: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        AdminController adc = new AdminController();

        if (choice == 1)
            adc.addAccount();
        else if (choice == 2)
            adc.showAllAccounts();
        else if (choice == 3)
            adc.showAllTransactionHistory();
        else if (choice == 4)
            this.logOut();
        else {
            System.out.println("\nPlease enter valid number...\n");
        }

        System.out.println("------------------------------------\n");

    }

    public void logOut() {
        currentUser = null;
        currentAccount = null;
        currentUserRole = null;
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Login as---------");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("3. Super Admin");
        System.out.println("\nPlease enter to perform: ");

        int choice = sc.nextInt();
        if (!(choice > 0 && choice < 3)) {
            System.out.println("\nPlease enter valid number: ");
            this.login();
        }

        System.out.println("\nEnter email: ");
        String email = sc.next();
        System.out.println("\nEnter password: ");
        String password = sc.next();

        if (choice == 1) {
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
        if (choice == 2) {
            User admin = null;

            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).email.equals(email)) {
                    admin = customers.get(i);
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
