package com.twu.biblioteca;

public class ReturnBookOption implements MenuOption{
    private Console console;
    private Library library;

    public ReturnBookOption(Library library, Console console) {
        this.library = library;
        this.console = console;

    }

    @Override
    public void execute() {
        library.displayCheckedOutBooksWithNumbers();
        console.displayChooseBookPromptMessage();

        String userInput = console.getUserInput();
        library.returnBook(Integer.parseInt(userInput));
    }
}
