package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    public static List<Book> bookList=new ArrayList<Book>();
    static {
        bookList.add(new Book("Book1"));
        bookList.add(new Book("Book2"));
    }
    public static List<Book> list(){
        return bookList;
    }

    private String title;

    public Book(String title){
        this.title = title;
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
        return ""+title;
    }

    public String getTitle() {
        return title;
    }
}
