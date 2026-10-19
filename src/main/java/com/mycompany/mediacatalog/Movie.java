/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediacatalog;

/**
 * Represents a movie in the media catalog.
 * Stores the director's name, release year,
 * and runtime in minutes.
 *
 * @author zakariawardak
 */
public class Movie extends MediaItem
{
    private String director;
    private int year;
    private int runtimeMinutes;
    
    /**
    * Creates a Movie object with the specified information.
    *
    * @param director the movie director's name
    * @param year the movie's release year
    * @param runtimeMinutes the movie's duration in minutes
    * @param title the movie title
    * @param id the movie's unique identifier
    */
    public Movie (String director, int year, 
            int runtimeMinutes,String title, String id )
    {
        super(id,title);
        setYear(year);
        setDirector(director);
        setRuntimeMinutes(runtimeMinutes);   
    }
    /**
    * Returns the name of the movie's director.
    *
    * @return the director's name
    */
    public String getDirector() {
        return director;
    }
    /**
    * Returns the release year of the movie.
    *
    * @return the year the movie was released
    */
    public int getYear() {
        return year;
    }
    /**
    * Returns the total runtime of the movie in minutes.
    *
    * @return the movie's runtime in minutes
    */
    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }
    /**
    * Updates the movie director's name after validation.
    *
    * @param director the new director's name
    * @throws IllegalArgumentException if the name is null or blank
    */
    public void setDirector(String director) 
    {
        if (director== null || director.trim().isEmpty())
        {
            throw new IllegalArgumentException("Enter the movie "
                    + "director name.");
        }
        this.director = director;
    }
    /**
    * Updates the movie's release year after validation.
    *
    * @param year the new release year
    * @throws IllegalArgumentException if the year is zero or negative
    */
    public void setYear(int year)
    {
        if (year <= 0)
        {
            throw new IllegalArgumentException(
                "Publication year must be greater than zero."
            );
        }

        this.year = year;
    }
    /**
    * Updates the movie's runtime after validating the value.
    *
    * @param runtimeMinutes the new runtime in minutes
    * @throws IllegalArgumentException if runtimeMinutes is zero or negative
    */
    public void setRuntimeMinutes(int runtimeMinutes) 
    {
        if (runtimeMinutes <=0)
        {
            throw new IllegalArgumentException(
            "Runtime must be greater than zero.");
        }
        this.runtimeMinutes = runtimeMinutes;
    }
    /**
    * Returns a formatted summary of the movie,
    * including its ID, title, director, year,
    * and runtime.
    *
    * @return the formatted movie information
    */
    @Override
    public String summary()
    {
        return "Movie ID: " + getId()
             + "\nTitle: " + getTitle()
             + "\nDirector: " + director
             + "\nRelease Year: " + year
             + "\nRuntime: " + runtimeMinutes + " minutes";
    }
    
    
}
