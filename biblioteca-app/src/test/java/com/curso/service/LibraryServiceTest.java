package com.curso.service;

import com.curso.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para LibraryService.
 */
@DisplayName("Pruebas del Servicio de Biblioteca")
class LibraryServiceTest {
    private LibraryService libraryService;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryService();
        book1 = new Book("978-3-16-148410-0", "Clean Code", "Robert C. Martin", 2008, "Programación");
        book2 = new Book("978-0-13-110362-7", "The C Programming Language", "Brian Kernighan", 1988, "Programación");
        book3 = new Book("978-0-201-61622-4", "The Pragmatic Programmer", "David Hunt", 1999, "Programación");
    }

    @Test
    @DisplayName("Debe agregar un libro correctamente")
    void testAddBook() {
        boolean result = libraryService.addBook(book1);
        assertTrue(result);
        assertEquals(1, libraryService.getTotalBooks());
    }

    @Test
    @DisplayName("No debe agregar un libro con ISBN duplicado")
    void testAddDuplicateBook() {
        libraryService.addBook(book1);
        Book duplicateBook = new Book("978-3-16-148410-0", "Another Title", "Another Author", 2020);

        boolean result = libraryService.addBook(duplicateBook);
        assertFalse(result);
        assertEquals(1, libraryService.getTotalBooks());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando se intenta agregar null")
    void testAddNullBook() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.addBook(null));
    }

    @Test
    @DisplayName("Debe encontrar un libro por ISBN")
    void testFindBookByIsbn() {
        libraryService.addBook(book1);

        Optional<Book> found = libraryService.findBookByIsbn("978-3-16-148410-0");
        assertTrue(found.isPresent());
        assertEquals(book1, found.get());
    }

    @Test
    @DisplayName("Debe devolver vacío cuando no encuentra un libro por ISBN")
    void testFindBookByIsbnNotFound() {
        Optional<Book> found = libraryService.findBookByIsbn("invalid-isbn");
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Debe devolver vacío para ISBN nulo o vacío")
    void testFindBookByNullOrEmptyIsbn() {
        Optional<Book> resultNull = libraryService.findBookByIsbn(null);
        Optional<Book> resultEmpty = libraryService.findBookByIsbn("");

        assertFalse(resultNull.isPresent());
        assertFalse(resultEmpty.isPresent());
    }

    @Test
    @DisplayName("Debe encontrar libros por autor")
    void testFindBooksByAuthor() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        Book anotherByMartin = new Book("123-456", "Design Patterns", "Robert C. Martin", 2010);
        libraryService.addBook(anotherByMartin);

        List<Book> foundBooks = libraryService.findBooksByAuthor("Robert C. Martin");
        assertEquals(2, foundBooks.size());
    }

    @Test
    @DisplayName("Debe devolver lista vacía cuando no hay libros del autor")
    void testFindBooksByAuthorNotFound() {
        List<Book> foundBooks = libraryService.findBooksByAuthor("Unknown Author");
        assertTrue(foundBooks.isEmpty());
    }

    @Test
    @DisplayName("Debe verificar si un ISBN existe")
    void testIsbnExists() {
        libraryService.addBook(book1);

        assertTrue(libraryService.isbnExists("978-3-16-148410-0"));
        assertFalse(libraryService.isbnExists("invalid-isbn"));
    }

    @Test
    @DisplayName("Debe prestar un libro disponible")
    void testBorrowBook() {
        libraryService.addBook(book1);
        assertTrue(book1.isAvailable());

        Book borrowed = libraryService.borrowBook("978-3-16-148410-0");
        assertFalse(borrowed.isAvailable());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando intenta prestar un libro no disponible")
    void testBorrowUnavailableBook() {
        libraryService.addBook(book1);
        libraryService.borrowBook("978-3-16-148410-0");

        assertThrows(IllegalStateException.class,
                () -> libraryService.borrowBook("978-3-16-148410-0"));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando intenta prestar un libro inexistente")
    void testBorrowNonExistentBook() {
        assertThrows(IllegalStateException.class,
                () -> libraryService.borrowBook("invalid-isbn"));
    }

    @Test
    @DisplayName("Debe devolver un libro prestado")
    void testReturnBook() {
        libraryService.addBook(book1);
        libraryService.borrowBook("978-3-16-148410-0");
        assertFalse(book1.isAvailable());

        libraryService.returnBook("978-3-16-148410-0");
        assertTrue(book1.isAvailable());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando intenta devolver un libro inexistente")
    void testReturnNonExistentBook() {
        assertThrows(IllegalStateException.class,
                () -> libraryService.returnBook("invalid-isbn"));
    }

    @Test
    @DisplayName("Debe obtener todos los libros")
    void testGetAllBooks() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addBook(book3);

        List<Book> allBooks = libraryService.getAllBooks();
        assertEquals(3, allBooks.size());
    }

    @Test
    @DisplayName("Debe obtener solo libros disponibles")
    void testGetAvailableBooks() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addBook(book3);

        libraryService.borrowBook("978-3-16-148410-0");
        List<Book> available = libraryService.getAvailableBooks();
        assertEquals(2, available.size());
    }

    @Test
    @DisplayName("Debe eliminar un libro")
    void testRemoveBook() {
        libraryService.addBook(book1);
        assertEquals(1, libraryService.getTotalBooks());

        boolean removed = libraryService.removeBook("978-3-16-148410-0");
        assertTrue(removed);
        assertEquals(0, libraryService.getTotalBooks());
    }

    @Test
    @DisplayName("Debe devolver false al intentar eliminar un libro inexistente")
    void testRemoveNonExistentBook() {
        boolean removed = libraryService.removeBook("invalid-isbn");
        assertFalse(removed);
    }

    @Test
    @DisplayName("Debe obtener el total de libros")
    void testGetTotalBooks() {
        assertEquals(0, libraryService.getTotalBooks());
        libraryService.addBook(book1);
        assertEquals(1, libraryService.getTotalBooks());
        libraryService.addBook(book2);
        assertEquals(2, libraryService.getTotalBooks());
    }

    @Test
    @DisplayName("Debe obtener el contador de libros disponibles")
    void testGetAvailableBooksCount() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        libraryService.addBook(book3);
        assertEquals(3, libraryService.getAvailableBooksCount());

        libraryService.borrowBook("978-3-16-148410-0");
        assertEquals(2, libraryService.getAvailableBooksCount());

        libraryService.borrowBook("978-0-13-110362-7");
        assertEquals(1, libraryService.getAvailableBooksCount());
    }

    @Test
    @DisplayName("Debe encontrar libros por género")
    void testFindBooksByGenre() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        Book ficcionBook = new Book("978-0-06-112008-4", "To Kill a Mockingbird", "Harper Lee", 1960, "Ficción");
        libraryService.addBook(ficcionBook);

        List<Book> programationBooks = libraryService.findBooksByGenre("Programación");
        assertEquals(2, programationBooks.size());

        List<Book> fictionBooks = libraryService.findBooksByGenre("Ficción");
        assertEquals(1, fictionBooks.size());
    }

    @Test
    @DisplayName("Debe devolver lista vacía cuando no hay libros del género")
    void testFindBooksByGenreNotFound() {
        libraryService.addBook(book1);
        List<Book> foundBooks = libraryService.findBooksByGenre("Misterio");
        assertTrue(foundBooks.isEmpty());
    }

    @Test
    @DisplayName("Debe encontrar libros disponibles por género")
    void testFindAvailableBooksByGenre() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        assertEquals(2, libraryService.findAvailableBooksByGenre("Programación").size());

        libraryService.borrowBook("978-3-16-148410-0");
        assertEquals(1, libraryService.findAvailableBooksByGenre("Programación").size());
    }

    @Test
    @DisplayName("Debe obtener todos los géneros únicos")
    void testGetAllGenres() {
        libraryService.addBook(book1);
        libraryService.addBook(book2);
        Book ficcionBook = new Book("978-0-06-112008-4", "To Kill a Mockingbird", "Harper Lee", 1960, "Ficción");
        libraryService.addBook(ficcionBook);

        List<String> genres = libraryService.getAllGenres();
        assertEquals(2, genres.size());
        assertTrue(genres.contains("Programación"));
        assertTrue(genres.contains("Ficción"));
    }

    @Test
    @DisplayName("Debe validar género por defecto 'General'")
    void testBookGenreDefault() {
        Book bookWithoutGenre = new Book("978-1234567890", "Test Book", "Test Author", 2023);
        assertEquals("General", bookWithoutGenre.getGenre());
    }

    @Test
    @DisplayName("Debe permitir cambiar género del libro")
    void testSetBookGenre() {
        book1.setGenre("Ficción");
        assertEquals("Ficción", book1.getGenre());
    }
}
