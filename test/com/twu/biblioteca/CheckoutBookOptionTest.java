package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutBookOptionTest {

    private Library library;
    private CheckoutBookOption checkoutBookOption;
    private PrintStream fakePrintStream;
    private Console console;

    @Before
    public void setUp() throws Exception {
        library = mock(Library.class);
        fakePrintStream = mock(PrintStream.class);
        console = mock(Console.class);
        checkoutBookOption = new CheckoutBookOption(library, fakePrintStream, console);

        when(console.getUserInput()).thenReturn("1");
    }

    @Test
    public void shouldPromptUserOnExecute() {
        checkoutBookOption.execute();
        verify(library).displayBooksWithNumbers();
        verify(fakePrintStream).print("Choose a book: ");
    }

    @Test
    public void shouldGetInputFromConsoleOnExecute() {
        checkoutBookOption.execute();

        verify(console).getUserInput();
    }

    @Test
    public void shouldCheckoutBookFromLibrary(){
        checkoutBookOption.execute();
        verify(library).checkoutBook(1);
    }

    @Test
    public void shouldDisplayMessageWhenCheckoutSuccessful() {
        when(library.checkoutBook(1)).thenReturn(true);
        checkoutBookOption.execute();
        verify(fakePrintStream).println("Thank you! Enjoy your book.");

    }

    @Test
    public void shouldDisplayMessageWhenCheckoutUnsuccessful() {
        when(library.checkoutBook(3)).thenReturn(false);
        checkoutBookOption.execute();
        verify(fakePrintStream).println("That book is not available.");

    }

    @Test
    public void shouldRepromptUserForBookWhenUserChoiceIsInvalid() {
        when(library.checkoutBook(3)).thenReturn(false);
        checkoutBookOption.execute();
        verify(fakePrintStream).println("That book is not available.");
        verify(fakePrintStream).print("Please re-enter a valid book choice: ");
    }
}