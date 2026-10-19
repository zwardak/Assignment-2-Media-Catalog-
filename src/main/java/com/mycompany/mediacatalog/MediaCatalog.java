/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mediacatalog;
import java.util.ArrayList;

/**
 * Manages a collection of media items, including books,
 * movies, and albums.
 * Provides methods to add items, display their information,
 * generate unique IDs, and retrieve the catalog items.
 *
 * @author zakariawardak
 */
public class MediaCatalog 
{
    private ArrayList<MediaItem> items;
   /**
    * Creates a MediaCatalog using the provided list
    * of media items.
    *
    * @param items the list of media items to store
    */ 
    public MediaCatalog(ArrayList<MediaItem> items)
    {
        this.items = new ArrayList<>(items);
    }
    /**
    * Creates an empty media catalog.
    */
    public MediaCatalog()
    {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a media item to the catalog.
     * The item can be a Book, Movie, or Album object.
     *
     * @param item the media item to add to the catalog
     */

    public void addItems (MediaItem item)
    {
        items.add(item);
        
    }
    /**
    * Displays all media items stored in the catalog.
    * Uses polymorphism to call the appropriate summary()
    * method for each media item.
    */
    public void listItems()
    {
        for (MediaItem item : items )
        {
            System.out.println(item.summary());
            System.out.println();
        }
    }
    
    /**
     * Generates a unique ID for a media item based on its type.
     *
     * @param prefix the media type prefix (BK, MV, or AL)
     * @return the newly generated unique ID
     */
    public String generateId(String prefix)
    {
        int maxNumber = 0;

        for (MediaItem item : items)
        {
            String id = item.getId();

            if (id.startsWith(prefix + "-"))
            {
                String numberPart = id.substring(prefix.length() + 1);
                int number = Integer.parseInt(numberPart);

                if (number > maxNumber)
                {
                    maxNumber = number;
                }
            }
        }

        int newId = maxNumber + 1;

        return String.format("%s-%03d", prefix, newId);
    }
    /**
    * Returns a copy of all media items stored in the catalog,
    * including books, movies, and albums.
    *
    * @return an ArrayList containing all media items
    */
    public ArrayList<MediaItem> getItems ()
    {
        return new ArrayList<>(items);
    }
    
    
}
