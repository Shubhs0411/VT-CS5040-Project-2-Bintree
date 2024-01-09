
import java.io.ByteArrayOutputStream;
import java.io.IOException;
// import java.io.File;
// import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * The `SemSearchTest` class contains a set of unit tests for the `SemSearch`
 * class,
 * which is responsible for managing seminar records and performing various
 * operations
 * based on user commands. This class extends the `TestCase` class from a
 * testing framework
 * and provides test methods to verify functionality of `SemSearch` methods
 * under
 * different scenarios.
 *
 * The tests cover cases such as incorrect input parameters, validation of
 * world size,
 * parsing files, checking if a value is a power of two, handling unknown
 * commands, and more.
 * Additionally, it tests correctness of parsing and searching operations on
 * seminar records.
 *
 * This class helps ensure the correctness of `SemSearch` class by executing
 * various test
 * cases and verifying expected behavior.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
public class SemSearchTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Tests the behavior when an incorrect number of arguments is provided.
     */
    public void testIncorrectNumberOfArguments() {
        // no. of input arguments less than 2
        String[] args = new String[] { "128" };

        // Redirect standard error to capture the printed message
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream errorStream = System.err;
        System.setErr(new PrintStream(outputStream));

        // Call the method
        SemSearch.main(args);

        // Assert that the correct error message is printed
        assertEquals("Incorrect number of arguments: provide two arguments in"
            + " the correct format.", outputStream.toString().trim());

        // Reset the standard error
        System.setErr(errorStream);
    }


    /**
     * Tests if the world size provided is a power of two.
     */
    public void testIsPowerOfTwoWorldSize() {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setErr(new PrintStream(result));
        String[] args = { "125", "input.txt" };
        SemSearch.main(args);
        String expectedOutput =
            "Incorrect world-size: The world-size should be a power of two.\n";
        assertEquals(expectedOutput, result.toString());
    }


    /**
     * To check if the data type of worldsize is integer or not
     */
    public void testIsWorldSizeInteger() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setErr(new PrintStream(result));
        String[] args = { "h", "input.txt" };
        SemSearch.main(args);
        String expectedOutput =
            "Incorrect world-size: This is not a valid integer.\n";
        assertEquals(expectedOutput, result.toString());
    }


    /**
     * Test for function isPowerOfTwo
     */
    public void testIsPowerOfTwo() {
        assertTrue(SemSearch.isPowerOfTwo(1)); // 2^0 = 1
        assertTrue(SemSearch.isPowerOfTwo(2)); // 2^1 = 2
        assertTrue(SemSearch.isPowerOfTwo(64)); // 2^6 = 64
        assertFalse(SemSearch.isPowerOfTwo(0)); // Not a power of two
        assertFalse(SemSearch.isPowerOfTwo(35)); // Not a power of two
        assertFalse(SemSearch.isPowerOfTwo(-64)); // Negative integer not a
                                                  // power of two
        assertFalse(SemSearch.isPowerOfTwo(-4)); // Negative integer not power
                                                 // of two
        assertFalse(SemSearch.isPowerOfTwo(-7)); // Negative integer not power
                                                 // of two
    }


    /**
     * Test for checking the parsing of invalid file
     */
    public void testFileParameterNotValid() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setErr(new PrintStream(result));
        String[] args = { "64", "InvalidFile.txt" };
        SemSearch.main(args);
        String expectedOutput =
            "Error: File not found. Please enter a valid input filepath\n";
        assertEquals(expectedOutput, result.toString());
    }

    /*
     * Test for default case i.e. unknown command
     */
// public void testUnknownCommand() {
// // Create a test file with an unknown command and test the parsing
// // Ensure that the method prints the appropriate error message and returns
// false
// }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx() {
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
        // SemSearch.main(null);
    }


    /**
     * 
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * 
     * @return the string
     * 
     * @throws IOException
     * 
     */

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Tests the parsing of a file with seminar records and verifies the
     * program's output
     * matches the expected reference output.
     *
     * @throws IOException
     *             if there is an I/O error while reading the reference output
     *             file.
     */
    public void testparserfull() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "Insert_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("Insert_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Tests the search functionality of the program using a specified input
     * file and
     * compares the program's output to a reference output file.
     *
     * @throws IOException
     *             if there is an I/O error while reading the reference output
     *             file.
     */
    public void testsearch() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "Search_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("Search_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Tests the program's search functionality with multiple search queries
     * using a specified
     * input file and compares the program's output to a reference output file.
     *
     * @throws IOException
     *             if there is an I/O error while reading the reference output
     *             file.
     */
    public void testsearchAll() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "Search_all_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("Search_all_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }

// public void testEmptyBST() throws IOException {
// String[] args = new String[2];
// args[0] = "1024";
// args[1] = "print_input.txt";
// SemSearch.main(args);
// String output = systemOut().getHistory();
// String referenceOutput = readFile("print_output.txt");
// assertFuzzyEquals(referenceOutput, output);
// }


    /**
     * Tests the program with valid input arguments and ensures that no error
     * messages
     * are printed to the standard error stream.
     */
    public void testValidArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "128", "print_input.txt" };
        SemSearch.main(args);
        // Restore the standard error stream
        System.setErr(System.err);
        String errorMessage = outContent.toString().trim();
        assertEquals("", errorMessage);
    }


    /**
     * Tests the delete functionality of the program using a specified input
     * file and
     * compares the program's output to a reference output file.
     *
     * @throws IOException
     *             if there is an I/O error while reading the reference output
     *             file.
     */
    public void testDelete() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "Delete_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("Delete_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Tests the program's delete functionality with multiple delete queries
     * using a specified
     * input file and compares the program's output to reference output file.
     *
     * @throws IOException
     *             if there is an I/O error while reading the reference output
     *             file.
     */

    public void testDeleteMultiple() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "Delete_input_multiple.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("Delete_output_multiple.txt");
        assertFuzzyEquals(referenceOutput, output);
    }

    /**
     * This method tests the behavior of the `Bintree` class by running it
     * with specific
     * arguments and comparing its output to a reference output file.
     *
     * @throws IOException
     *             if an I/O error occurs during the test
     */
    public void testBintree() throws IOException {
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "Bintree_input.txt";
        SemSearch.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("Bintree_output.txt");
        assertFuzzyEquals(referenceOutput, output);
    }

    /**
     * This method tests the behavior of the `Bintree` class by running it
     * with specific
     * arguments and comparing its output to a reference output file.
     *
     * @throws IOException
     *             if an I/O error occurs during the test
     */
//    public void testBintree() throws IOException {
//        String[] args = new String[2];
//        args[0] = "128";
//        args[1] = "Bintree_input.txt";
//        SemSearch.main(args);
//        String output = systemOut().getHistory();
//        String referenceOutput = readFile("Bintree_output.txt");
//        assertFuzzyEquals(referenceOutput, output);
//    }


    /**
     * This method tests the behavior of the `Bintree` class by running it
     * with specific
     * arguments and comparing its output to a reference output file.
     *
     * @throws IOException
     *             if an I/O error occurs during the test
     */
//    public void testBintreeComplete() throws IOException {
//        String[] args = new String[2];
//        args[0] = "128";
//        args[1] = "Bintree_complete_input.txt";
//        SemSearch.main(args);
//        String output = systemOut().getHistory();
//        String referenceOutput = readFile("Bintree_complete_output.txt");
//        assertFuzzyEquals(referenceOutput, output);
//    }


}
