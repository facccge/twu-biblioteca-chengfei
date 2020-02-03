package com.twu.biblioteca.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    public static List<Book> bookList;

    static {
        initializeBookList();
    }

    private String id;
    private String title;
    private String author;
    private String publicationYear;
    private String checkedOutUserName;

    public Book(String id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.checkedOutUserName = "";
    }

    public static void initializeBookList() {
        bookList = new ArrayList();
        bookList.add(new Book("0001", "Book1", "Author1", "2001"));
        bookList.add(new Book("0002", "Book2", "Author2", "2002"));
    }

    public static List<Book> list() {
        List availableBookList = new ArrayList();
        for (Book book : bookList) {
            if (book.checkedOutUserName.equals(""))
                availableBookList.add(book);
        }
        return availableBookList;
    }

    public static List<Book> listCheckedOut(String userName) {
        List checkedOutBookList = new ArrayList();
        for (Book book : bookList) {
            if (book.checkedOutUserName.equals(userName))
                checkedOutBookList.add(book);
        }
        return checkedOutBookList;
    }

    public static boolean checkOut(String checkOutId, String userName) {
        boolean isSuccess = false;
        for (Book book : bookList) {
            if (book.id.equals(checkOutId) && book.checkedOutUserName.equals("")) {
                book.checkedOutUserName = userName;
                isSuccess = true;
                break;
            }
        }
        return isSuccess;
    }

    public static boolean returnBook(String returnId, String userName) {
        boolean isSuccess = false;
        for (Book book : bookList) {
            if (book.id.equals(returnId) && book.checkedOutUserName.equals(userName)) {
                book.checkedOutUserName = null;
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
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + publicationYear;
    }
}
