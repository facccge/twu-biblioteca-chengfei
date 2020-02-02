package com.twu.biblioteca;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookTest {
    private static List bookList = new ArrayList<>();

    @BeforeClass
    public static void setUpBookList() {
        bookList.add(new Book("Book1", "Author1", "2001"));
        bookList.add(new Book("Book2", "Author2", "2002"));
    }

    @Test
    public void listTest() {
        List actual = Book.list();
        assertEquals(bookList, actual);
    }
}
