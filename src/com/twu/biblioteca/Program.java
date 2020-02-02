package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Program {
    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "Please press the number of the option you want to select.";

    public void main() {
        Utils.printMessage(mainMenu);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                listOfBooks();
                break;
            default:
                Utils.printMessage("Please select a valid option!");
                break;
        }
    }

    public void listOfBooks() {
        List<Book> bookList = Book.list();
        String bookListMessage = "Books:\n";
        for (Book book : bookList) {
            bookListMessage += book.toString() + "\n";
        }
        Utils.printMessage(bookListMessage.substring(0, bookListMessage.length() - 1));
    }
}
