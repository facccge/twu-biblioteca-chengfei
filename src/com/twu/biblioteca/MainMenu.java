package com.twu.biblioteca;

import com.twu.biblioteca.Model.Book;
import com.twu.biblioteca.Model.Movie;
import com.twu.biblioteca.Model.Users;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "2. Check out book\n" +
            "3. Return book\n" +
            "4. List of movies\n" +
            "5. Check out movie\n" +
            "6. Return book\n" +
            "7. List checked out books\n" +
            "8. List checked out movies\n" +
            "q. Quit\n" +
            "Please select an option.";
    private static String logInMessage = "Please input username and password to log in.";
    private static String logInSuccessMessage = "Log in successfully.";
    private static String logInFailedMessage = "Log in filed.";
    private static String loggedInUser = null;

    private Scanner scanner = new Scanner(System.in);


    public boolean main() {
        boolean isRunning = true;
        while (loggedInUser == null) {
            Utils.printMessage(logInMessage);
            String userName = scanner.nextLine();
            String password = scanner.nextLine();
            if (Users.login(userName, password)) {
                loggedInUser = userName;
                Utils.printMessage(logInSuccessMessage);
            } else {
                Utils.printMessage(logInFailedMessage);
            }
        }

        Utils.printMessage(Users.listUserInfo(loggedInUser));
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
            case "7":
                listCheckedOutBooks();
                break;
            case "8":
                listCheckedOutMovies();
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
        if (Book.checkOut(id, loggedInUser)) {
            Utils.printMessage("Thank you! Enjoy the book.");
            return;
        }
        Utils.printMessage("Sorry, that book is not available.");
    }

    public void returnBook() {
        Utils.printMessage("Please input id of the book which you want to return.");
        String id = scanner.nextLine();
        if (Book.returnBook(id, loggedInUser)) {
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
        if (Movie.checkOut(id, loggedInUser)) {
            Utils.printMessage("Thank you! Enjoy the movie.");
            return;
        }
        Utils.printMessage("Sorry, that movie is not available.");
    }

    public void returnMovie() {
        Utils.printMessage("Please input id of the movie which you want to return.");
        String id = scanner.nextLine();
        if (Movie.returnMovie(id, loggedInUser)) {
            Utils.printMessage("Thank you for returning the movie.");
            return;
        }
        Utils.printMessage("That is not a valid movie to return.");
    }

    public void listCheckedOutBooks() {
        List<Book> bookList = Book.listCheckedOut(loggedInUser);
        String bookListMessage = "************************************************\n" +
                "Checked out books:\n";
        for (Book book : bookList) {
            bookListMessage += book.toString() + "\n";
        }
        bookListMessage += "************************************************";
        Utils.printMessage(bookListMessage);
    }

    public void listCheckedOutMovies() {
        List<Movie> movieList = Movie.listCheckedOut(loggedInUser);
        String movieListMessage = "************************************************\n" +
                "Checked out movies:\n";
        for (Movie movie : movieList) {
            movieListMessage += movie.toString() + "\n";
        }
        movieListMessage += "************************************************";
        Utils.printMessage(movieListMessage);
    }
}
