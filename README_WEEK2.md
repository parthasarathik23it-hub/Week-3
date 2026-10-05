# Library Book Inventory System

## Week 2 Task — Java Application Development

A command-line Java application that manages a library's book inventory using in-memory Java collections.

## Features

- Add a new book
- List all books
- Update existing book information
- Delete a book
- Search books internally by ISBN
- Prevent duplicate ISBNs
- Validate empty input
- Validate numeric input
- Validate publication year
- Handle invalid operations with meaningful error messages

## Book Properties

Each book contains:

- Title
- Author
- ISBN
- Publication Year

ISBN is treated as the unique identifier for each book.

## Technology

- Java
- Java Collections Framework
- ArrayList
- Scanner
- Exception handling

## Project Structure

```text
library-book-inventory/
├── src/
│   ├── Book.java
│   ├── BookManager.java
│   └── Main.java
└── README.md
```

## How to Compile

Open a terminal in the project folder and run:

```bash
javac -d out src/*.java
```

## How to Run

```bash
java -cp out Main
```

## Menu

```text
1. Add Book
2. List All Books
3. Update Book
4. Delete Book
5. Exit
```

## Implementation Approach

The application uses an `ArrayList<Book>` to store book objects in memory. `Book` represents the entity, `BookManager` handles CRUD operations, and `Main` provides the command-line interface.

The application uses ISBN as the unique identifier. Before adding a book, the manager checks whether the ISBN already exists. Update and delete operations also use ISBN to locate the required book.

Input validation is handled in the CLI layer, while business validation such as duplicate ISBN and missing books is handled by `BookManager`.

## Limitations

The application stores data only in memory. Therefore, all books are lost when the application exits. A future version could use MySQL or another persistent database.

## Future Enhancements

- Search by title or author
- Borrow and return functionality
- File/database persistence
- User authentication
- Book categories
- Sorting and filtering
- Unit testing with JUnit
- Spring Boot REST API version
