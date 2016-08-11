package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Library {
    private List<Book> booksInStock;
    private List<Book> booksCheckedOut;
    private PrintStream printStream;

    public Library(List<Book> booksInStock, List<Book> booksCheckedOut, PrintStream printStream) {
        this.booksCheckedOut = booksCheckedOut;
        this.booksInStock = booksInStock;
        this.printStream = printStream;
    }

    public void displayBooks() {
        for (Book book : booksInStock) {
            printStream.println(book.getDetails());
        }
    }

    public void displayBooksWithNumbers() {
        for (int i=1; i<= booksInStock.size(); i++) {
            printStream.print(i + ". ");
            printStream.println(booksInStock.get(i - 1).getDetails());
        }
    }

    public boolean checkoutBook(int bookNumber) {
        int index = bookNumber - 1;
        if (index >= 0 && index < booksInStock.size()) {
            Book book = booksInStock.remove(index);
            booksCheckedOut.add(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(int bookNumber) {
        int index = bookNumber - 1;
        if (index >= 0 && index < booksCheckedOut.size()) {
            Book book = booksCheckedOut.remove(index);

            booksInStock.add(book);
            return true;
        }
        return false;
    }

    public void displayCheckedOutBooksWithNumbers() {
        for (int i=1; i<= booksCheckedOut.size(); i++) {
            printStream.print(i + ". ");
            printStream.println(booksCheckedOut.get(i - 1).getDetails());
        }
    }
}
