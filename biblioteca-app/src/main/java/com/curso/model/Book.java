package com.curso.model;

import java.util.Objects;

/**
 * Clase que representa un libro en la biblioteca.
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private boolean available;

    /**
     * Constructor para crear un libro.
     *
     * @param isbn el ISBN del libro
     * @param title el título del libro
     * @param author el autor del libro
     * @param publicationYear el año de publicación
     */
    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    // Getters y Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Book{"
                + "isbn='" + isbn + '\''
                + ", title='" + title + '\''
                + ", author='" + author + '\''
                + ", publicationYear=" + publicationYear
                + ", available=" + available
                + '}';
    }
}
