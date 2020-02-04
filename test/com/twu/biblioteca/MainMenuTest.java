package com.twu.biblioteca;


import com.twu.biblioteca.Model.Book;
import com.twu.biblioteca.Model.Movie;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainMenuTest {
    private static List bookList = new ArrayList<>();
    private static String userInfomation = "abc-1234 | 小明 | xiaoming@123.com | 13012345678\n";
    private static String menuMesage = "Main menu:\n" +
            "1. List of books\n" +
            "2. Check out book\n" +
            "3. Return book\n" +
            "4. List of movies\n" +
            "5. Check out movie\n" +
            "6. Return book\n" +
            "7. List checked out books\n" +
            "8. List checked out movies\n" +
            "q. Quit\n" +
            "Please select an option.\n";
    private static String logInInput = "abc-1234\n123456\nq";
    private static String userName = "abc-1234";
    private static String logInMessage = "Please input username and password to log in.\n" +
            "Log in successfully.\n";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeClass
    public static void setUpBookList() {
        bookList.add(new Book("0001", "Book1", "Author1", "2001"));
        bookList.add(new Book("0002", "Book2", "Author2", "2002"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream(logInInput.getBytes()));
        MainMenu mainMenu = new MainMenu();
        mainMenu.main();
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        Book.initializeBookList();
        Movie.initializeMovieList();
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    public static void mockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void mainTestWhenListBooks() {
        mockInput("1");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "************************************************\n" +
                        "Books:\n" +
                        "0001 | Book1 | Author1 | 2001\n" +
                        "0002 | Book2 | Author2 | 2002\n" +
                        "************************************************\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutBook() {
        mockInput("2\n0001");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the book which you want to check out.\n" +
                        "Thank you! Enjoy the book.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutBookFalse() {
        mockInput("2\n0003");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the book which you want to check out.\n" +
                        "Sorry, that book is not available.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnBook() {
        Book.checkOut("0001", userName);
        mockInput("3\n0001");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the book which you want to return.\n" +
                        "Thank you for returning the book.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnBookFalse() {
        mockInput("3\n0001");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the book which you want to return.\n" +
                        "That is not a valid book to return.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenInputError() {
        mockInput("something");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please select a valid option!\n", outContent.toString());
    }

    @Test
    public void mainTestWhenSelectQuit() {
        mockInput("q");
        MainMenu mainMenu = new MainMenu();

        assertEquals(false, mainMenu.main());
        Assert.assertEquals(
                userInfomation + menuMesage, outContent.toString());
    }

    @Test
    public void mainTestWhenListMovies() {
        mockInput("4");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "************************************************\n" +
                        "Movies:\n" +
                        "0001 | Movie1 | 2001 | director1 | 10\n" +
                        "0002 | Movie2 | 2002 | director2 | unrated\n" +
                        "************************************************\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutMovie() {
        mockInput("5\n0001");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the movie which you want to check out.\n" +
                        "Thank you! Enjoy the movie.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutMovieFalse() {
        mockInput("5\n0003");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the movie which you want to check out.\n" +
                        "Sorry, that movie is not available.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnMovie() {
        Movie.checkOut("0001", userName);
        mockInput("6\n0001");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the movie which you want to return.\n" +
                        "Thank you for returning the movie.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnMovieFalse() {
        mockInput("6\n0001");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "Please input id of the movie which you want to return.\n" +
                        "That is not a valid movie to return.\n", outContent.toString());
    }

    @Test
    public void mainTestWithListCheckedOutBook() {
        Book.checkOut("0001", userName);

        mockInput("7");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "************************************************\n" +
                        "Checked out books:\n" +
                        "0001 | Book1 | Author1 | 2001\n" +
                        "************************************************\n", outContent.toString());
    }

    @Test
    public void mainTestWithListCheckedOutMovie() {
        Movie.checkOut("0001", userName);

        mockInput("8");
        MainMenu mainMenu = new MainMenu();

        assertEquals(true, mainMenu.main());
        assertEquals(
                userInfomation + menuMesage +
                        "************************************************\n" +
                        "Checked out movies:\n" +
                        "0001 | Movie1 | 2001 | director1 | 10\n" +
                        "************************************************\n", outContent.toString());
    }
}
