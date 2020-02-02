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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private static List bookList = new ArrayList<>();

    @BeforeClass
    public static void setUpBookList() {
        bookList.add(new Book("Book1", "Author1", "2001"));
        bookList.add(new Book("Book2", "Author2", "2002"));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void printMessageTest() {
        String message = "some message";
        Program program = new Program();
        program.printMessage(message);
        assertEquals("some message\n", outContent.toString());
    }

    @Test
    public void mainTest() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        Program program = new Program();
        program.main();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n" +
                "Main menu:\n" +
                "1. List of books\n" +
                "Please press the number of the option you want to select.\n" +
                "Books:\n" +
                "Book1 | Author1 | 2001\n" +
                "Book2 | Author2 | 2002\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void listOfBooksTest() {
        Program program = new Program();
        program.listOfBooks();
        assertEquals("Books:\n" +
                "Book1 | Author1 | 2001\n" +
                "Book2 | Author2 | 2002\n", outContent.toString());
    }
}
