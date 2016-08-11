package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsoleTest {

    private Console console;
    private BufferedReader fakeBufferedReader;
    private PrintStream fakePrintStream;

    @Before
    public void setUp() throws Exception {
        fakeBufferedReader = mock(BufferedReader.class);
        fakePrintStream = mock(PrintStream.class);
        console = new Console(fakeBufferedReader, fakePrintStream);
    }

    @Test
    public void shouldReturnProperUserInputAsString() throws Exception {
        when(fakeBufferedReader.readLine()).thenReturn("Q");
        assertThat(console.getUserInput(), is("Q"));
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        console.displayWelcomeMessage();

        verify(fakePrintStream).println("Welcome!");
    }

    @Test
    public void shouldDisplayPromptMessage(){
        console.displayChooseBookPromptMessage();

        verify(fakePrintStream).print("Choose a book: ");
    }
}