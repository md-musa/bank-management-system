package com.baper_bank;

import com.baper_bank.utils.DummyData;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();
        DummyData.addDummyAccounts();
        DummyData.addDummyAdmin();

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("\n\n|------------------------------------------------------|");
            System.out.println("|                   BAPER BANK                         |");
            System.out.println("|------------------------------------------------------|");
            if (BankSystem.currentUserRole == null)
                bank.login();
            else if (BankSystem.currentUserRole.equals("CUSTOMER"))
                bank.showCustomerMenu();
            else if (BankSystem.currentUserRole.equals("ADMIN"))
                bank.showAdminMenu();

        }
    }
}