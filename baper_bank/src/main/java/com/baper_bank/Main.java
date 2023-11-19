package com.baper_bank;

import com.baper_bank.utils.IOUtil;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();

        User admin = new User();
        admin.userId = 1;
        admin.name = "admin";
        admin.email = "admin@gmail.com";
        admin.address = "Dhaka";
        admin.phoneNumber = "01974834737";
        admin.password = "123456";
        bank.admins.add(admin);
        // ct.userId = 10;
        // ct.email = "musa1@gmail.com";
        // ct.password = "123456";
        // ct.address = "Dhaka";
        // ct.phoneNumber = "01754434344";
        // bank.customers.add(ct);

        User ct = new User();
        ct.userId = 1;
        ct.email = "musa1@gmail.com";
        ct.password = "123456";
        ct.address = "Dhaka";
        ct.phoneNumber = "01754434344";
        bank.customers.add(ct);

        Account ac = new Account();
        ac.setUserId(ct.userId);
        ac.setAccountNumber(1);
        ac.setBalance(1000);
        bank.accounts.add(ac);

        User ct2 = new User();
        ct2.userId = 2;
        ct2.email = "musa2@gmail.com";
        ct2.password = "123456";
        ct2.address = "Kustia";
        ct2.phoneNumber = "01489374832";
        bank.customers.add(ct2);

        Account ac2 = new Account();
        ac2.setUserId(ct2.userId);
        ac2.setAccountNumber(2);
        ac2.setBalance(1000);
        bank.accounts.add(ac2);

        for (int i = 3; i <= 10; i++) {
            User t = new User();
            t.userId = i;
            t.email = "user" + i + "@gmail.com";
            t.password = "password" + i;
            t.address = "Address" + i;
            t.phoneNumber = "0175" + (i * 1111111);

            bank.customers.add(t);

            Account c = new Account();
            c.setUserId(t.userId);
            c.setAccountNumber(i);
            c.setBalance(1000 * i);

            bank.accounts.add(c);
        }

        while (true) {
            // IOUtil.clearConsole();

            if (bank.currentUserRole != null) {
                bank.showCustomerMenu();
            } else {
                bank.login();
            }
        }
    }
}