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
            "q. Quit\n" +
            "Please select an option.\n";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeClass
    public static void setUpBookList() {
        bookList.add(new Book("0001", "Book1", "Author1", "2001"));
        bookList.add(new Book("0002", "Book2", "Author2", "2002"));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        Book.initializeBookList();
    }


    @Test
    public void mainTestWhenSelectList() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(true, program.main());

        assertEquals(
                mainMenu +
                        "************************************************\n" +
                        "Books:\n" +
                        "0001 | Book1 | Author1 | 2001\n" +
                        "0002 | Book2 | Author2 | 2002\n" +
                        "************************************************\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWhenCheckOutBook() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n0001".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(true, program.main());

        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to check out.\n" +
                        "Thank you! Enjoy the book.\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWhenCheckOutBookFalse() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n0003".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(true, program.main());

        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to check out.\n" +
                        "Sorry, that book is not available.\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWhenReturnBook() {
        Book.checkOut("0001");

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3\n0001".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(true, program.main());

        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to return.\n" +
                        "Thank you for returning the book.\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWhenReturnBookFalse() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3\n0001".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(true, program.main());

        assertEquals(
                mainMenu +
                        "Please input id of the book which you want to return.\n" +
                        "That is not a valid book to return.\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWhenInputError() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("error input".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(true, program.main());

        assertEquals(
                mainMenu +
                        "Please select a valid option!\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWhenSelectQuit() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        System.setIn(in);

        Program program = new Program();
        assertEquals(false, program.main());

        assertEquals(
                mainMenu, outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void listOfBooksTest() {
        Program program = new Program();
        program.listOfBooks();
        assertEquals("************************************************\n" +
                "Books:\n" +
                "0001 | Book1 | Author1 | 2001\n" +
                "0002 | Book2 | Author2 | 2002\n" +
                "************************************************\n", outContent.toString());
    }
}
