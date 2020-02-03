package com.twu.biblioteca.Model;

import com.twu.biblioteca.Model.Movie;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    private static Movie movie1 = new Movie("0001", "Movie1", "2001", "director1", "10");
    private static Movie movie2 = new Movie("0002", "Movie2", "2002", "director2", "unrated");
    private static String userName = "user1";

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

        Movie.checkOut("0001", userName);
        expected = new ArrayList();
        expected.add(movie2);
        List actualAfterCheckOut = Movie.list();
        assertEquals(expected, actualAfterCheckOut);
    }

    @Test
    public void checkOutTest() {
        assertEquals(true, Movie.checkOut("0001", userName));
        assertEquals(false, Movie.checkOut("0001", userName));
        assertEquals(false, Movie.checkOut("0003", userName));
    }

    @Test
    public void returnMovieTest() {
        assertEquals(true, Movie.checkOut("0001", userName));
        assertEquals(false, Movie.returnMovie("0002", userName));
        assertEquals(true, Movie.returnMovie("0001", userName));
    }

    @Test
    public void listCheckedOutTest() {
        Movie.checkOut("0001", userName);
        List<Movie> expected = new ArrayList();
        expected.add(movie1);
        List actual = Movie.listCheckedOut(userName);
        assertEquals(expected, actual);
    }
}
