/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediacatalog;

import java.util.Scanner;
import java.io.IOException;

/**
 * Main class for the Media Catalog application.
 * Provides a command-line menu to manage books,
 * movies, and albums.
 *
 * @author zakariawardak
 */
public class Main
{
    /**
     * Starts the Media Catalog application.
     * Loads existing data and displays the menu.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args)
    {
        MediaCatalog catalog = new MediaCatalog();
        CatalogFileManager fileManager = new CatalogFileManager();

        try (Scanner scanner = new Scanner(System.in))
        {
            // Load existing catalog when the program starts
            try
            {
                fileManager.loadFromCSV(catalog);
                System.out.println("Catalog loading completed.");
            }
            catch (IOException e)
            {
                System.out.println("Error loading CSV: "
                        + e.getMessage());
                return;
            }

            boolean running = true;

            while (running)
            {
                System.out.println("\n========== MEDIA CATALOG ==========");
                System.out.println("1. List all items");
                System.out.println("2. Add item (Book/Movie/Album)");
                System.out.println("3. Save catalog to CSV");
                System.out.println("4. Export catalog to binary file");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice)
                {
                    case "1":
                        if (catalog.getItems().isEmpty())
                        {
                            System.out.println("Catalog is empty.");
                        }
                        else
                        {
                            catalog.listItems();
                        }
                        break;

                    case "2":
                        addItem(scanner, catalog);
                        break;

                    case "3":
                        try
                        {
                            fileManager.saveToCSV(catalog);
                            System.out.println(
                                    "Catalog saved successfully.");
                        }
                        catch (IOException e)
                        {
                            System.out.println("Error saving CSV: "
                                    + e.getMessage());
                        }
                        break;

                    case "4":
                        try
                        {
                            fileManager.exportToBinary(catalog);
                        }
                        catch (IOException e)
                        {
                            System.out.println("Export error: "
                                    + e.getMessage());
                        }
                        break;

                    case "0":
                        running = false;
                        System.out.println(
                                "Thank you for using Media Catalog.");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again.");
                }
            }
        }
    }

    /**
     * Allows the user to add a Book, Movie, or Album.
     * Automatically generates an ID for the selected type.
     *
     * @param scanner Scanner used to read user input
     * @param catalog catalog where the item will be added
     */
    private static void addItem(Scanner scanner,
                                MediaCatalog catalog)
    {
        System.out.println("\n========== ADD MEDIA ITEM ==========");
        System.out.println("1. Add Book");
        System.out.println("2. Add Movie");
        System.out.println("3. Add Album");
        System.out.println("0. Cancel");
        System.out.print("Select media type: ");

        String type = scanner.nextLine().trim();

        if (type.equals("0"))
        {
            return;
        }

        if (!type.equals("1") && !type.equals("2")
                && !type.equals("3"))
        {
            System.out.println("Invalid media type.");
            return;
        }

        String title = readText(scanner, "Enter title: ");

        String creator;
        int year;
        int count;

        switch (type)
        {
            case "1":
                creator = readText(scanner, "Enter author: ");
                year = readPositiveInt(scanner,
                        "Enter publication year: ");
                count = readPositiveInt(scanner,
                        "Enter page count: ");

                String bookId = catalog.generateId("BK");

                Book book = new Book(
                        creator, year, count, bookId, title);

                catalog.addItems(book);

                System.out.println("Book added successfully!");
                System.out.println("Generated ID: " + bookId);
                break;

            case "2":
                creator = readText(scanner, "Enter director: ");
                year = readPositiveInt(scanner,
                        "Enter release year: ");
                count = readPositiveInt(scanner,
                        "Enter runtime in minutes: ");

                String movieId = catalog.generateId("MV");

                Movie movie = new Movie(
                        creator, year, count, title, movieId);

                catalog.addItems(movie);

                System.out.println("Movie added successfully!");
                System.out.println("Generated ID: " + movieId);
                break;

            case "3":
                creator = readText(scanner, "Enter artist: ");
                year = readPositiveInt(scanner,
                        "Enter release year: ");
                count = readPositiveInt(scanner,
                        "Enter track count: ");

                String albumId = catalog.generateId("AL");

                Album album = new Album(
                        title, albumId, creator, year, count);

                catalog.addItems(album);

                System.out.println("Album added successfully!");
                System.out.println("Generated ID: " + albumId);
                break;
        }
    }

    /**
     * Reads a non-empty String from the user.
     * Rejects commas because the application uses
     * a simple comma-separated CSV format.
     *
     * @param scanner Scanner used for user input
     * @param prompt message displayed to the user
     * @return the validated String
     */
    private static String readText(Scanner scanner, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (value.isEmpty())
            {
                System.out.println("Value cannot be empty.");
            }
            else if (value.contains(","))
            {
                System.out.println(
                        "Commas are not supported in this field.");
            }
            else
            {
                return value;
            }
        }
    }

    /**
     * Reads and validates a positive integer.
     * Repeats the prompt until valid input is entered.
     *
     * @param scanner Scanner used for user input
     * @param prompt message displayed to the user
     * @return a positive integer
     */
    private static int readPositiveInt(Scanner scanner,
                                       String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            try
            {
                int number = Integer.parseInt(
                        scanner.nextLine().trim());

                if (number > 0)
                {
                    return number;
                }

                System.out.println(
                        "Number must be greater than zero.");
            }
            catch (NumberFormatException e)
            {
                System.out.println(
                        "Invalid input. Enter a whole number.");
            }
        }
    }
}