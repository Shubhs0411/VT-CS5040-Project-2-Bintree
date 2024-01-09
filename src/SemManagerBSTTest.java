import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Scanner;
import student.TestCase;

/**
 * This class contains test cases for the SemManagerBST class, which manages
 * seminar
 * records using binary search trees.
 * It extends the TestCase class from student package for testing purposes.
 *
 * @author Lavisha Goyal
 * @author Shubham Laxmikant Deshmukh
 * @version 1.0
 */
public class SemManagerBSTTest extends TestCase {

    private SemManagerBST semManager;
    private IdBST idBST;
    private CostBST costBST;
    private DateBST dateBST;
    private KeywordBST keywordBST;
    private Bintree binLocation;
    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent =
        new ByteArrayOutputStream();

    /**
     * Set up initial test environment by initializing instances of various
     * data structures
     * used in the SemManagerBST testing. This method is executed before each
     * test case.
     */
    public void setUp() {
        semManager = new SemManagerBST();
        idBST = new IdBST();
        costBST = new CostBST();
        dateBST = new DateBST();
        keywordBST = new KeywordBST();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    /**
     * Test to check if the parameters are in Range 0 to worldsize -1.
     */
    public void testIsInRangeValid() {
        assertTrue(SemManagerBST.isInRange((short)5, 10));
        assertTrue(SemManagerBST.isInRange((short)0, 100));
        assertTrue(SemManagerBST.isInRange((short)100, 200));
        assertTrue(SemManagerBST.isInRange((short)127, 128));
        assertFalse(SemManagerBST.isInRange((short)128, 128));
    }


    /**
     * Test to check if the output is correct if parameters are not in range 0
     * to worldsize -1.
     */
    public void testIsInRangeInvalid() {
        // Test when the parameter is below the valid range (less than 0)
        assertFalse(SemManagerBST.isInRange((short)-1, 10));

        assertFalse(SemManagerBST.isInRange((short)11, 10));

    }

    /**
     * To test the addseminarRecord method in insert command
     */
// public void testAddSeminarRecordValid() {
// // Creating a valid input string and test adding a seminar record
// int worldSize = 1024;
//
// // Create a string with test input data
// String testInput =
// "1\nOverview of HCI Research at VT\n0610051600\n90\n10\n10\n45\n"
// + "HCI Computer_Science VT Virginia_Tech\n"
// + "This seminar will present an overview of HCI research"
// + " at VT\r\n" + "";
//
// // Convert the test input string to an InputStream
// InputStream inputStream = new ByteArrayInputStream(testInput
// .getBytes());
//
// // Redirect standard input to use the InputStream
// System.setIn(inputStream);
//
// // Redirect standard output to capture printed messages
// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
// PrintStream originalOut = System.out;
// System.setOut(new PrintStream(outputStream));
//
// // Create an instance of SemManagerBST
// SemManagerBST semManagerObject = new SemManagerBST();
//
// // Create a Scanner instance with the redirected standard input
// Scanner scanner = new Scanner(System.in);
//
// try {
// // Call the addSeminarRecord method with the Scanner and worldSize
// semManagerObject.addSeminarRecord(scanner, worldSize, idBST,
// costBST, dateBST, keywordBST, binLocation);
//
// // Reset the standard input and output
// System.setIn(System.in);
// System.setOut(originalOut);
//
// // Assert that the printed output matches your expected output
// assertEquals("Successfully inserted record with ID 1\r\n"
// + "ID: 1, Title: Overview of HCI Research at VT\r\n"
// + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
// + "Description: This seminar will present "
// + "an overview of HCI research at VT\r\n"
// + "Keywords: HCI, Computer_Science, VT, Virginia_Tech",
// outputStream.toString().trim());
// }
// catch (Exception e) {
// fail("Exception occurred: " + e.getMessage());
// }
// }


    /**
     * Testing when x is less than valid range
     */
    public void testRangeOfXNegative() {
        // Creating a valid input string and test adding a seminar record
        int worldSize = 1024;

        // Create a string with test input data
        String testInput =
            "1\nOverview of HCI Research at VT\n0610051600\n90\n-1\n0\n45\n"
                + "HCI Computer_Science VT Virginia_Tech\n"
                + "This seminar will present an overview of HCI research at VT"
                + "\r\n" + "";

        // Convert the test input string to an InputStream
        InputStream inputStream = new ByteArrayInputStream(testInput
            .getBytes());

        // Redirect standard input to use the InputStream
        System.setIn(inputStream);

        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create an instance of SemManagerBST
        SemManagerBST semManagerObject = new SemManagerBST();

        // Create a Scanner instance with the redirected standard input
        Scanner scanner = new Scanner(System.in);

        try {
            // Call the addSeminarRecord method with the Scanner and worldSize
            semManagerObject.addSeminarRecord(scanner, worldSize, idBST,
                costBST, dateBST, keywordBST, binLocation);

            // Reset the standard input and output
            System.setIn(System.in);
            System.setOut(originalOut);
            // System.out.println("Insert FAILED - Bad x, y coordinates: -1,
            // 0");

            // Assert that the printed output matches your expected output
            assertEquals("Insert FAILED - Bad x, y coordinates: -1, 0",
                outputStream.toString().trim());
        }
        catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }


    /**
     * Testing when x is greater than valid range
     */
    public void testRangeOfXGreater() {
        // Creating a valid input string and test adding a seminar record
        int worldSize = 1024;

        // Create a string with test input data
        String testInput =
            "1\nOverview of HCI Research at VT\n0610051600\n90\n1025\n0\n45\n"
                + "HCI Computer_Science VT Virginia_Tech\nThis seminar will"
                + " present an overview of HCI research at VT\r\n" + "";

        // Convert the test input string to an InputStream
        InputStream inputStream = new ByteArrayInputStream(testInput
            .getBytes());

        // Redirect standard input to use the InputStream
        System.setIn(inputStream);

        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create an instance of SemManagerBST
        SemManagerBST semManagerObject = new SemManagerBST();

        // Create a Scanner instance with the redirected standard input
        Scanner scanner = new Scanner(System.in);

        try {
            // Call the addSeminarRecord method with the Scanner and worldSize
            semManagerObject.addSeminarRecord(scanner, worldSize, idBST,
                costBST, dateBST, keywordBST, binLocation);

            // Reset the standard input and output
            System.setIn(System.in);
            System.setOut(originalOut);
            // System.out.println("Insert FAILED - Bad x, y coordinates: -1,
            // 0");

            // Assert that the printed output matches your expected output
            assertEquals("Insert FAILED - Bad x, y coordinates: 1025, 0",
                outputStream.toString().trim());
        }
        catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }


    /**
     * Testing when y is less than valid range
     */
    public void testRangeOfYNegative() {
        // Creating a valid input string and test adding a seminar record
        int worldSize = 1024;

        // Create a string with test input data
        String testInput =
            "1\nOverview of HCI Research at VT\n0610051600\n90\n0\n-1\n45\n"
                + "HCI Computer_Science VT Virginia_Tech\nThis seminar will"
                + " present an overview of HCI research at VT\r\n" + "";

        // Convert the test input string to an InputStream
        InputStream inputStream = new ByteArrayInputStream(testInput
            .getBytes());

        // Redirect standard input to use the InputStream
        System.setIn(inputStream);

        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create an instance of SemManagerBST
        SemManagerBST semManagerObject = new SemManagerBST();

        // Create a Scanner instance with the redirected standard input
        Scanner scanner = new Scanner(System.in);

        try {
            // Call the addSeminarRecord method with the Scanner and worldSize
            semManagerObject.addSeminarRecord(scanner, worldSize, idBST,
                costBST, dateBST, keywordBST, binLocation);

            // Reset the standard input and output
            System.setIn(System.in);
            System.setOut(originalOut);
            // System.out.println("Insert FAILED - Bad x, y coordinates: -1,
            // 0");

            // Assert that the printed output matches your expected output
            assertEquals("Insert FAILED - Bad x, y coordinates: 0, -1",
                outputStream.toString().trim());
        }
        catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }


    /**
     * Testing when y is greater than valid range
     */
    public void testRangeOfYGreater() {
        // Creating a valid input string and test adding a seminar record
        int worldSize = 1024;

        // Create a string with test input data
        String testInput =
            "1\nOverview of HCI Research at VT\n0610051600\n90\n0\n2000\n45\n"
                + "HCI Computer_Science VT Virginia_Tech\nThis seminar will"
                + " present an overview of HCI research at VT\r\n" + "";

        // Convert the test input string to an InputStream
        InputStream inputStream = new ByteArrayInputStream(testInput
            .getBytes());

        // Redirect standard input to use the InputStream
        System.setIn(inputStream);

        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create an instance of SemManagerBST
        SemManagerBST semManagerObject = new SemManagerBST();

        // Create a Scanner instance with the redirected standard input
        Scanner scanner = new Scanner(System.in);

        try {
            // Call the addSeminarRecord method with the Scanner and worldSize
            semManagerObject.addSeminarRecord(scanner, worldSize, idBST,
                costBST, dateBST, keywordBST, binLocation);

            // Reset the standard input and output
            System.setIn(System.in);
            System.setOut(originalOut);
            // System.out.println("Insert FAILED - Bad x, y coordinates: -1,
            // 0");

            // Assert that the printed output matches your expected output
            assertEquals("Insert FAILED - Bad x, y coordinates: 0, 2000",
                outputStream.toString().trim());
        }
        catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

}
