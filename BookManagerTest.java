import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Week 2 BookManager CRUD functionality.
 * The tests focus on business logic rather than console interaction.
 */
class BookManagerTest {

    private BookManager manager;

    @BeforeEach
    void setUp() {
        manager = new BookManager();
    }

    private Book sampleBook() {
        return new Book("Clean Code", "Robert C. Martin", "9780132350884", 2008);
    }

    @Test
    void addBookShouldStoreBook() {
        Book book = sampleBook();
        manager.addBook(book);

        assertEquals(1, manager.getAllBooks().size());
        assertSame(book, manager.findBookByIsbn("9780132350884"));
    }

    @Test
    void addMultipleBooksShouldStoreAllBooks() {
        manager.addBook(sampleBook());
        manager.addBook(new Book("Effective Java", "Joshua Bloch", "9780134685991", 2018));

        assertEquals(2, manager.getAllBooks().size());
    }

    @Test
    void addDuplicateIsbnShouldThrowException() {
        manager.addBook(sampleBook());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> manager.addBook(new Book("Another Book", "Another Author", "9780132350884", 2020))
        );

        assertEquals("A book with this ISBN already exists.", exception.getMessage());
    }

    @Test
    void getAllBooksShouldReturnEmptyListInitially() {
        assertTrue(manager.getAllBooks().isEmpty());
    }

    @Test
    void getAllBooksShouldReturnCopyOfInternalCollection() {
        manager.addBook(sampleBook());
        List<Book> books = manager.getAllBooks();

        books.clear();

        assertEquals(1, manager.getAllBooks().size());
    }

    @Test
    void findExistingBookShouldReturnBook() {
        manager.addBook(sampleBook());

        Book result = manager.findBookByIsbn("9780132350884");

        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
    }

    @Test
    void findBookShouldBeCaseInsensitiveForIsbn() {
        manager.addBook(new Book("Test Book", "Author", "ABC123", 2020));

        assertNotNull(manager.findBookByIsbn("abc123"));
    }

    @Test
    void findMissingBookShouldReturnNull() {
        assertNull(manager.findBookByIsbn("DOES-NOT-EXIST"));
    }

    @Test
    void updateExistingBookShouldChangeMutableFields() {
        manager.addBook(sampleBook());

        manager.updateBook("9780132350884", "Clean Code 2nd Edition", "Robert Martin", 2025);

        Book updated = manager.findBookByIsbn("9780132350884");
        assertEquals("Clean Code 2nd Edition", updated.getTitle());
        assertEquals("Robert Martin", updated.getAuthor());
        assertEquals(2025, updated.getPublicationYear());
        assertEquals("9780132350884", updated.getIsbn());
    }

    @Test
    void updateMissingBookShouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> manager.updateBook("MISSING", "Title", "Author", 2020)
        );

        assertEquals("Book not found.", exception.getMessage());
    }

    @Test
    void deleteExistingBookShouldRemoveBook() {
        manager.addBook(sampleBook());

        manager.deleteBook("9780132350884");

        assertNull(manager.findBookByIsbn("9780132350884"));
        assertTrue(manager.getAllBooks().isEmpty());
    }

    @Test
    void deleteMissingBookShouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> manager.deleteBook("MISSING")
        );

        assertEquals("Book not found.", exception.getMessage());
    }

    @Test
    void deleteOneBookShouldKeepOtherBooks() {
        manager.addBook(sampleBook());
        manager.addBook(new Book("Effective Java", "Joshua Bloch", "9780134685991", 2018));

        manager.deleteBook("9780132350884");

        assertEquals(1, manager.getAllBooks().size());
        assertNotNull(manager.findBookByIsbn("9780134685991"));
    }

    @Test
    void updateShouldPreserveBookCount() {
        manager.addBook(sampleBook());

        manager.updateBook("9780132350884", "Updated", "Updated Author", 2024);

        assertEquals(1, manager.getAllBooks().size());
    }

    @Test
    void bookToStringShouldContainImportantDetails() {
        Book book = sampleBook();
        String text = book.toString();

        assertTrue(text.contains("9780132350884"));
        assertTrue(text.contains("Clean Code"));
        assertTrue(text.contains("Robert C. Martin"));
        assertTrue(text.contains("2008"));
    }

    @Test
    void bookFieldsShouldBeUpdatedThroughSetters() {
        Book book = sampleBook();

        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setPublicationYear(2020);

        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals(2020, book.getPublicationYear());
    }

    @Test
    void isbnShouldRemainUnchangedDuringUpdate() {
        manager.addBook(sampleBook());

        manager.updateBook("9780132350884", "Changed", "Changed Author", 2024);

        assertEquals("9780132350884", manager.getAllBooks().get(0).getIsbn());
    }

    @Test
    void duplicateCheckShouldBeCaseInsensitive() {
        manager.addBook(new Book("Book A", "Author A", "ABC123", 2020));

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addBook(new Book("Book B", "Author B", "abc123", 2021))
        );
    }
}
