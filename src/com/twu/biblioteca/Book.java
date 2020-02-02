package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    public static List<Book> bookList;

    static {
        initializeBookList();
    }

    public static void initializeBookList() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("0001", "Book1", "Author1", "2001"));
        bookList.add(new Book("0002", "Book2", "Author2", "2002"));
    }

    public static List<Book> list() {
        List availableBookList = new ArrayList();
        for (Book book : bookList) {
            if (book.isCheckOut == false)
                availableBookList.add(book);
        }
        return availableBookList;
    }

    public static boolean checkOut(String checkOutId) {
        boolean isSuccess = false;
        for (Book book : bookList) {
            if (book.id.equals(checkOutId) && (book.isCheckOut != true)) {
                book.isCheckOut = true;
                isSuccess = true;
                break;
            }
        }
        return isSuccess;
    }

    private String id;
    private String title;
    private String author;
    private String publicationYear;
    private boolean isCheckOut;

    public Book(String id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isCheckOut = false;
    }

    public static boolean returnBook(String returnId) {
        boolean isSuccess = false;
        for (Book book : bookList) {
            if (book.id.equals(returnId) && (book.isCheckOut == true)) {
                book.isCheckOut = false;
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
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + publicationYear;
    }
}
