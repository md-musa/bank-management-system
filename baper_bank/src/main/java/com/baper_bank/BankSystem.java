package com.baper_bank;

import java.beans.Customizer;
import java.io.IOException;
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

    public void showAdminMenu() {

    }

    public void showCustomerMenu() {
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
            showAdminMenu();
        else if (serial == 6)
            checkBalance();

        System.out.println("------------------------------------\n");

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
