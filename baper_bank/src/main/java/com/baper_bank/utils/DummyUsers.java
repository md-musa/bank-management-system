package com.baper_bank.utils;

import com.baper_bank.Account;
import com.baper_bank.BankSystem;
import com.baper_bank.User;

public class DummyUsers {
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
}
