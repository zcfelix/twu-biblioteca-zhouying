package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Map;

public class Application {

    Library library;
    PrintStream printStream;
    Console console;
    Map<String, MenuOption> menu;

    public Application(Map<String, MenuOption> menu, Library library, PrintStream printStream, Console console) {
        this.library = library;
        this.printStream = printStream;
        this.console = console;
        this.menu = menu;
    }

    public void start() {
        console.displayWelcomeMessage();
        displayMenu();
        String input = console.getUserInput();

        while (!input.equals("Q")) {
            if (menu.containsKey(input)) {
                menu.get(input).execute();
            } else {
                printStream.println("Select a valid option!");
            }

            displayMenu();
            input = console.getUserInput();
        }
    }

    public void displayMenu() {
        printStream.println();
        printStream.println("1. List books");
        printStream.println("2. Checkout book");
        printStream.println("3. Return book");
        printStream.println("Q. Quit");
        printStream.println();
        printStream.print("Enter option number: ");
    }
}
