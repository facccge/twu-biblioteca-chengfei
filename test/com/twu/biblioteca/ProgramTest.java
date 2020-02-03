package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProgramTest {
    private static List bookList = new ArrayList<>();
    private static String mainMenu = "Main menu:\n" +
            "1. List of books\n" +
            "2. Check out book\n" +
            "3. Return book\n" +
            "4. List of movies\n" +
            "5. Check out movie\n" +
            "6. Return book\n" +
            "q. Quit\n" +
            "Please select an option.\n";
    private static String logInInput = "user1\n123456\nq";
    private static String logInMessage = "Please input username and password to log in.\n" +
            "Log in successfully.\n";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeClass
    public static void setUpBookList() {
        bookList.add(new Book("0001", "Book1", "Author1", "2001"));
        bookList.add(new Book("0002", "Book2", "Author2", "2002"));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        System.setIn(new ByteArrayInputStream(logInInput.getBytes()));
        Program program = new Program();
        program.main();
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        Book.initializeBookList();
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    public static void mockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void mainTestWhenListBooks() {
        mockInput("1");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "************************************************\n" +
                        "Books:\n" +
                        "0001 | Book1 | Author1 | 2001\n" +
                        "0002 | Book2 | Author2 | 2002\n" +
                        "************************************************\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutBook() {
        mockInput("2\n0001");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to check out.\n" +
                        "Thank you! Enjoy the book.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutBookFalse() {
        mockInput("2\n0003");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to check out.\n" +
                        "Sorry, that book is not available.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnBook() {
        Book.checkOut("0001");
        mockInput("3\n0001");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to return.\n" +
                        "Thank you for returning the book.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnBookFalse() {
        mockInput("3\n0001");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to return.\n" +
                        "That is not a valid book to return.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenInputError() {
        mockInput("something");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please select a valid option!\n", outContent.toString());
    }

    @Test
    public void mainTestWhenSelectQuit() {
        mockInput("q");
        Program program = new Program();

        assertEquals(false, program.main());
        assertEquals(
                mainMenu, outContent.toString());
    }

    @Test
    public void mainTestWhenListMovies() {
        mockInput("4");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "************************************************\n" +
                        "Movies:\n" +
                        "0001 | Movie1 | 2001 | director1 | 10\n" +
                        "0002 | Movie2 | 2002 | director2 | unrated\n" +
                        "************************************************\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutMovie() {
        mockInput("5\n0001");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the movie which you want to check out.\n" +
                        "Thank you! Enjoy the movie.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenCheckOutMovieFalse() {
        mockInput("5\n0003");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the movie which you want to check out.\n" +
                        "Sorry, that movie is not available.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnMovie() {
        Book.checkOut("0001");
        mockInput("6\n0001");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the movie which you want to return.\n" +
                        "Thank you for returning the movie.\n", outContent.toString());
    }

    @Test
    public void mainTestWhenReturnMovieFalse() {
        mockInput("6\n0001");
        Program program = new Program();

        assertEquals(true, program.main());
        assertEquals(
                mainMenu +
                        "Please input id of the movie which you want to return.\n" +
                        "That is not a valid movie to return.\n", outContent.toString());
    }
}
