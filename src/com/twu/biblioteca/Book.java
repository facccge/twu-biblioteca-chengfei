package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    public static List<Book> bookList=new ArrayList<Book>();
    static {
        bookList.add(new Book("Book1","Author1","2001"));
        bookList.add(new Book("Book2","Author2","2002"));
    }
    public static List<Book> list(){
        return bookList;
    }

    private String title;
    private String author;
    private String publicationYear;

    public Book(String title, String author, String publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }
}
