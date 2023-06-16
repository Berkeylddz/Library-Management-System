import java.time.LocalDate;

/**
 * Represents a student member of the library system.
 * Inherits from the {@link Member} class.
 */
public class Student extends Member {
    private int takenBooks = 0;
    /**
     * Gets the number of books currently taken by the student.
     * @return The number of books taken by the student.
     */
    public int getTakenBooks() {
        return takenBooks;
    }
    /**
     * Sets the number of books taken by the student.
     * @param takenBooks The number of books taken by the student.
     */
    public void setTakenBooks(int takenBooks) {
        this.takenBooks += takenBooks;
    }
    /**
     * Creates a new instance of the Student class with the given ID.
     * @param id The ID of the student.
     */
    public Student(int id) {
        super(id);
    }


}
