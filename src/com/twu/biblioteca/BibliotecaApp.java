package com.twu.biblioteca;


public class BibliotecaApp {
    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        Utils.printMessage(welcomeMessage);
        MainMenu mainMenu = new MainMenu();
        boolean isRunning = true;
        while (isRunning) {
            isRunning = mainMenu.main();
        }
    }
}
