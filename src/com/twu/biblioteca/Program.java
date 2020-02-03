package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Program {
    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "2. Check out book\n" +
            "3. Return book\n" +
            "4. List of movies\n" +
            "5. Check out movie\n" +
            "6. Return book\n" +
            "q. Quit\n" +
            "Please select an option.";
    Scanner scanner = new Scanner(System.in);

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
            case "4":
                listOfMovies();
                break;
            case "5":
                checkOutMovie();
                break;
            case "6":
                returnMovie();
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
        if (Book.returnBook(id)) {
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

    public void listOfMovies() {
        List<Movie> movieList = Movie.list();
        String movieListMessage = "************************************************\n" +
                "Movies:\n";
        for (Movie movie : movieList) {
            movieListMessage += movie.toString() + "\n";
        }
        movieListMessage += "************************************************";
        Utils.printMessage(movieListMessage);
    }

    public void checkOutMovie() {
        Utils.printMessage("Please input id of the movie which you want to check out.");
        String id = scanner.nextLine();
        if (Movie.checkOut(id)) {
            Utils.printMessage("Thank you! Enjoy the movie.");
            return;
        }
        Utils.printMessage("Sorry, that movie is not available.");
    }

    public void returnMovie() {
        Utils.printMessage("Please input id of the movie which you want to return.");
        String id = scanner.nextLine();
        if (Movie.returnMovie(id)) {
            Utils.printMessage("Thank you for returning the movie.");
            return;
        }
        Utils.printMessage("That is not a valid movie to return.");
    }
}
