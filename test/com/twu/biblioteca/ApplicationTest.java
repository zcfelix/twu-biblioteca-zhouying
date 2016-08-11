package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ApplicationTest {

    private Library library;
    private Application application;
    private PrintStream fakePrintStream;
    private Console fakeConsole;
    private ListBookOption listBookOption;
    private CheckoutBookOption checkoutBookOption;
    private Map<String, MenuOption> menu;
    private ReturnBookOption returnBookOption;

    @Before
    public void setUp() throws Exception {
        library = mock(Library.class);
        fakePrintStream = mock(PrintStream.class);
        fakeConsole = mock(Console.class);
        listBookOption = mock(ListBookOption.class);
        checkoutBookOption = mock(CheckoutBookOption.class);
        returnBookOption = mock(ReturnBookOption.class);
        menu = new HashMap<String, MenuOption>();
        menu.put("1", listBookOption);
        menu.put("2", checkoutBookOption);
        menu.put("3", returnBookOption);

        application = new Application(menu, library, fakePrintStream, fakeConsole);

        when(fakeConsole.getUserInput()).thenReturn("1").thenReturn("Q");
    }

    @Test
    public void shouldWelcomeUserWhenStarting(){
        application.start();

        verify(fakeConsole).displayWelcomeMessage();
    }

    @Test
    public void shouldDisplayMenuWhenStarting(){
        application.start();
        verifyMenuDisplayedTimes(2);
    }

    @Test
    public void shouldGetUserInput(){
        when(fakeConsole.getUserInput()).thenReturn("Q");

        application.start();

        verify(fakeConsole).getUserInput();
    }

    @Test
    public void shouldPrintErrorMessageWhenInvalidOptionIsChosen() {
        when(fakeConsole.getUserInput()).thenReturn("X").thenReturn("1").thenReturn("Q");

        application.start();
        verify(fakePrintStream).println("Select a valid option!");
        verifyMenuDisplayedTimes(3);
        verify(listBookOption).execute();
    }

    @Test
    public void shouldLoopUntilQuit() {
        when(fakeConsole.getUserInput()).thenReturn("1").thenReturn("1").thenReturn("Q");

        application.start();

        verifyMenuDisplayedTimes(3);
        verify(listBookOption, times(2)).execute();
    }

    @Test
    public void shouldSelectListBooks() {
        when(fakeConsole.getUserInput()).thenReturn("1").thenReturn("Q");

        application.start();

        verify(listBookOption).execute();
    }

    @Test
    public void shouldCheckoutBook() {
        when(fakeConsole.getUserInput()).thenReturn("2").thenReturn("1").thenReturn("Q");

        application.start();

        verify(checkoutBookOption).execute();
    }

    @Test
    public void shouldReturnBook() {
        when(fakeConsole.getUserInput()).thenReturn("3").thenReturn("1").thenReturn("Q");

        application.start();

        verify(returnBookOption).execute();
    }

    private void verifyMenuDisplayedTimes(int t) {
        verify(fakePrintStream, times(t)).println("1. List books");
        verify(fakePrintStream, times(t)).println("2. Checkout book");
        verify(fakePrintStream, times(t)).println("3. Return book");
        verify(fakePrintStream, times(t)).println("Q. Quit");
        verify(fakePrintStream, times(t)).print("Enter option number: ");
    }

}
