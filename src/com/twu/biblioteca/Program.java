package com.twu.biblioteca;

import java.util.List;

public class Program {
    private static String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void main() {
        printMessage(welcomeMessage);
        List bookList = Book.list();
        printMessage(buildBookList(bookList));
    }

    public String buildBookList(List<Book> bookList) {
        String bookListMessage ="Books:\n";
        for (Book book : bookList) {
            bookListMessage += book.toString()+"\n";
        }
        return bookListMessage;
    }
}
