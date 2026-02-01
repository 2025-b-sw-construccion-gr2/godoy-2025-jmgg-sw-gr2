package com.curso;

import com.curso.model.Book;
import com.curso.service.LibraryService;

import java.util.List;
import java.util.Optional;

/**
 * Clase principal que demuestra todas las funcionalidades del sistema de biblioteca.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE GESTIÓN DE BIBLIOTECA - DEMO              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Crear instancia del servicio
        LibraryService library = new LibraryService();

        // ============================================
        // PASO 1: Crear y agregar libros
        // ============================================
        printSection("1. CREAR Y AGREGAR LIBROS");
        addBooks(library);

        // ============================================
        // PASO 2: Mostrar todos los libros
        // ============================================
        printSection("2. MOSTRAR TODOS LOS LIBROS");
        displayAllBooks(library);

        // ============================================
        // PASO 3: Búsqueda por ISBN
        // ============================================
        printSection("3. BÚSQUEDA POR ISBN");
        searchByIsbn(library);

        // ============================================
        // PASO 4: Búsqueda por Autor
        // ============================================
        printSection("4. BÚSQUEDA POR AUTOR");
        searchByAuthor(library);

        // ============================================
        // PASO 5: Búsqueda por Género (NUEVA FUNCIONALIDAD)
        // ============================================
        printSection("5. BÚSQUEDA POR GÉNERO (NUEVA)");
        searchByGenre(library);

        // ============================================
        // PASO 6: Libros Disponibles por Género
        // ============================================
        printSection("6. LIBROS DISPONIBLES POR GÉNERO");
        displayAvailableByGenre(library);

        // ============================================
        // PASO 7: Préstamo de Libros
        // ============================================
        printSection("7. PRÉSTAMO DE LIBROS");
        borrowBooks(library);

        // ============================================
        // PASO 8: Mostrar estado después de préstamos
        // ============================================
        printSection("8. ESTADO DESPUÉS DE PRÉSTAMOS");
        displayAvailableBooks(library);

        // ============================================
        // PASO 9: Devolver Libros
        // ============================================
        printSection("9. DEVOLVER LIBROS");
        returnBooks(library);

        // ============================================
        // PASO 10: Estadísticas de la Biblioteca
        // ============================================
        printSection("10. ESTADÍSTICAS DE LA BIBLIOTECA");
        displayStatistics(library);

        // ============================================
        // PASO 11: Todos los Géneros Disponibles
        // ============================================
        printSection("11. GÉNEROS DISPONIBLES EN LA BIBLIOTECA");
        displayAllGenres(library);

        // ============================================
        // RESUMEN FINAL
        // ============================================
        printSection("RESUMEN FINAL");
        System.out.println("✅ Demostración completada exitosamente");
        System.out.println("📚 Total de libros en biblioteca: " + library.getTotalBooks());
        System.out.println("📖 Libros disponibles: " + library.getAvailableBooksCount());
    }

    /**
     * Agrega libros de diferentes géneros a la biblioteca.
     */
    private static void addBooks(LibraryService library) {
        Book[] booksToAdd = {
            new Book("978-3-16-148410-0", "Clean Code", "Robert C. Martin", 2008, "Programación"),
            new Book("978-0-13-110362-7", "The C Programming Language", "Brian Kernighan", 1988, "Programación"),
            new Book("978-0-201-61622-4", "The Pragmatic Programmer", "David Hunt", 1999, "Programación"),
            new Book("978-0-06-112008-4", "To Kill a Mockingbird", "Harper Lee", 1960, "Ficción"),
            new Book("978-0-451-52494-2", "1984", "George Orwell", 1949, "Ficción"),
            new Book("978-0-7432-7356-5", "The Da Vinci Code", "Dan Brown", 2003, "Misterio"),
            new Book("978-0-06-085052-4", "The Alchemist", "Paulo Coelho", 1988, "Autoayuda"),
            new Book("978-0-321-14653-0", "Design Patterns", "Gang of Four", 1994, "Programación"),
            new Book("978-1-491-91205-8", "Introduction to Algorithms", "Cormen", 2009, "Programación"),
            new Book("978-0-8044-2957-1", "Sapiens", "Yuval Harari", 2011, "Historia")
        };

        for (Book book : booksToAdd) {
            boolean added = library.addBook(book);
            String status = added ? "✅ Agregado" : "❌ Duplicado";
            System.out.printf("%-60s %s\n", book.getTitle(), status);
        }
        System.out.println();
    }

    /**
     * Muestra todos los libros de la biblioteca.
     */
    private static void displayAllBooks(LibraryService library) {
        System.out.printf("📚 Total de libros: %d\n", library.getTotalBooks());
        System.out.println(repeatString("─", 80));
        printBookTable(library.getAllBooks());
    }

    /**
     * Busca y muestra un libro por ISBN.
     */
    private static void searchByIsbn(LibraryService library) {
        String isbnToSearch = "978-3-16-148410-0";
        Optional<Book> found = library.findBookByIsbn(isbnToSearch);
        if (found.isPresent()) {
            System.out.println("✅ Libro encontrado:");
            printBook(found.get());
        } else {
            System.out.println("❌ Libro no encontrado");
        }
        System.out.println();
    }

    /**
     * Busca y muestra libros por autor.
     */
    private static void searchByAuthor(LibraryService library) {
        String authorToSearch = "Robert C. Martin";
        List<Book> found = library.findBooksByAuthor(authorToSearch);
        System.out.printf("📖 Libros de '%s': %d encontrado(s)\n", authorToSearch, found.size());
        printBookTable(found);
    }

    /**
     * Busca y muestra libros por género (NUEVA FUNCIONALIDAD).
     */
    private static void searchByGenre(LibraryService library) {
        String genreToSearch = "Programación";
        List<Book> found = library.findBooksByGenre(genreToSearch);
        System.out.printf("📚 Libros de género '%s': %d encontrado(s)\n", genreToSearch, found.size());
        printBookTable(found);
    }

    /**
     * Muestra libros disponibles por género.
     */
    private static void displayAvailableByGenre(LibraryService library) {
        String genre = "Ficción";
        List<Book> available = library.findAvailableBooksByGenre(genre);
        System.out.printf("📖 Libros disponibles de género '%s': %d\n", genre, available.size());
        printBookTable(available);
    }

    /**
     * Muestra todos los libros disponibles.
     */
    private static void displayAvailableBooks(LibraryService library) {
        List<Book> available = library.getAvailableBooks();
        System.out.printf("📖 Libros disponibles: %d de %d\n", available.size(), library.getTotalBooks());
        System.out.println(repeatString("─", 80));
        printBookTable(available);
    }

    /**
     * Simula préstamo de libros.
     */
    private static void borrowBooks(LibraryService library) {
        String[] isbsnToBorrow = {
            "978-3-16-148410-0",
            "978-0-06-112008-4"
        };

        for (String isbn : isbsnToBorrow) {
            try {
                Book borrowed = library.borrowBook(isbn);
                System.out.printf("✅ Libro prestado: '%s'\n", borrowed.getTitle());
            } catch (IllegalStateException e) {
                System.out.printf("❌ Error: %s\n", e.getMessage());
            }
        }
        System.out.println();
    }

    /**
     * Simula devolución de libros.
     */
    private static void returnBooks(LibraryService library) {
        String[] isbnsToReturn = {
            "978-3-16-148410-0",
            "978-0-06-112008-4"
        };

        for (String isbn : isbnsToReturn) {
            try {
                library.returnBook(isbn);
                Optional<Book> book = library.findBookByIsbn(isbn);
                if (book.isPresent()) {
                    System.out.printf("✅ Libro devuelto: '%s'\n", book.get().getTitle());
                }
            } catch (IllegalStateException e) {
                System.out.printf("❌ Error: %s\n", e.getMessage());
            }
        }
        System.out.println();
    }

    /**
     * Muestra estadísticas de la biblioteca.
     */
    private static void displayStatistics(LibraryService library) {
        System.out.printf("📊 Total de libros en biblioteca: %d\n", library.getTotalBooks());
        System.out.printf("📖 Libros disponibles: %d\n", library.getAvailableBooksCount());
        System.out.printf("📤 Libros prestados: %d\n", library.getTotalBooks() - library.getAvailableBooksCount());
        System.out.printf("📈 Porcentaje disponible: %.1f%%\n", 
            (library.getAvailableBooksCount() * 100.0) / library.getTotalBooks());
        System.out.println();
    }

    /**
     * Muestra todos los géneros disponibles.
     */
    private static void displayAllGenres(LibraryService library) {
        List<String> genres = library.getAllGenres();
        System.out.printf("🏷️  Géneros disponibles: %d\n", genres.size());
        for (String genre : genres) {
            int total = library.findBooksByGenre(genre).size();
            int available = library.findAvailableBooksByGenre(genre).size();
            System.out.printf("  • %-20s: %d total, %d disponible(s)\n", genre, total, available);
        }
        System.out.println();
    }

    /**
     * Imprime una tabla de libros.
     */
    private static void printBookTable(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("❌ No hay libros para mostrar\n");
            return;
        }

        System.out.println("┌─────────────────────────────────────────────────────────────────────────────┬─────────────┐");
        System.out.println("│ Título                                                                  │ Disponible  │");
        System.out.println("├─────────────────────────────────────────────────────────────────────────────┼─────────────┤");

        for (Book book : books) {
            String available = book.isAvailable() ? "✅ Sí" : "❌ No";
            System.out.printf("│ %-75s │ %-11s │\n", truncate(book.getTitle(), 75), available);
        }

        System.out.println("└─────────────────────────────────────────────────────────────────────────────┴─────────────┘");
        System.out.println();
    }

    /**
     * Imprime un libro individual.
     */
    private static void printBook(Book book) {
        System.out.println(repeatString("─", 80));
        System.out.printf("ISBN: %s\n", book.getIsbn());
        System.out.printf("Título: %s\n", book.getTitle());
        System.out.printf("Autor: %s\n", book.getAuthor());
        System.out.printf("Año: %d\n", book.getPublicationYear());
        System.out.printf("Género: %s\n", book.getGenre());
        System.out.printf("Disponible: %s\n", book.isAvailable() ? "✅ Sí" : "❌ No");
        System.out.println(repeatString("─", 80));
        System.out.println();
    }

    /**
     * Imprime un encabezado de sección.
     */
    private static void printSection(String title) {
        System.out.println("\n" + repeatString("═", 80));
        System.out.printf("║ %s%s║\n", title, repeatString(" ", 80 - title.length() - 2));
        System.out.println(repeatString("═", 80));
    }

    /**
     * Repite un string N veces (compatible con Java 8).
     */
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * Trunca un texto a una longitud máxima.
     */
    private static String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
}
