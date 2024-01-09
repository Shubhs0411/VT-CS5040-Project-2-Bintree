import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The `CommandProcessor` class is responsible for processing commands from a
 * file and
 * managing seminar records.
 * It reads commands from a file, parses them, and performs actions like
 * inserting,
 * printing, searching, and deleting seminar records based on these commands.
 *
 * The class maintains instances of various BSTs to efficiently manage seminar
 * records
 * by different criteria such as ID, cost, date, and keywords. Additionally, it
 * handles
 * location-based operations using a bintree structure.
 *
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

public class CommandProcessor {
    private SemManagerBST semManager;
    private IdBST idBST;
    private CostBST costBST;
    private DateBST dateBST;
    private KeywordBST keywordBST;
    private Bintree binLocation;
    private int worldSize;

    /**
     * Constructs a new `CommandProcessor` with the given world size.
     *
     * @param worldSize
     *            The world size, a power of two, used in operations.
     */
    public CommandProcessor(int worldSize) {
        this.worldSize = worldSize;
        semManager = new SemManagerBST();
        idBST = new IdBST();
        costBST = new CostBST();
        dateBST = new DateBST();
        keywordBST = new KeywordBST();
        binLocation = new Bintree();
    }


    /**
     * Processes a sequence of commands from a file.
     *
     * @param filePath
     *            The path to the file containing commands and seminar data.
     * @return True if the file was successfully processed, false otherwise.
     */
    public boolean processCommandsFromFile(String filePath) {
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String command = scanner.next().trim();

                switch (command) {
                    case "insert":

                        semManager.addSeminarRecord(scanner, worldSize, idBST,
                            costBST, dateBST, keywordBST, binLocation);

                        break;
                    case "print":
                        String nextToken = scanner.next().trim();
                        if ("ID".equals(nextToken)) {
                            SemManagerBST.printIdTree(idBST);
                        }
                        else if ("cost".equals(nextToken)) {
                            SemManagerBST.printCostTree(costBST);
                        }
                        else if ("date".equals(nextToken)) {
                            SemManagerBST.printDateTree(dateBST);
                        }
                        else if ("keyword".equals(nextToken)) {
                            SemManagerBST.printKeywordTree(keywordBST);
                        }
                        else if ("location".equals(nextToken)) {
                            binLocation.preorderTraversal();
                        }
                        break;

                    case "search":
                        String nextTokenSearch = scanner.next().trim();

                        if ("ID".equals(nextTokenSearch)) {
                            int searchId = scanner.nextInt();

                            Seminar foundSeminar = idBST.searchID(searchId);
                            if (foundSeminar != null) {
                                System.out.println("Found record with ID "
                                    + searchId + ":");
                                System.out.println(foundSeminar.toString());
                            }
                            else {
                                System.out.println(
                                    "Search FAILED -- There is no record with"
                                        + " ID " + searchId);
                            }
                        }
                        else if ("cost".equals(nextTokenSearch)) {
                            int minCost = scanner.nextInt();
                            int maxCost = scanner.nextInt();

                            System.out.println("Seminars with costs in range "
                                + minCost + " to " + maxCost + ":");

                            // Call the searchAndPrintCostRange method to
                            // perform the search and print results
                            costBST.searchAndPrintCostRange(minCost, maxCost);
                        }

                        else if ("date".equals(nextTokenSearch)) {
                            String minDate = scanner.next().trim();
                            String maxDate = scanner.next().trim();

                            System.out.println("Seminars with dates in range "
                                + minDate + " to " + maxDate + ":");

                            dateBST.searchAndPrintDateRange(minDate, maxDate);
                        }
                        else if ("keyword".equals(nextTokenSearch)) {
                            String keyword = scanner.next().trim();

                            keywordBST.searchAndPrintByKeyword(keyword);
                        }
                        else if ("location".equals(nextTokenSearch)) {
                            int x = scanner.nextInt();
                            // System.out.println("x: "+ x);
                            int y = scanner.nextInt();
                            // System.out.println("y: "+ y);
                            int radius = scanner.nextInt();
                            // System.out.println("radius: "+ radius);
                            // binLocation.search(x, y, radius, worldSize);

                        }

                        break;
                    case "delete":
                        String nextTokenDelete = scanner.next().trim();
                        int deleteId = Integer.parseInt(nextTokenDelete);
                        Seminar seminarToDelete = idBST.searchID(deleteId);
                        if (seminarToDelete != null) {

                            System.out.println("Record with ID "
                                + seminarToDelete.id()
                                + " successfully deleted from the database");
                            KVPair<Integer, Seminar> idToDelete = new KVPair<>(
                                seminarToDelete.id(), seminarToDelete);
                            idBST.removeSeminar(idToDelete);

                            KVPair<Integer, Seminar> costToDelete =
                                new KVPair<>(seminarToDelete.cost(),
                                    seminarToDelete);
                            costBST.removeSeminar(costToDelete);

                            KVPair<String, Seminar> dataToDelete = new KVPair<>(
                                seminarToDelete.date(), seminarToDelete);
                            dateBST.removeSeminar(dataToDelete);
                            String[] seminarKeywords = seminarToDelete
                                .keywords();
                            for (String keyword : seminarKeywords) {
                                KVPair<String, Seminar> keywordToDelete =
                                    new KVPair<>(keyword, seminarToDelete);
                                keywordBST.removeSeminar(keywordToDelete);
                            }

                            binLocation.delete(seminarToDelete.x(),
                                seminarToDelete.y(), worldSize);
                        }
                        else {
                            System.out.println(
                                "Delete FAILED -- There is no record with ID "
                                    + deleteId);
                        }

                        break;
                    default:
                        System.err.println("Unknown command: " + command);
                        scanner.nextLine();
                        break;
                }
            }
            return true;
        }
        catch (FileNotFoundException e) {
            System.err.println(
                "Error: File not found. Please enter a valid input filepath");
            return false;
        }
        catch (Exception e) {
            System.err.println(
                "Error: An exception occurred while processing commands.");
            return false;
        }
    }

}
