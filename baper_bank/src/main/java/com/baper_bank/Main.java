package com.baper_bank;

import com.baper_bank.utils.IOUtil;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();

        User ct = new User();
        ct.userId = 1;
        ct.email = "musa@gmail.com";
        ct.password = "123456";
        ct.address = "Dhaka";
        ct.phoneNumber = "01754434344";
        bank.customers.add(ct);

        Account ac = new Account();
        ac.setUserId(ct.userId);
        ac.setAccountNumber(1);
        ac.setBalance(1000);
        bank.accounts.add(ac);

        while (true) {
            // IOUtil.clearConsole();

            if (bank.currentUserRole != null) {
                bank.showMenu();
            } else {
                bank.login();
            }
        }
    }
}