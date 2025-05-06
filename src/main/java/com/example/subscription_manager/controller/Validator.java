/** Clasa pentru Validare
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */

package com.example.subscription_manager.controller;

public class Validator {
    public static boolean ValidareParola(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        int upperCount = 0;
        int digitCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upperCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        return ContineCaracterSpecial(password) && upperCount >= 1 && digitCount >= 3;
    }

    public static boolean ValidareEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)\\.com$";
        return email != null && email.matches(regex);
    }

    public static boolean ContineCaracterSpecial(String s) {
        return s != null && s.matches(".*[^A-Za-z0-9 ].*");
    }
}
