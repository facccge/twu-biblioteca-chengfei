package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
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
    public void printBookListTest() {
        Program program = new Program();
        String actual = program.buildBookList(bookList);
        assertEquals("Books:\n" +
                "Book1 | Author1 | 2001\n" +
                "Book2 | Author2 | 2002\n", actual);
    }

    @Test
    public void mainTest() {
        Program program = new Program();
        program.main();
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n" +
                "Books:\n" +
                "Book1 | Author1 | 2001\n" +
                "Book2 | Author2 | 2002\n" +
                "\n", outContent.toString());
    }
}
