package com.curso.service;

import com.curso.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones sobre libros en la biblioteca.
 */
public class LibraryService {
    private final List<Book> books;

    /**
     * Constructor que inicializa la lista de libros.
     */
    public LibraryService() {
        this.books = new ArrayList<>();
    }

    /**
     * Agrega un libro a la biblioteca.
     *
     * @param book el libro a agregar
     * @return true si se agregó exitosamente, false si el ISBN ya existe
     */
    public boolean addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }
        if (isbnExists(book.getIsbn())) {
            return false;
        }
        return books.add(book);
    }

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn el ISBN del libro
     * @return Optional con el libro si existe, vacío en caso contrario
     */
    public Optional<Book> findBookByIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            return Optional.empty();
        }
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    /**
     * Busca libros por autor.
     *
     * @param author el nombre del autor
     * @return lista de libros del autor
     */
    public List<Book> findBooksByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            return new ArrayList<>();
        }
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Verifica si un ISBN ya existe en la biblioteca.
     *
     * @param isbn el ISBN a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean isbnExists(String isbn) {
        return findBookByIsbn(isbn).isPresent();
    }

    /**
     * Obtiene un libro disponible por ISBN.
     *
     * @param isbn el ISBN del libro
     * @return el libro si está disponible
     * @throws IllegalStateException si el libro no existe o no está disponible
     */
    public Book borrowBook(String isbn) {
        Book book = findBookByIsbn(isbn)
                .orElseThrow(() -> new IllegalStateException("Libro no encontrado: " + isbn));
        if (!book.isAvailable()) {
            throw new IllegalStateException("El libro no está disponible: " + isbn);
        }
        book.setAvailable(false);
        return book;
    }

    /**
     * Devuelve un libro prestado.
     *
     * @param isbn el ISBN del libro
     * @throws IllegalStateException si el libro no existe
     */
    public void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn)
                .orElseThrow(() -> new IllegalStateException("Libro no encontrado: " + isbn));
        book.setAvailable(true);
    }

    /**
     * Obtiene todos los libros de la biblioteca.
     *
     * @return lista de todos los libros
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Obtiene todos los libros disponibles.
     *
     * @return lista de libros disponibles
     */
    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    /**
     * Elimina un libro de la biblioteca.
     *
     * @param isbn el ISBN del libro a eliminar
     * @return true si se eliminó, false en caso contrario
     */
    public boolean removeBook(String isbn) {
        return books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    /**
     * Obtiene el total de libros en la biblioteca.
     *
     * @return cantidad de libros
     */
    public int getTotalBooks() {
        return books.size();
    }

    /**
     * Obtiene la cantidad de libros disponibles.
     *
     * @return cantidad de libros disponibles
     */
    public int getAvailableBooksCount() {
        return (int) books.stream().filter(Book::isAvailable).count();
    }

    /**
     * Busca libros por género o categoría.
     *
     * @param genre el género del libro
     * @return lista de libros del género especificado
     */
    public List<Book> findBooksByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return new ArrayList<>();
        }
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Busca libros disponibles por género.
     *
     * @param genre el género del libro
     * @return lista de libros disponibles del género especificado
     */
    public List<Book> findAvailableBooksByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return new ArrayList<>();
        }
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre) && book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Obtiene todos los géneros únicos registrados.
     *
     * @return lista de géneros
     */
    public List<String> getAllGenres() {
        List<String> genres = new ArrayList<>();
        for (Book book : books) {
            if (!genres.contains(book.getGenre())) {
                genres.add(book.getGenre());
            }
        }
        return genres;
    }
}
