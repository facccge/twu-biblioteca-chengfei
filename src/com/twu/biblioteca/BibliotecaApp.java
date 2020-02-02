package com.twu.biblioteca;


public class BibliotecaApp {
    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        Utils.printMessage(welcomeMessage);
        Program program = new Program();
        boolean isRunning = true;
        while (isRunning) {
            isRunning = program.main();
        }
    }
}
