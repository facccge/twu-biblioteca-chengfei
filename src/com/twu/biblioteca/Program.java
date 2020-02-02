package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Program {
    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "Please press the number of the option you want to select.";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void main() {
        printMessage(welcomeMessage);
        printMessage(mainMenu);
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                listOfBooks();
                break;
            default:
                printMessage("error");
                break;
        }

    }

    public void listOfBooks() {
        List<Book> bookList = Book.list();
        String bookListMessage = "Books:\n";
        for (Book book : bookList) {
            bookListMessage += book.toString() + "\n";
        }
        printMessage(bookListMessage.substring(0, bookListMessage.length() - 1));
    }
}
