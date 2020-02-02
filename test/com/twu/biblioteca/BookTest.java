package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookTest {
    @Test
    public void listTest() {
        List bookList = Book.list();
        List expected = new ArrayList<Book>();
        expected.add(new Book("Book1"));
        expected.add(new Book("Book2"));
        assertEquals(expected, bookList);
    }
}
