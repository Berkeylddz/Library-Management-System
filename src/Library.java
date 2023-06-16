import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Borrowing> borrowings;
    private List<ReadingInLibrary> readingInLibraries;
    private int bookCount=0;
    private int memberCount=0;

    public Library(){
        this.books= new ArrayList<>();
        this.members = new ArrayList<>();
        this.borrowings = new ArrayList<>();
        this.readingInLibraries = new ArrayList<>();
        this.bookCount=0;

    }

    /**
     Adds a new book to the library based on the specified type.
     @param type the type of book to add ("P" for Printed, "H" for Handwritten)
     @param fileWriter a FileWriter object to write log messages to
     @throws IOException if an I/O error occurs while writing to the log file
     */
    public void addBook(String type, FileWriter fileWriter) throws IOException {
        bookCount+=1;
        Book book = null;

        if(type.equals("P")){
            book = new PrintedBook(bookCount);
            String id = Integer.toString(book.getId());
            fileWriter.write("Created new book: Printed [id: "+id+"]\n");
        }
        else if (type.equals("H")) {
            book = new HandwrittenBook(bookCount);
            String id = Integer.toString(book.getId());
            fileWriter.write("Created new book: Handwritten [id: "+id+"]\n");
        }
        this.books.add(book);
    }

    /**
     Adds a new member to the library system based on the provided member type.
     Increments the member count and creates a new instance of the corresponding member type.
     @param type The type of member to add. Valid types are "A" for academic and "S" for student.
     @param fileWriter A FileWriter object used to write log messages to a log file.
     @throws IOException If an I/O error occurs while writing to the log file.
     */
    public void addMember(String type, FileWriter fileWriter) throws IOException{
        memberCount+=1;
        Member member=null;

        if(type.equals("A")){
            member = new Academic(memberCount);
            String id = Integer.toString(member.getId());
            fileWriter.write("Created new member: Academic [id: "+id+"]\n");
        }
        else if (type.equals("S")) {
            member = new Student(memberCount);
            String id = Integer.toString(member.getId());
            fileWriter.write("Created new member: Student [id: "+id+"]\n");

        }
        this.members.add(member);
    }

    /**
     This method is used to borrow a book from the library. It takes the book id, member id, and the current date as input
     and uses them to find the book and member in the library. If the member is a student, it checks if they have exceeded
     their borrowing limit of 2 books. If the book is available and the member is eligible to borrow it, the method
     creates a new borrowing object and adds it to the list of borrowings. It also sets the book's borrow status to true,
     its due date to the current date, and the member's reading status to true.
     @param bookId the id of the book being borrowed
     @param memberId the id of the member borrowing the book
     @param localDate the current date
     @param fileWriter a FileWriter object used to write to the library's log file
     @throws IOException if there is an error writing to the log file
     */
    public void borrowBook(int bookId, int memberId, LocalDate localDate,FileWriter fileWriter) throws IOException {
        Book chosenBook=null;
        Member chosenMember=null;
        for(Book book: books){
            if(book.getId()==bookId){
                chosenBook = book;
            }
        }
        for(Member member:members){
            if(member.getId()==memberId){
                chosenMember = member;
            }
        }
        if(chosenBook instanceof HandwrittenBook){
            fileWriter.write("You cannot borrow this book!\n");
        }
        else {
            if(chosenMember instanceof Student){
                ((Student) chosenMember).setTakenBooks(1);
            }

            if(chosenBook == null || chosenMember==null || chosenBook.isBorrowed()){
                fileWriter.write("You cannot borrow this book!\n");
            }
            else {

                if(chosenMember instanceof Student){
                    if(((Student) chosenMember).getTakenBooks()>2){
                        fileWriter.write("You have exceeded the borrowing limit!\n");
                    }
                    else {

                        chosenBook.setBorrowed(true);
                        chosenBook.setDueDate(localDate.plusDays(7));
                        chosenMember.setReading(true);
                        Borrowing borrowing = new Borrowing(chosenBook,chosenMember,localDate);
                        borrowings.add(borrowing);
                        fileWriter.write("The book ["+ bookId +"] was borrowed by member ["+memberId+"] at "+localDate+"\n");
                    }
                }
                else {
                    chosenBook.setBorrowed(true);
                    chosenBook.setDueDate(localDate.plusDays(14));
                    chosenMember.setReading(true);
                    Borrowing borrowing = new Borrowing(chosenBook,chosenMember,localDate);
                    borrowings.add(borrowing);
                    fileWriter.write("The book ["+ bookId +"] was borrowed by member ["+memberId+"] at "+localDate+"\n");
                }
            }
        }
    }

    /**
     Returns a borrowed book to the library and updates the borrowing status of the member and book.
     If the book is returned after its due date, the method calculates the fee to be paid by the member.
     If the book is returned on time, the fee is 0.
     @param bookId the id of the book to be returned
     @param memberId the id of the member returning the book
     @param returnDate the date the book is returned
     @param fileWriter the FileWriter object to write logs to
     @param library the Library object that holds the book and member lists
     @throws IOException if an I/O error occurs
     */
    public void returnBook(int bookId, int memberId, LocalDate returnDate,FileWriter fileWriter,Library library) throws IOException {
        Book chosenBook=null;
        Member chosenMember=null;

        for(Member member: members){
            if(member.getId()==memberId){
                chosenMember = member;
            }
        }
        for(Book book: books){
            if(book.getId()==bookId){
                chosenBook = book;
            }
        }
        int feeDay=0;
        if(chosenBook.getDueDate().isBefore(returnDate)){
            feeDay = (int) ChronoUnit.DAYS.between(chosenBook.getDueDate(),returnDate);
        }


        try{
            for(Borrowing borrowings1:borrowings){
                if(borrowings1.getBook().getId()==bookId){
                    borrowings1.getBook().setBorrowed(false);
                    borrowings1.getBook().setDueDate(null);
                    borrowings1.getMember().setReading(false);
                    borrowings.remove(borrowings1);

                    fileWriter.write("The book ["+bookId+"] was returned by member ["+memberId+"] at "+returnDate+" Fee: "+feeDay+"\n");
                }
            }

            for(ReadingInLibrary readingInLibrary:readingInLibraries){
                if(readingInLibrary.getBook().getId()==bookId){
                    readingInLibrary.getBook().setBorrowed(false);
                    readingInLibrary.getBook().setDueDate(null);
                    readingInLibrary.getMember().setReading(false);
                    readingInLibraries.remove(readingInLibrary);
                    fileWriter.write("The book ["+bookId+"] was returned by member ["+memberId+"] at "+returnDate+" Fee: 0\n");

                }
            }
        }
        catch (Exception e){
            ;
        }


    }

    /**
     Allows a member to read a book in the library.
     @param bookId the ID of the book to be read
     @param memberId the ID of the member who will read the book
     @param currentDate the current date when the book is being read
     @param fileWriter the FileWriter object to write the operation results to a file
     @throws IOException if there is an error while writing to the file
     */
    public void readInLibrary(int bookId, int memberId, LocalDate currentDate, FileWriter fileWriter) throws IOException {
        Book chosenBook=null;
        Member chosenMember=null;
        for(Book book: books){
            if(book.getId()==bookId){
                chosenBook = book;
            }
        }
        for(Member member:members){
            if(member.getId()==memberId){
                chosenMember = member;
            }
        }

        if(chosenMember instanceof Student && chosenBook instanceof HandwrittenBook){
            fileWriter.write("Students can not read handwritten books!\n");
        }

        else if(chosenBook.isBorrowed()){
            fileWriter.write("You can not read this book!\n");
        }
        else {
            chosenBook.setBorrowed(true);
            chosenBook.setDueDate(currentDate);
            chosenMember.setReading(true);
            ReadingInLibrary readingInLibrary = new ReadingInLibrary(chosenBook,chosenMember,currentDate);
            readingInLibraries.add(readingInLibrary);
            fileWriter.write("The book ["+bookId+"] was read in library by member ["+memberId+"] at "+currentDate+"\n");
        }
    }

    /**
     Writes a summary of the library's history to a file.
     The summary includes:
     The number of students and their IDs
     The number of academics and their IDs
     The number of printed books and their IDs
     The number of handwritten books and their IDs
     The number of borrowed books and their details (book ID, member ID, borrow date)
     The number of books read in the library and their details (book ID, member ID, borrow date)
     @param fileWriter the FileWriter to write the summary to
     @throws IOException if there is an error writing to the FileWriter
     */
    public void getTheHistory(FileWriter fileWriter) throws IOException {
        fileWriter.write("History of library:\n");
        int studentSize=0;
        int academicSize=0;

        for(Member member:members){
            if(member instanceof Student){
                studentSize++;
            }
            else {
                academicSize++;
            }
        }

        fileWriter.write("\nNumber of students: "+studentSize+"\n");
        for(Member member:members){
            if(member instanceof Student){
                fileWriter.write("Student [id: "+member.getId()+"]\n");
            }
        }

        fileWriter.write("\nNumber of academics: "+academicSize+"\n");
        for (Member member:members){
            if(member instanceof Academic){
                fileWriter.write("Academic [id: "+member.getId()+"]\n");
            }
        }

        int printedBook = 0;
        int handwrittenBook = 0;
        for(Book book:books){
            if(book instanceof PrintedBook){
                printedBook++;
            }
            else {
                handwrittenBook++;
            }
        }

        fileWriter.write("\nNumber of printed books: "+printedBook+"\n");
        for (Book book:books){
            if(book instanceof PrintedBook){
                fileWriter.write("Printed [id: "+book.getId()+"]\n");
            }
        }

        fileWriter.write("\nNumber of handwritten books: "+handwrittenBook+"\n");
        for(Book book:books){
            if(book instanceof HandwrittenBook){
                fileWriter.write("Handwritten [id: "+book.getId()+"]\n");
            }
        }

        fileWriter.write("\nNumber of borrowed books: "+borrowings.size()+"\n");
        for(Borrowing borrowing: borrowings){
            fileWriter.write("The book ["+borrowing.getBook().getId()
                    +"] was borrowed by member ["+borrowing.getMember().getId()+"] at "+borrowing.getBorrowDate()+"\n");
        }

        fileWriter.write("\nNumber of books read in library: "+readingInLibraries.size()+"\n");
        for(ReadingInLibrary readingInLibrary:readingInLibraries){
            fileWriter.write("The book ["+readingInLibrary.getBook().getId()
                    +"] was read in library by member ["+readingInLibrary.getMember().getId()+"] at "
                    +readingInLibrary.getBorrowDate()+"\n");
        }
    }

    /**
     Extends the deadline of a borrowed book by a member.
     @param bookId the ID of the book to be extended
     @param memberId the ID of the member who wants to extend the book
     @param localDate the current date when the extension is made
     @param fileWriter the FileWriter object to write the result to a file
     @throws IOException if an I/O error occurs while writing to the file
     */
    public void extendBook(int bookId, int memberId, LocalDate localDate, FileWriter fileWriter) throws IOException {
        Book chosenBook=null;
        Member chosenMember=null;
        for(Book book: books){
            if(book.getId()==bookId){
                chosenBook = book;
            }
        }
        for(Member member:members){
            if(member.getId()==memberId){
                chosenMember = member;
            }
        }

        if(chosenBook.isExtended()){
            fileWriter.write("You cannot extend the deadline!\n");
        }
        else {
            chosenBook.setExtended(true);
            fileWriter.write("The deadline of book ["+chosenBook.getId()
                    +"] was extended by member ["+chosenMember.getId()+"] at "+localDate+"\n");

            LocalDate result = localDate.plusDays(7);
            chosenBook.setDueDate(result);
            fileWriter.write("New deadline of book ["+chosenBook.getId()+"] is "+chosenBook.getDueDate()+"\n");
        }

    }
}


