import java.time.LocalDate;
/**
 A class that represents a borrowing instance of a book by a member.
 */
public class Borrowing {
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    /**
     Constructs a new Borrowing instance with the given book, member, and borrow date.
     @param book the book being borrowed.
     @param member the member who is borrowing the book.
     @param borrowDate the date on which the book was borrowed.
     */
    public Borrowing(Book book, Member member, LocalDate borrowDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
    }
    /**
     Returns the book being borrowed.
     @return the book being borrowed.
     */
    public Book getBook() {
        return book;
    }
    /**
     Returns the member who is borrowing the book.
     @return the member who is borrowing the book.
     */
    public Member getMember() {
        return member;
    }
    /**
     Returns the date on which the book was borrowed.
     @return the date on which the book was borrowed.
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
