package com.twu.biblioteca;

public class ListBookOption implements MenuOption {

    private Library library;

    public ListBookOption(Library library) {
        this.library = library;
    }

    @Override
    public void execute() {
        library.displayBooks();
    }
}
