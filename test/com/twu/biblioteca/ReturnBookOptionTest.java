package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReturnBookOptionTest {

    private Library library;
    private Console console;
    private ReturnBookOption returnBookOption;

    @Before
    public void setUp() throws Exception {
        library = mock(Library.class);
        console = mock(Console.class);
        returnBookOption = new ReturnBookOption(library, console);

        when(console.getUserInput()).thenReturn("1");
    }

    @Test
    public void shouldDisplayCheckedOutBooksOnExecute() {
        returnBookOption.execute();

        verify(library).displayCheckedOutBooksWithNumbers();
    }

    @Test
    public void shouldPromptUserOnExecute() {
        returnBookOption.execute();

        verify(console).displayChooseBookPromptMessage();
    }

    @Test
    public void shouldGetInputFromConsoleOnExecute() {
        returnBookOption.execute();

        verify(console).getUserInput();
    }


    @Test
    public void shouldReturnBook1WhenExecuteCalledWithInput1(){
        returnBookOption.execute();

        verify(library).returnBook(1);
    }
}
