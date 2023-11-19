package com.baper_bank;

import java.util.Scanner;

import com.baper_bank.utils.IOUtil;

public class AdminController {

    private int addUser() {
        int lastCustomerId = 1;
        if (BankSystem.customers.size() > 0)
            lastCustomerId = BankSystem.customers.get(BankSystem.customers.size() - 1).userId;

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

        BankSystem.customers.add(newUser);
        return newUser.userId;
    }

    public void addAccount() {
        int userId = this.addUser();
        int lastAN = 1;
        if (BankSystem.accounts.size() > 0)
            lastAN = BankSystem.accounts.get(BankSystem.accounts.size() - 1).getAccountNumber() + 1;

        Account ac = new Account();
        ac.setAccountNumber(lastAN);
        ac.setUserId(userId);
        BankSystem.accounts.add(ac);

        System.out.println("Account created successfully\n");
        System.out.println("A/N: " + lastAN + "\n\n");
    }

    public void showAllAccounts() {

        System.out.println("-------All Accounts--------");
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            Account ac = BankSystem.accounts.get(i);
            for (int j = 0; j < BankSystem.customers.size(); j++) {
                if (ac.getUserId() == BankSystem.customers.get(j).userId) {

                    System.out.print("| " +
                            ac.getAccountNumber() + "  " +
                            BankSystem.customers.get(j).name + "  " +
                            BankSystem.customers.get(j).address + "  " + ac.getBalance() + " |\n");
                    break;
                }
            }

        }
        System.out.println("--------------------------");
        IOUtil.pressEnterKey();
    }

    public void showAllTransactionHistory() {
    }
}
