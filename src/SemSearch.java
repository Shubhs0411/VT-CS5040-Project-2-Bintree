
/**
 * The class containing the main method. It represents a program for managing
 * seminar records and performing operations on them.
 * It reads and parses a file containing seminar data and commands, allowing
 * users to insert, search, and print seminar records.
 * 
 * The program assumes a world size and uses BST and bintree
 * for record management.
 *
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class SemSearch {
    /**
     * The main entry point for the program.
     *
     * @param args
     *            Command-line arguments. Expects 'worldSize' (a power of two)
     *            and the
     *            path to the input file.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                "Incorrect number of arguments: provide two arguments"
                    + " in the correct format.");
            return;
        }

        int worldSize;
        try {
            worldSize = Integer.parseInt(args[0]);
            if (!isPowerOfTwo(worldSize)) {
                System.err.println("Incorrect world-size: The world-size "
                    + "should be a power of two.");
                return;
            }
        }
        catch (NumberFormatException e) {
            System.err.println(
                "Incorrect world-size: This is not a valid integer.");
            return;
        }

        String filePath = args[1];
        CommandProcessor commandProcessor = new CommandProcessor(worldSize);
        commandProcessor.processCommandsFromFile(filePath);
    }


    /**
     * Checks if a given integer is a power of two.
     *
     * @param num
     *            The integer to check.
     * @return True if the number is a power of two, false otherwise.
     */
    public static boolean isPowerOfTwo(int num) {
        return (num > 0) && ((num & (num - 1)) == 0);
    }
}
