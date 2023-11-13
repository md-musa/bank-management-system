package com.baper_bank.utils;

import java.io.IOException;

public class IOUtil {

    public static void pressEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
