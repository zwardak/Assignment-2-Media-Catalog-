/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediacatalog;

/**
 * Represents a book in the media catalog.
 * Stores the author's name, publication year,
 * and total number of pages.
 *
 * @author zakariawardak
 */
public class Book extends MediaItem 
{
    private String author;
    private int year;
    private int pageCount;
    
    /**
    * Creates a Book object with the specified information.
    *
    * @param author the name of the book's author
    * @param year the publication year of the book
    * @param pageCount the total number of pages
    * @param Id the unique ID inherited from MediaItem
    * @param title the book title inherited from MediaItem
    */
    public Book(String author, int year,
        int pageCount, String Id, String title)
    {
        super(Id, title);
        setAuthor(author);
        setYear(year);
        setPageCount(pageCount);
    }
    /**
    * Returns the name of the book's author.
    *
    * @return the author's name
    */
    public String getAuthor() {
        return author;
    }
    /**
    * Returns the publication year of the book.
    *
    * @return the year the book was published
    */
    public int getYear() {
        return year;
    }
    /**
    * Returns the total number of pages in the book.
    *
    * @return the total page count
    */
    public int getPageCount() {
        return pageCount;
    }
    /**
    * Updates the author's name after validating it.
    *
    * @param author the new author's name
    * @throws IllegalArgumentException if the author is null or blank
    */
    public void setAuthor(String author)
    {
        if (author == null || author.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Author name cannot be empty."
            );
        }

            this.author = author;
    }
    /**
    * Updates the publication year after validating it.
    *
    * @param year the new publication year
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
    * Updates the number of pages after validating it.
    *
    * @param pageCount the new number of pages
    * @throws IllegalArgumentException if pageCount is zero or negative
    */
    public void setPageCount(int pageCount)
    {
        if (pageCount <= 0)
        {
            throw new IllegalArgumentException(
                "Page count must be greater than zero."
            );
        }

        this.pageCount = pageCount;
    }
    
    /**
    * Returns a formatted summary of the book,
    * including its ID, title, author, year,
    * and number of pages.
    *
    * @return the formatted book information
    */
    
  @Override
    public String summary()
    {
        return "Book ID: " + getId()
             + "\nTitle: " + getTitle()
             + "\nAuthor: " + author
             + "\nPublication Year: " + year
             + "\nNumber of Pages: " + pageCount;
    }

    
}
