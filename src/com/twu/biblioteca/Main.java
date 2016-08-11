package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = System.out;
        Console console = new Console(bufferedReader, printStream);
        Library library = library();
        Map<String, MenuOption> menuOptions = new HashMap<String, MenuOption>();
        menuOptions.put("1", new ListBookOption(library));
        menuOptions.put("2", new CheckoutBookOption(library, printStream, console));
        menuOptions.put("3", new ReturnBookOption(library, console));

        new Application(menuOptions, library(), printStream, console).start();
    }

    private static Library library() {
        List<Book> listOfBooks = new ArrayList<Book>();
        Book book1 = new Book("The Dispossessed", "Ursula K. Le Guin", "2001");
        Book book2 = new Book("Island", "Aldous Huxley", "1953");
        listOfBooks.add(book1);
        listOfBooks.add(book2);

        List<Book> listOfCheckedOutBooks = new ArrayList<Book>();
        Book checkedOutBook1 = new Book("The Name of the Wind", "Patrick Rothfuss", "2006");
        Book checkedOutBook2 = new Book("Wise Man's Fear", "Patrick Rothfuss", "2010");
        listOfCheckedOutBooks.add(checkedOutBook1);
        listOfCheckedOutBooks.add(checkedOutBook2);

        return new Library(listOfBooks, listOfCheckedOutBooks, System.out);
    }
}