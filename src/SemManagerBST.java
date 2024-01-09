
/**
 * A class to manage the seminar records and its various operations.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * The `SemManagerBST` class is responsible for managing seminar records using
 * binary search trees (BSTs).
 * It provides methods for adding seminar records to appropriate BSTs, checking
 * coordinate validity, and
 * printing seminar records sorted by various criteria such as ID, cost, date,
 * and keywords.
 * 
 * This class facilitates the organization and retrieval of seminar records
 * based on different attributes,
 * allowing for efficient data management and retrieval.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
public class SemManagerBST {

    /**
     * Constructor
     */
    public SemManagerBST() {

    }


    /**
     * Adds a seminar record to the appropriate binary search trees.
     *
     * @param scanner
     *            The scanner for reading input.
     * @param worldSize
     *            The world size for coordinate validation.
     * @param idBST
     *            The binary search tree for seminar records by ID.
     * @param costBST
     *            The binary search tree for seminar records by cost.
     * @param dateBST
     *            The binary search tree for seminar records by date.
     * @param keywordBST
     *            The binary search tree for seminar records by keywords.
     * @param binLocation
     *            The bintree for seminar records location.
     */

    public void addSeminarRecord(
        Scanner scanner,
        int worldSize,
        IdBST idBST,
        CostBST costBST,
        DateBST dateBST,
        KeywordBST keywordBST,
        Bintree binLocation) {

        int id = Integer.parseInt(scanner.nextLine().trim());
        String title = scanner.nextLine().trim();
        String dateTime = scanner.next().trim();
        int length = Integer.parseInt(scanner.next().trim());
        short x = Short.parseShort(scanner.next().trim());
        short y = Short.parseShort(scanner.next().trim());
        int cost = Integer.parseInt(scanner.next().trim());
        scanner.nextLine(); // Consume the newline

        String[] keywords = scanner.nextLine().split(" ");
        keywords = Arrays.stream(keywords).filter(keyword -> !keyword.isEmpty())
            .toArray(String[]::new);

        String description = scanner.nextLine().trim();

        // Check if x and y are in the range 0 to worldSize-1

        if ((!isInRange(x, worldSize)) || (!isInRange(y, worldSize))) {
            System.out.println("Insert FAILED - Bad x, y coordinates: " + x
                + ", " + y);
            return;
        }

        Seminar seminar = new Seminar(id, title, dateTime, length, x, y, cost,
            keywords, description);

        boolean checkDuplicate = idBST.insertSeminar(id, seminar);
        if (checkDuplicate) {
            costBST.insertSeminar(cost, seminar);
            dateBST.insertSeminar(dateTime, seminar);
            keywordBST.insertSeminar(seminar, keywords);
            binLocation.insert(seminar, worldSize);
            System.out.println("Successfully inserted record with ID " + id);
            System.out.println(seminar.toString());
        }

    }


    /**
     * Checks if a value is in the range 0 to worldSize-1.
     *
     * @param value
     *            The value to check.
     * @param worldSize
     *            The world size for the range.
     * @return True if the value is in the specified range, false otherwise.
     */
    public static boolean isInRange(short value, int worldSize) {
        int intValue = Short.toUnsignedInt(value);
        boolean result = intValue >= 0 && intValue < worldSize;
        return result;
    }


    /**
     * Prints the seminar records sorted by ID.
     *
     * @param idBST
     *            The binary search tree for seminar records by ID.
     */
    public static void printIdTree(IdBST idBST) {
        idBST.printIDTree(); // Call the printIDTree method from IdBST
    }


    /**
     * Prints the seminar records sorted by cost.
     *
     * @param costBST
     *            The binary search tree for seminar records by cost.
     */
    public static void printCostTree(CostBST costBST) {
        costBST.printCostTree(); // Call the printCostTree method from CostBST
    }


    /**
     * Prints the seminar records sorted by date.
     *
     * @param dateBST
     *            The binary search tree for seminar records by date.
     */
    public static void printDateTree(DateBST dateBST) {
        dateBST.printDateTree(); // Call the printDateTree method from DateBST
    }


    /**
     * Prints the seminar records sorted by keyword.
     *
     * @param keywordBST
     *            The binary search tree for seminar records by keywords.
     */
    public static void printKeywordTree(KeywordBST keywordBST) {
        keywordBST.printKeywordTree(); // Call the printKeywordTree method from
                                       // KeywordBST
    }

}
