package com.baper_bank;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();

        User admin = new User();
        admin.userId = 1;
        admin.name = "Admin";
        admin.name = "admin";
        admin.email = "admin@gmail.com";
        admin.address = "Dhaka";
        admin.phoneNumber = "01974834737";
        admin.password = "123456";
        BankSystem.admins.add(admin);
        // ct.userId = 10;
        // ct.email = "musa1@gmail.com";
        // ct.password = "123456";
        // ct.address = "Dhaka";
        // ct.phoneNumber = "01754434344";
        // bank.customers.add(ct);

        User ct = new User();
        ct.userId = 1;
        ct.name = "Md. Musa";
        ct.email = "musa1@gmail.com";
        ct.password = "123456";
        ct.address = "Dhaka";
        ct.phoneNumber = "01754434344";
        BankSystem.customers.add(ct);

        Account ac = new Account();
        ac.setUserId(ct.userId);
        ac.setAccountNumber(1000);
        ac.setBalance(1000);
        BankSystem.accounts.add(ac);

        User ct2 = new User();
        ct2.userId = 2;
        ct2.name = "Md. Musa2";
        ct2.email = "musa2@gmail.com";
        ct2.password = "123456";
        ct2.address = "Kustia";
        ct2.phoneNumber = "01489374832";
        BankSystem.customers.add(ct2);

        Account ac2 = new Account();
        ac2.setUserId(ct2.userId);
        ac2.setAccountNumber(1001);
        ac2.setBalance(1000);
        BankSystem.accounts.add(ac2);

        for (int i = 1003; i <= 1010; i++) {
            User t = new User();
            t.userId = i;
            t.name = "User" + (i - 1000);
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
            if (BankSystem.currentUserRole == null)
                bank.login();
            else if (BankSystem.currentUserRole.equals("CUSTOMER"))
                bank.showCustomerMenu();
            else if (BankSystem.currentUserRole.equals("ADMIN"))
                bank.showAdminMenu();

        }
    }
}