/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediacatalog;

/**
 * Abstract parent class representing a media item
 * in the catalog.
 * 
 * @author zakariawardak
 */
public abstract class MediaItem 
{
    private String id;
    private String title;
    
    /**
     * Creates a media item with and ID and title. 
     * @param id unique identifier of the media item
     * @param title title of the media item
     */
    public MediaItem (String id,String title)
    {
        this.id = id;
        this.title = title;
    }

    public String getId() 
    {
        return id;
    }

    public String getTitle() 
    {
        return title;
    }

    /**
     * Return a summary of the media item's information. 
     * @return formatted summary of the media item.
     */
    public abstract String summary();
    
    
    
    
}
