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
    private final PrintStream originalOut = System.out;

    private static List bookList = new ArrayList<>();

    @BeforeClass
    public static void setUpBookList() {
        bookList.add(new Book("Book1", "Author1", "2001"));
        bookList.add(new Book("Book2", "Author2", "2002"));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    public void mainTest() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        Program program = new Program();
        program.main();

        assertEquals(
                "Main menu:\n" +
                        "1. List of books\n" +
                        "Please press the number of the option you want to select.\n" +
                        "Books:\n" +
                        "Book1 | Author1 | 2001\n" +
                        "Book2 | Author2 | 2002\n", outContent.toString());

        System.setIn(sysInBackup);
    }

    @Test
    public void mainTestWithError() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("error input".getBytes());
        System.setIn(in);

        Program program = new Program();
        program.main();

        assertEquals(
                "Main menu:\n" +
                        "1. List of books\n" +
                        "Please press the number of the option you want to select.\n" +
                        "Please select a valid option!\n", outContent.toString());

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
