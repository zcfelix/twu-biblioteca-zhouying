package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListBookOptionTest {

    private Library library;
    private ListBookOption listBookOption;

    @Before
    public void setUp() throws Exception {
        library = mock(Library.class);
        listBookOption = new ListBookOption(library);
    }

    @Test
    public void shouldListBooksOnExecute() {
        listBookOption.execute();

        verify(library).displayBooks();
    }

}
