# Week 3 — Intentional Bug Introduction and Resolution

The Week 3 task requires intentional bug introduction, test-driven detection, debugging, and resolution. The following controlled defects were created temporarily in the working copy. The final source in `src/` contains the corrected implementation.

## Bug 1 — Duplicate ISBN validation removed

### Intentional defect
The uniqueness check inside `BookManager.addBook()` was temporarily removed so that the method directly executed:

```java
books.add(book);
```

### Detection
`BookManagerTest.addDuplicateIsbnShouldThrowException()` failed because the second book with the same ISBN was accepted instead of raising `IllegalArgumentException`.

### Root cause
The business rule requiring ISBN uniqueness was bypassed.

### Fix
Restored the validation:

```java
if (findBookByIsbn(book.getIsbn()) != null) {
    throw new IllegalArgumentException("A book with this ISBN already exists.");
}
books.add(book);
```

### Verification
The duplicate-ISBN test is expected to pass after the fix.

## Bug 2 — Update used an incorrect ISBN lookup

### Intentional defect
The update method was temporarily changed to search for a hard-coded/non-matching ISBN instead of using the method parameter.

### Detection
`updateExistingBookShouldChangeMutableFields()` failed because the requested book could not be located and the expected updated values were not present.

### Root cause
The update operation did not use the ISBN supplied by the caller to locate the target entity.

### Fix
Restored the correct lookup:

```java
Book book = findBookByIsbn(isbn);
```

The method then updates title, author, and publication year only after a matching book is found.

### Verification
The update test and ISBN-preservation test are expected to pass after the fix.

## Bug 3 — Delete operation not removing the selected object

### Intentional defect
The remove statement was temporarily omitted from `deleteBook()`.

### Detection
`deleteExistingBookShouldRemoveBook()` failed because the book was still returned by `findBookByIsbn()` after the delete operation.

### Root cause
The method validated the existence of the book but did not modify the collection.

### Fix
Restored:

```java
books.remove(book);
```

### Verification
The delete test and the test that confirms other books remain in the collection are expected to pass after the fix.

## Debugging Strategy Used

1. Create a focused JUnit test for the expected business rule.
2. Introduce one controlled defect at a time.
3. Run the relevant test and inspect the failure.
4. Trace the failing assertion to the corresponding production method.
5. Identify the violated business rule.
6. Restore the correct implementation.
7. Re-run the affected test and the full test suite.
8. Keep the final source code free of the intentional defects.
