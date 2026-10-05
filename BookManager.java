import java.util.ArrayList;
import java.util.List;

/**
 * Handles CRUD operations for the library inventory.
 * An ArrayList is used to store books in memory as required by the task.
 */
public class BookManager {

    private final List<Book> books = new ArrayList<>();

    /**
     * Adds a new book after checking that its ISBN is unique.
     */
    public void addBook(Book book) {
        if (findBookByIsbn(book.getIsbn()) != null) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }

        books.add(book);
    }

    /**
     * Returns a copy of the current book list.
     * Returning a copy prevents outside code from directly modifying
     * the internal collection.
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Finds a book using its ISBN.
     */
    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Updates an existing book.
     */
    public void updateBook(String isbn, String title, String author, int publicationYear) {
        Book book = findBookByIsbn(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
    }

    /**
     * Deletes a book using its ISBN.
     */
    public void deleteBook(String isbn) {
        Book book = findBookByIsbn(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        books.remove(book);
    }
}
