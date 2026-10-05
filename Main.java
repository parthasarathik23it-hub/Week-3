import java.util.List;
import java.util.Scanner;

/**
 * Entry point of the Library Book Inventory application.
 * Provides a menu-driven command-line interface.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("======================================");
        System.out.println("     LIBRARY BOOK INVENTORY SYSTEM");
        System.out.println("======================================");

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> listBooks();
                    case 3 -> updateBook();
                    case 4 -> deleteBook();
                    case 5 -> {
                        running = false;
                        System.out.println("Thank you for using the Library System.");
                    }
                    default -> System.out.println("Invalid choice. Please select 1-5.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Exit");
        System.out.println("----------------------------");
    }

    private static void addBook() {
        String title = readRequiredString("Enter title: ");
        String author = readRequiredString("Enter author: ");
        String isbn = readRequiredString("Enter ISBN: ");
        int year = readPublicationYear();

        Book book = new Book(title, author, isbn, year);
        bookManager.addBook(book);

        System.out.println("Book added successfully.");
    }

    private static void listBooks() {
        List<Book> books = bookManager.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books found in the inventory.");
            return;
        }

        System.out.println("\n----------- BOOKS -----------");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void updateBook() {
        String isbn = readRequiredString("Enter ISBN of the book to update: ");

        if (bookManager.findBookByIsbn(isbn) == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        String title = readRequiredString("Enter new title: ");
        String author = readRequiredString("Enter new author: ");
        int year = readPublicationYear();

        bookManager.updateBook(isbn, title, author, year);
        System.out.println("Book updated successfully.");
    }

    private static void deleteBook() {
        String isbn = readRequiredString("Enter ISBN of the book to delete: ");
        bookManager.deleteBook(isbn);
        System.out.println("Book deleted successfully.");
    }

    /**
     * Reads a valid integer and keeps asking until the user enters one.
     */
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a non-empty string.
     */
    private static String readRequiredString(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    /**
     * Reads and validates a publication year.
     */
    private static int readPublicationYear() {
        while (true) {
            int year = readInt("Enter publication year: ");

            if (year >= 1000 && year <= 2026) {
                return year;
            }

            System.out.println("Enter a valid publication year between 1000 and 2026.");
        }
    }
}
