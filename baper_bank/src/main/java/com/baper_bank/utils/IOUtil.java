package com.baper_bank.utils;

import java.io.IOException;

public class IOUtil {

    public static void pressEnterKey() {
        System.out.println("\nPress \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        for (int clear = 0; clear < 300; clear++) {
            System.out.println("\b");
        }
    }
}
