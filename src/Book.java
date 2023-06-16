import java.time.LocalDate;
/**
 The {@code Book} class represents a book in a library system.
 It is an abstract class, and cannot be instantiated on its own.
 @author [Author Name]
 @version [Date]
 */
public abstract class Book {
    private int id;
    private boolean borrowed;
    private LocalDate dueDate;
    private boolean extended;

    /**
     Constructs a new {@code Book} object with the given ID.
     @param id the unique ID of the book
     */
    public Book(int id) {
        this.id = id;
        this.borrowed = false;
        this.dueDate = null;
        this.extended = false;
    }
    /**
     Returns whether the book has been extended or not.
     @return {@code true} if the book has been extended, {@code false} otherwise
     */
    public boolean isExtended() {
        return extended;
    }
    /**
     Sets whether the book has been extended or not.
     @param extended {@code true} if the book has been extended, {@code false} otherwise
     */
    public void setExtended(boolean extended) {
        this.extended = extended;
    }
    /**
     Returns whether the book is currently borrowed or not.
     @return {@code true} if the book is currently borrowed, {@code false} otherwise
     */
    public boolean isBorrowed() {
        return borrowed;
    }
    /**
     Sets whether the book is currently borrowed or not.
     @param borrowed {@code true} if the book is currently borrowed, {@code false} otherwise
     */
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
    /**
     Returns the due date of the book.
     @return the due date of the book
     */
    public LocalDate getDueDate() {
        return dueDate;
    }
    /**
     Sets the due date of the book.
     @param dueDate the due date of the book
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    /**
     Returns the ID of the book.
     @return the ID of the book
     */
    public int getId() {
        return this.id;
    }
}
