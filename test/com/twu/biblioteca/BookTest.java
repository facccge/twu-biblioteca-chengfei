package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookTest {
    private static Book book1 = new Book("0001", "Book1", "Author1", "2001");
    private static Book book2 = new Book("0002", "Book2", "Author2", "2002");

    @After
    public void restoreBookList() {
        Book.initializeBookList();
    }

    @Test
    public void listTest() {
        List expected = new ArrayList();
        expected.add(book1);
        expected.add(book2);
        List actual = Book.list();
        assertEquals(expected, actual);

        Book.checkOut("0001");
        expected = new ArrayList();
        expected.add(book2);
        List actualAfterCheckOut = Book.list();
        assertEquals(expected, actualAfterCheckOut);
    }

    @Test
    public void checkOutTest() {
        assertEquals(true, Book.checkOut("0001"));
        assertEquals(false, Book.checkOut("0001"));
        assertEquals(false, Book.checkOut("0003"));
    }

    @Test
    public void returnBookTest() {
        assertEquals(true, Book.checkOut("0001"));
        assertEquals(false, Book.returnBook("0002"));
        assertEquals(true, Book.returnBook("0001"));
    }
}
