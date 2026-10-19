/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediacatalog;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;



/**
 * Manages file operations for the Media Catalog application.
 * Provides methods to load media items from a CSV file,
 * save catalog items to a CSV file, and export catalog
 * data into a binary file.
 *
 * @author zakariawardak
 */

public class CatalogFileManager 
{
    

/**
 * Loads media items from the catalog.csv file.
 * Reads each record and identifies whether the item
 * is a Book, Movie, or Album.
 * Creates the appropriate object and adds it to
 * the MediaCatalog.
 *
 * If the CSV file does not exist, the method
 * displays a message and returns.
 *
 * @param catalog the MediaCatalog where items are stored
 * @throws IOException if an error occurs while reading the file
 */

 public void loadFromCSV(MediaCatalog catalog) throws IOException
 {
     File file = new File ("catalog.csv");
     
     if (!file.exists())
     {
         System.out.println("CSV file not found. ");
         return;
     }
     
    try (BufferedReader reader =
        new BufferedReader(new FileReader(file)))
    {
    String line;

    while ((line = reader.readLine()) != null)
    {
        String [] fields = line.split(",");
        
        if (fields.length !=6)
        {
            System.out.println("Invalid CSV recod"+line);
            continue;
        }
        try
        {

            String type = fields[0].trim();
            String id = fields [1].trim();
            String title = fields[2].trim();
            String creator= fields[3].trim();
            int year = Integer.parseInt(fields[4].trim());
            int count = Integer.parseInt(fields[5].trim());
             

            if (type.equalsIgnoreCase("Book"))
            {
                Book book = new Book(
            creator, year, count, id, title);
                catalog.addItems(book);
            }
            else if (type.equalsIgnoreCase("Movie"))
            {
                Movie movie = new Movie (creator, year, count, title, id);
                catalog.addItems (movie);
            }
            else if (type.equalsIgnoreCase("Album"))
            {
                Album album = new Album (title, id, creator, year, count);
                catalog.addItems (album);
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid CSV record: " + line);
            System.out.println("Reason: " + e.getMessage());
        }
        
        
        
    }
     
    }
 }
 
 /**
 * Saves all books, movies, and albums from the media catalog
 * into catalog.csv using the required CSV format.
 * Overwrites the existing file with the current catalog data.
 *
 * @param catalog the MediaCatalog containing items to save
 * @throws IOException if an error occurs while writing the file
 */
 public void saveToCSV (MediaCatalog catalog) throws IOException
 {
     try (BufferedWriter writer = 
             new BufferedWriter(new FileWriter("catalog.csv")))
     {
         for (MediaItem item : catalog.getItems())
         {
            if (item instanceof Book)
            {
                Book book = (Book) item;
                
                String csvLine = "Book,"
                        +book.getId()+","
                        +book.getTitle()+","
                        +book.getAuthor()+","
                        +book.getYear()+","
                        +book.getPageCount();
                
                writer.write(csvLine);
                writer.newLine();
            }
            else if (item instanceof Movie)
            {
                Movie movie = (Movie) item; 
                
                String csvLine = "Movie,"
                        + movie.getId() + ","
                        + movie.getTitle() + ","
                        + movie.getDirector() + ","
                        + movie.getYear() + ","
                        + movie.getRuntimeMinutes();

                
                writer.write(csvLine);
                writer.newLine();
                       
            }
            else if (item instanceof Album)
            {
                Album album = (Album) item;
                
                String csvLine = "Album,"
                         + album.getId() + ","
                        + album.getTitle() + ","
                        + album.getArtist() + ","
                        + album.getYear() + ","
                        + album.getTrackCount();
                writer.write(csvLine);
                writer.newLine();
                        
                                
            }
         }
     }
 }

/**
 * Exports all media items from the catalog into
 * a binary file named catalog.bin.
 *
 * Uses DataOutputStream to write the number of items,
 * media types, IDs, titles, and additional information
 * for each Book, Movie, and Album.
 *
 * Overwrites the existing binary file if it exists.
 *
 * @param catalog the MediaCatalog containing items to export
 * @throws IOException if an error occurs while writing
 * the binary file or an unsupported item type is found
 */

 public void exportToBinary (MediaCatalog catalog)throws IOException
 {
     try (DataOutputStream writer =
             new DataOutputStream (new FileOutputStream ("catalog.bin")))
     {
        writer.writeInt(catalog.getItems().size());

        for (MediaItem item : catalog.getItems())
        {
            if (item instanceof Book)
            {
                Book book = (Book)item;
                writer.writeUTF("Book");
                writer.writeUTF(book.getId());
                writer.writeUTF(book.getTitle());
                writer.writeUTF(book.getAuthor());
                writer.writeInt(book.getYear());
                writer.writeInt(book.getPageCount());

            }
            else if (item instanceof Movie)
            {
                Movie movie = (Movie)item;
                
                writer.writeUTF("Movie");
                writer.writeUTF(movie.getId());
                writer.writeUTF(movie.getTitle());
                writer.writeUTF(movie.getDirector());
                writer.writeInt(movie.getYear());
                writer.writeInt(movie.getRuntimeMinutes());
            }
            else if (item instanceof Album)
            {
                Album album = (Album)item;
                
                writer.writeUTF("Album");
                writer.writeUTF(album.getId());
                writer.writeUTF(album.getTitle());
                writer.writeUTF(album.getArtist());
                writer.writeInt(album.getYear());
                writer.writeInt(album.getTrackCount());
                
            }
            else 
            {
                throw new IOException("Unsupported media item type.");
             
            }
         }
     }
     System.out.println("Catalog exported to catalog.bin successfully.");
     
 }
    
}
