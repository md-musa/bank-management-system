package com.baper_bank.utils;

import com.baper_bank.Account;
import com.baper_bank.BankSystem;
import com.baper_bank.User;

public class DummyData {
    public static void addDummyAccounts() {
        User ct = new User();
        ct.userId = 1;
        ct.name = "Md. Musa";
        ct.email = "musa@gmail.com";
        ct.password = "123456";
        ct.address = "Noakhali";
        ct.phoneNumber = "01754434344";
        BankSystem.customers.add(ct);

        Account ac = new Account();
        ac.setUserId(ct.userId);
        ac.setAccountNumber(1001);
        ac.setBalance(1000);
        BankSystem.accounts.add(ac);

        User ct2 = new User();
        ct2.userId = 2;
        ct2.name = "Robin";
        ct2.email = "robin@gmail.com";
        ct2.password = "123456";
        ct2.address = "Pabna";
        ct2.phoneNumber = "0144897348";
        BankSystem.customers.add(ct2);

        Account ac2 = new Account();
        ac2.setUserId(ct2.userId);
        ac2.setAccountNumber(1002);
        ac2.setBalance(10000);
        BankSystem.accounts.add(ac2);

        User ct3 = new User();
        ct3.userId = 3;
        ct3.name = "Asif";
        ct3.email = "asif@gmail.com";
        ct3.password = "123456";
        ct3.address = "Cumilla";
        ct3.phoneNumber = "0144884933";
        BankSystem.customers.add(ct3);

        Account ac3 = new Account();
        ac3.setUserId(ct3.userId);
        ac3.setAccountNumber(1003);
        ac3.setBalance(100000);
        BankSystem.accounts.add(ac3);

        User ct4 = new User();
        ct4.userId = 4;
        ct4.name = "Zerin";
        ct4.email = "zerin@gmail.com";
        ct4.password = "123456";
        ct4.address = "Gazipur";
        ct4.phoneNumber = "0144748374";
        BankSystem.customers.add(ct4);

        Account ac4 = new Account();
        ac4.setUserId(ct4.userId);
        ac4.setAccountNumber(1004);
        ac4.setBalance(1000000);
        BankSystem.accounts.add(ac4);

        // for (int i = 1003; i <= 1010; i++) {
        // User t = new User();
        // t.userId = i;
        // t.name = "User" + (i - 1000);
        // t.email = "user" + i + "@gmail.com";
        // t.password = "password" + i;
        // t.address = "Address" + i;
        // t.phoneNumber = "0175" + (i * 1111111);

        // bank.customers.add(t);

        // Account c = new Account();
        // c.setUserId(t.userId);
        // c.setAccountNumber(i);
        // c.setBalance(1000 * i);

        // bank.accounts.add(c);
        // }

    }

    public static void addDummyAdmin() {
        User admin = new User();
        admin.userId = 1;
        admin.name = "Admin";
        admin.name = "admin";
        admin.email = "admin@gmail.com";
        admin.address = "Dhaka";
        admin.phoneNumber = "01974834737";
        admin.password = "123456";
        BankSystem.admins.add(admin);
    }
}
