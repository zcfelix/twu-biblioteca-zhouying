package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Console {


    private BufferedReader reader;
    private PrintStream printStream;

    public Console(BufferedReader reader, PrintStream printStream) {
        this.reader = reader;
        this.printStream = printStream;
    }



    public String getUserInput() {
        String userInput = "";
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printStream.println();

        return userInput;
    }

    public void displayWelcomeMessage() {
        printStream.println("Welcome!");
    }

    public void displayChooseBookPromptMessage() {
        printStream.print("Choose a book: ");
    }
}
