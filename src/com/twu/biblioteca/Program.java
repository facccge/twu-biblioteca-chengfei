package com.twu.biblioteca;

public class Program {
    private static String welcomMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void main() {
        printMessage(welcomMessage);
    }
}
