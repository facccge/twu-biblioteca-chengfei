package com.twu.biblioteca.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {
    private String id;
    private String name;
    private String year;
    private String director;
    private String movieRating;
    private String checkedOutUserName;

    public static List<Movie> movieList;

    static {
        initializeMovieList();
    }

    public static void initializeMovieList() {
        movieList = new ArrayList();
        movieList.add(new Movie("0001", "Movie1", "2001", "director1", "10"));
        movieList.add(new Movie("0002", "Movie2", "2002", "director2", "unrated"));
    }

    public Movie(String id, String name, String year, String director, String movieRating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
        this.checkedOutUserName = "";
    }

    public static boolean checkOut(String checkOutId, String userName) {
        boolean isSuccess = false;
        for (Movie movie : movieList) {
            if (movie.id.equals(checkOutId) && movie.checkedOutUserName.equals("")) {
                movie.checkedOutUserName = userName;
                isSuccess = true;
                break;
            }
        }
        return isSuccess;
    }

    public static boolean returnMovie(String returnId, String userName) {
        boolean isSuccess = false;
        for (Movie movie : movieList) {
            if (movie.id.equals(returnId) && movie.checkedOutUserName.equals(userName)) {
                movie.checkedOutUserName = "";
                isSuccess = true;
                break;
            }
        }
        return isSuccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id +
                " | " + name +
                " | " + year +
                " | " + director +
                " | " + movieRating;
    }

    public static List<Movie> list() {
        List availableMovieList = new ArrayList();
        for (Movie movie : movieList) {
            if (movie.checkedOutUserName.equals(""))
                availableMovieList.add(movie);
        }
        return availableMovieList;
    }

    public static List<Movie> listCheckedOut(String userName) {
        List checkedOutMovieList = new ArrayList();
        for (Movie movie : movieList) {
            if (movie.checkedOutUserName.equals(userName))
                checkedOutMovieList.add(movie);
        }
        return checkedOutMovieList;
    }
}
