package com.baper_bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    public static User currentUser;
    public static Account currentAccount;
    public static String currentUserRole;

    public static List<User> customers = new ArrayList<User>();
    public static List<User> admins = new ArrayList<User>();
    public static List<Account> accounts = new ArrayList<Account>();
    public static List<Transaction> transactions = new ArrayList<Transaction>();

    public void showCustomerMenu() {
        System.out.println("| Name: " + currentUser.name +
                "     Balance: " + currentAccount.getBalance()
                + "   Role: Customer  |");
        System.out.println("|------------------------------------------------------|\n");

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

    }

    public void showAdminMenu() {

        System.out.println("|  Name: " + currentUser.name + "          Role: Admin                     |");
        System.out.println("|-------------------------------------------------------|\n");
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

    }

    public void logOut() {
        currentUser = null;
        currentAccount = null;
        currentUserRole = null;
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login as: ");
        System.out.println("1. Customer ");
        System.out.println("2. Admin ");
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

            currentUser = customer;
            currentUserRole = "CUSTOMER";
            currentAccount = accounts.get(index);
            return true;
        }

        else if (choice == 2) {
            User admin = null;

            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).email.equals(email)) {
                    admin = admins.get(i);
                    break;
                }
            }

            if (admin == null) {
                System.out.println("Invalid email!\n");
                return false;
            }
            if (!password.equals(admin.password)) {
                System.out.println("Invalid password!\n");
                return false;
            }

            currentUser = admin;
            currentUserRole = "ADMIN";
            return true;
        }
        return false;
    }
}
