package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private String publishDate;

    public Book(String title, String author, String publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getDetails() {
        String titleToPrint = shortenTextIfNecessary(title);
        String authorToPrint = shortenTextIfNecessary(author);
        return String.format("%-30s %-30s %-4s", titleToPrint, authorToPrint, publishDate);
    }

    private String shortenTextIfNecessary(String text) {
        if (text.length() > 30) {
            return text.substring(0, 27) + "...";
        } else {
            return text;
        }
    }
}