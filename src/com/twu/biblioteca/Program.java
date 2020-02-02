package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Program {
    Scanner scanner = new Scanner(System.in);


    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "2. Check out book\n" +
            "q. Quit\n" +
            "Please select an option.";

    public boolean main() {
        boolean isRunning = true;
        Utils.printMessage(mainMenu);

        String option = scanner.nextLine();
        switch (option) {
            case "1":
                listOfBooks();
                break;
            case "2":
                checkOutBook();
                break;
            case "q":
                isRunning = false;
                break;
            default:
                Utils.printMessage("Please select a valid option!");
                break;
        }
        return isRunning;
    }

    public void checkOutBook() {
        Utils.printMessage("Please input id of the book which you want to check out.");
        String id = scanner.nextLine();
        Book.checkOut(id);
    }

    public void listOfBooks() {
        List<Book> bookList = Book.list();
        String bookListMessage = "************************************************\n" +
                "Books:\n";
        for (Book book : bookList) {
            bookListMessage += book.toString() + "\n";
        }
        bookListMessage += "************************************************";
        Utils.printMessage(bookListMessage);
    }
}
