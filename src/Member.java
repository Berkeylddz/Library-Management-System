/**
 The abstract class Member represents a library member who can borrow books from the library.
 It contains the member's ID and whether they are currently reading a book or not.
 */
public abstract class Member {
    private int id;
    private boolean isReading;
    private boolean isReadingInLibrary;

    public boolean isReadingInLibrary() {
        return isReadingInLibrary;
    }

    public void setReadingInLibrary(boolean readingInLibrary) {
        isReadingInLibrary = readingInLibrary;
    }

    /**
     Constructs a new Member object with the specified ID and sets their isReading status to false.
     @param id the ID of the member
     */
    public Member(int id) {
        this.id = id;
        this.isReading = false;
    }
    /**
     Returns the ID of the member.
     @return the member's ID
     */
    public int getId() {
        return this.id;
    }
    /**
     Returns whether the member is currently reading a book or not.
     @return true if the member is reading a book, false otherwise
     */
    public boolean isReading() {
        return isReading;
    }
    /**
     Sets the member's isReading status to the specified value.
     @param reading the value to set for the member's isReading status
     */
    public void setReading(boolean reading) {
        isReading = reading;
    }
}
