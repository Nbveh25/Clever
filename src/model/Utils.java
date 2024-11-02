package model;

import jakarta.servlet.RequestDispatcher;

import java.util.Random;

public class Utils {
    public static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
