import java.io.File;
import java.io.FileWriter;

/**
 This class represents the main entry point of the program. It takes in two arguments from the command line,
 a path to an input file and a name for the output file. It reads the input file using a FileController object,
 adds the data to a Library object, and writes the data to the output file using a FileWriter object.
 */
public class Main {
    /**
     * Reads the input file, creates a Library object, and writes the data to the output file.
     *
     * @param args The command line arguments passed to the program.
     *             The first argument is the path to the input file.
     *             The second argument is the name for the output file.
     * @throws Exception If an error occurs while reading or writing files.
     */
    public static void main(String[] args) throws Exception {
        String path = args[0];
        File file = new File(path);
        FileController fileController = new FileController(file);
        String outputName = args[1];
        Library library = new Library();
        FileWriter fileWriter = new FileWriter(outputName);
        fileController.readInputFile(fileWriter);
        fileWriter.close();
    }
}