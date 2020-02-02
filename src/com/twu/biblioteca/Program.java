package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Program {
    Scanner scanner = new Scanner(System.in);


    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "2. Check out book\n" +
            "3. Return book\n" +
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
            case "3":
                returnBook();
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
        if (Book.checkOut(id)) {
            Utils.printMessage("Thank you! Enjoy the book.");
            return;
        }
        Utils.printMessage("Sorry, that book is not available.");
    }

    public void returnBook() {
        Utils.printMessage("Please input id of the book which you want to return.");
        String id = scanner.nextLine();
        if(Book.returnBook(id)){
            Utils.printMessage("Thank you for returning the book.");
            return;
        }
        Utils.printMessage("That is not a valid book to return.");
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
