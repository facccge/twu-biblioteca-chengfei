package com.twu.biblioteca;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    private static Movie movie1 = new Movie("0001", "Movie1", "2001", "director1", "10");
    private static Movie movie2 = new Movie("0002", "Movie2", "2002", "director2", "unrated");

    @After
    public void restoreBookList() {
        Movie.initializeMovieList();
    }

    @Test
    public void listTest() {
        List<Movie> expected = new ArrayList();
        expected.add(movie1);
        expected.add(movie2);
        List actual = Movie.list();
        assertEquals(expected, actual);
    }

    @Test
    public void checkOutTest() {
        assertEquals(true, Movie.checkOut("0001"));
        assertEquals(false, Movie.checkOut("0001"));
        assertEquals(false, Movie.checkOut("0003"));
    }

    @Test
    public void returnMovieTest() {
        assertEquals(true, Movie.checkOut("0001"));
        assertEquals(false, Movie.returnMovie("0002"));
        assertEquals(true, Movie.returnMovie("0001"));
    }
}
