package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LibraryTest {

    private PrintStream fakePrintStream;
    private List<Book> stockedBooks;
    private List<Book> checkedOutBooks;
    private Book book1;
    private Book checkedOutBook1;
    private Library myLibrary;


    @Before
    public void setup(){
        fakePrintStream = mock(PrintStream.class);
        stockedBooks = new ArrayList<Book>();
        book1 = mock(Book.class);
        stockedBooks.add(book1);

        checkedOutBooks = new ArrayList<Book>();
        checkedOutBook1 = mock(Book.class);
        checkedOutBooks.add(checkedOutBook1);

        myLibrary = new Library(stockedBooks, checkedOutBooks, fakePrintStream);
    }

    @Test
    public void shouldPrintOneBookWhenLibraryHasOneBook() {

        myLibrary.displayBooks();

        verify(book1).getDetails();
    }

    @Test
    public void shouldPrintBooksWhenLibraryHasMultipleBooks(){
        Book book2 = mock(Book.class);
        stockedBooks.add(book2);

        myLibrary.displayBooks();

        verifyBookListPrinted(stockedBooks);
    }

    @Test
    public void shouldListBooksWithNumbers() {
        Book book2 = mock(Book.class);
        stockedBooks.add(book2);

        myLibrary.displayBooksWithNumbers();

        verifyBookListPrinted(stockedBooks);

        verify(fakePrintStream).print("1. ");
        verify(fakePrintStream).print("2. ");
    }

    @Test
    public void shouldListCheckedOutBooksWithNumbers() {
        Book checkedOutBook2 = mock(Book.class);
        checkedOutBooks.add(checkedOutBook2);

        myLibrary.displayCheckedOutBooksWithNumbers();

        verifyBookListPrinted(checkedOutBooks);

        verify(fakePrintStream).print("1. ");
        verify(fakePrintStream).print("2. ");
    }

    @Test
    public void shouldPrintNothingWhenLibraryIsEmpty(){
        List<Book> noBooks = new ArrayList<Book>();
        Library myLibrary = new Library(noBooks, checkedOutBooks, fakePrintStream);

        myLibrary.displayBooks();
        verify(fakePrintStream, times(0)).println();
    }

    @Test
    public void shouldRemoveCorrectBookWhenValidBookCheckedOut() {
        Book book2 = mock(Book.class);
        stockedBooks.add(book2);

        boolean checkedOut = myLibrary.checkoutBook(1);

        assertEquals(checkedOut, true);
        assertEquals(stockedBooks.size(), 1);
        assertEquals(stockedBooks.get(0), book2);
    }

    @Test
    public void shouldDecreaseStockByOneWhenCheckingOutValidBook() {
        myLibrary.checkoutBook(1);

        assertThat(stockedBooks.size(), is(0));
    }

    @Test
    public void shouldIncreaseStockedOutBooksByOneWhenCheckingOutValidBook() {
        myLibrary.checkoutBook(1);

        assertThat(checkedOutBooks.size(), is(2));
    }

    @Test
    public void shouldReturnFalseWhenInvalidBookCheckedOut() {
        Book book2 = mock(Book.class);
        stockedBooks.add(book2);

        boolean checkedOut = myLibrary.checkoutBook(10);

        assertEquals(checkedOut, false);
        assertEquals(stockedBooks.size(), 2);

    }

    @Test
    public void shouldReturnTrueWhenReturningValidBook() {
        boolean returned = myLibrary.returnBook(1);

        assertThat(returned, is(true));
    }

    @Test
    public void shouldIncreaseStockByOneWhenReturningValidBook() {
        myLibrary.returnBook(1);

        assertThat(stockedBooks.size(), is(2));
    }

    @Test
    public void shouldDecreaseCheckedOutBooksByOneWhenReturningValidBook() {
        myLibrary.returnBook(1);

        assertThat(checkedOutBooks.size(), is(0));
    }

    private void verifyBookListPrinted(List<Book> books) {
        for (Book book : books) {
            verify(book).getDetails();
        }
    }
}