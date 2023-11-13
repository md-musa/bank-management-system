package com.baper_bank;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();

        while (true) {
            for (int clear = 0; clear < 1000; clear++) {
                System.out.println("\b");
            }
            bank.showMenu();
        }
    }
}