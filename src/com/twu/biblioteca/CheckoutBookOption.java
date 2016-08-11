package com.twu.biblioteca;


import java.io.PrintStream;

public class CheckoutBookOption implements MenuOption {

    private final PrintStream printStream;
    private final Library library;
    private final Console console;

    public CheckoutBookOption(Library library, PrintStream printStream, Console console) {
        this.library = library;
        this.printStream = printStream;
        this.console = console;
    }

    @Override
    public void execute() {
        library.displayBooksWithNumbers();
        printStream.print("Choose a book: ");
        String userInput = console.getUserInput();
        if(library.checkoutBook(Integer.parseInt(userInput))) {
            printStream.println("Thank you! Enjoy your book.");
        } else {
            printStream.println("That book is not available.");
            printStream.print("Please re-enter a valid book choice: ");
        }
    }
}
