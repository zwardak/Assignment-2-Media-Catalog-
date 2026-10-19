/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediacatalog;

/**
 * Represents a music album in the media catalog.
 * Stores the artist's name, release year,
 * and number of tracks.
 *
 * @author zakariawardak
 */
public class Album extends MediaItem
{
    private String artist;
    private int year;
    private int trackCount;
    
    /**
    * Creates an Album object with the specified information.
    *
    * @param title the title of the album
    * @param id the album's unique identifier
    * @param artist the name of the album's artist
    * @param year the album's release year
    * @param trackCount the total number of tracks
    */
    public Album(String title,String id, String artist,int year, int trackCount)
    {
        super(id,title);
        setArtist(artist);
        setYear(year);
        setTrackCount(trackCount);
    }
    /**
    * Returns the name of the album's artist.
    *
    * @return the artist's name
    */
    public String getArtist() {
        return artist;
    }
    /**
    * Returns the release year of the album.
    *
    * @return the album's release year
    */
    public int getYear() {
        return year;
    }
    /**
     * Returns the number of tracks in the album.
     *
     * @return the total track count
     */ 
    public int getTrackCount() {
        return trackCount;
    }
    /**
    * Updates the album's artist after validation.
    *
    * @param artist the new artist's name
    * @throws IllegalArgumentException if the artist is null or blank
    */
    public void setArtist(String artist) {
        if (artist==null || artist.trim().isEmpty())
            throw new IllegalArgumentException("Please enter the artist name");
        this.artist = artist;
    }
    /**
     * Updates the album's release year after validation.
     *
     * @param year the new release year
     * @throws IllegalArgumentException if the year is zero or negative
     */
    public void setYear(int year)
    {
        if (year <= 0)
        {
            throw new IllegalArgumentException(
                "Release year must be greater than zero."
            );
        }

        this.year = year;
    }
    /**
    * Updates the number of tracks after validation.
    *
    * @param trackCount the new number of tracks
    * @throws IllegalArgumentException if trackCount is zero or negative
    */
    public void setTrackCount(int trackCount) {
        if (trackCount<=0)
            throw new IllegalArgumentException("The track has to"
                    + " be larger then 0.");
        this.trackCount = trackCount;
    }
    /**
    * Returns a formatted summary of the album,
    * including its ID, title, artist, release year,
    * and number of tracks.
    *
    * @return the formatted album information
    */
    @Override
    public String summary()
    {
        return "Album ID: " + getId()
             + "\nTitle: " + getTitle()
             + "\nArtist: " + artist
             + "\nRelease Year: " + year
             + "\nTrack Count: " + trackCount;
    }

}
