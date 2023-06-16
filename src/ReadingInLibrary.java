import java.time.LocalDate;

public class ReadingInLibrary {
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    /**
     Creates a new ReadingInLibrary object with the given book, member, and borrow date.
     @param book the book being read in the library
     @param member the member reading the book in the library
     @param borrowDate the date the member started reading the book
     */
    public ReadingInLibrary(Book book, Member member, LocalDate borrowDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        member.setReadingInLibrary(true);
    }
    /**
     Returns the book being read in the library.
     @return the book being read in the library
     */
    public Book getBook() {
        return book;
    }
    /**
     Returns the member reading the book in the library.
     @return the member reading the book in the library
     */
    public Member getMember() {
        return member;
    }
    /**
     Returns the date the member started reading the book.
     @return the date the member started reading the book
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
