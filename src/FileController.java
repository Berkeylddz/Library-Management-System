import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class FileController {
    private File path;
    public FileController(File path){
        this.path = path;
    }

    /**
     Reads the input file and performs the necessary operations based on the commands provided in the file.
     The input file is assumed to be in the following format:
     addBook bookTitle
     addMember memberName
     borrowBook bookId memberId borrowDate
     readInLibrary bookId memberId borrowDate
     returnBook bookId memberId returnDate
     getTheHistory
     extendBook bookId memberId extendDate
     @param fileWriter the FileWriter object used to write the output to a file
     @throws Exception if there is an error reading or parsing the input file, or if any of the operations fail
    */
    public void  readInputFile(FileWriter fileWriter) throws  Exception{
        Scanner sc = new Scanner(path);
        Library library = new Library();
        while (sc.hasNextLine()){
            String[] info = sc.nextLine().split("\t");
            if(info[0].equals("addBook")){
                library.addBook(info[1],fileWriter);
            }
            else if (info[0].equals("addMember")) {
                library.addMember(info[1],fileWriter);
            }
            else if (info[0].equals("borrowBook")) {
                int bookId = Integer.parseInt(info[1]);
                int memberId = Integer.parseInt(info[2]);
                LocalDate localDate = LocalDate.parse(info[3]);
                library.borrowBook(bookId,memberId,localDate,fileWriter);

            }
            else if(info[0].equals("readInLibrary")){
                int bookId = Integer.parseInt(info[1]);
                int memberId = Integer.parseInt(info[2]);
                LocalDate localDate = LocalDate.parse(info[3]);
                library.readInLibrary(bookId,memberId,localDate,fileWriter);
            }
            else if (info[0].equals("returnBook")) {
                int bookId = Integer.parseInt(info[1]);
                int memberId = Integer.parseInt(info[2]);
                LocalDate localDate = LocalDate.parse(info[3]);
                library.returnBook(bookId,memberId,localDate,fileWriter,library);
            }
            else if (info[0].equals("getTheHistory")) {
                library.getTheHistory(fileWriter);
            }
            else if(info[0].equals("extendBook")){
                int bookId = Integer.parseInt(info[1]);
                int memberId = Integer.parseInt(info[2]);
                LocalDate localDate = LocalDate.parse(info[3]);
                library.extendBook(bookId,memberId,localDate,fileWriter);
            }
        }
    }
}
