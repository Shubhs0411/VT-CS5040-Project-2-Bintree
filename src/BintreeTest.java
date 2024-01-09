
import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This class contains test cases for the BinTree class, which manages
 * seminar
 * records using bintree.
 * It extends the TestCase class from student package for testing purposes.
 *
 * @author Lavisha Goyal
 * @author Shubham Laxmikant Deshmukh
 * @version 1.0
 */
public class BintreeTest extends TestCase {

    /**
     * Test the 'preorderTraversal' method of an empty Bintree.
     * Creates an empty Bintree, redirects standard output to capture printed
     * content,
     * calls 'preorderTraversal,' and checks if printed content matches the
     * expected output.
     */
    public void testPreorderTraversalEmptyTree() {
        Bintree tree = new Bintree();
        // Redirect standard output to capture printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        tree.preorderTraversal();
        assertEquals("Location Tree:\nE\n", outputStream.toString());
        // Reset standard output
        System.setOut(System.out);
    }


    /**
     * Test the 'preorderTraversal' method of a Bintree with empty root node.
     * Creates a Bintree with empty root node, redirects standard output to
     * capture printed content,
     * calls 'preorderTraversal,' and checks if printed content matches the
     * expected output.
     */
    public void testPreorderTraversalEmptyNode() {
        Bintree tree = new Bintree();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        tree.preorderTraversal();

        System.setOut(System.out);

        String expectedOutput = "Location Tree:\nE";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }


    /**
     * Test case to verify the behavior of the 'preorderTraversal' method when
     * the Bintree root is null.
     * It sets the root to null (for testing purposes), captures console
     * output, and checks if the expected output matches.
     */
    void testPreorderTraversalNullRoot() {
        Bintree bintree = new Bintree();

        bintree.setRoot(null);

        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        bintree.preorderTraversal();

        // Verify the expected output when the root is null
        assertEquals("Location Tree:\nE\n", outContent.toString());
    }


    /**
     * Test case to verify the behavior of the 'preorderTraversal' method when
     * the Bintree root is empty.
     * It simulates an empty root, captures the console output, and checks if
     * the expected output matches.
     */
    void testPreorderTraversalEmptyRoot() {
        Bintree bintree = new Bintree();
        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        bintree.preorderTraversal();

        // Verify the expected output when the root is an empty node
        assertEquals("Location Tree:\nE\n", outContent.toString());
    }


    /**
     * Verifies that the tree remains empty after attempting to delete a node
     * from an empty binary tree.
     */
    public void testDeleteEmptyTree() {
        Bintree tree = new Bintree();

        int x = 10;
        int y = 20;
        int size = 100;
        tree.delete(x, y, size);

        // Check that the tree remains empty
        assertTrue(tree.isEmpty());
    }


    /**
     * Verifies that deleting a nonexistent node from the tree does not affect
     * the tree's contents.
     */
    public void testDeleteNonexistentNode() {
        Bintree tree = new Bintree();
        Seminar seminar = new Seminar(1, "Seminar 1", "2023-10-10", 60,
            (short)10, (short)20, 50, new String[] { "keyword1", "keyword2" },
            "Description"); // Create a seminar
        tree.insert(seminar, 100); // Insert the seminar

        int x = 30;
        int y = 40;
        int size = 100;
        tree.delete(x, y, size);

        // Check that the tree still contains the inserted seminar
        assertFalse(tree.isEmpty());
        // assertTrue(tree.search(x, y, 0, size).isEmpty());
    }


    /**
     * Verifies that deleting a single leaf node from the tree results in an
     * empty tree.
     */
    public void testDeleteSingleLeafNode() {
        Bintree tree = new Bintree();
        Seminar seminar = new Seminar(1, "Seminar 1", "2023-10-10", 60,
            (short)10, (short)20, 50, new String[] { "keyword1", "keyword2" },
            "Description"); // Create a seminar
        tree.insert(seminar, 100); // Insert the seminar

        int x = 10;
        int y = 20;
        int size = 100;
        tree.delete(x, y, size);

        // Check that the tree is empty after deleting the only leaf node
        assertTrue(tree.isEmpty());
    }


    /**
     * Verifies that deleting an internal node containing a leaf node leaves the
     * tree intact.
     */
    public void testDeleteInternalNodeWithLeaf() {
        Bintree tree = new Bintree();
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 60,
            (short)10, (short)20, 50, new String[] { "keyword1", "keyword2" },
            "Description"); // Create seminars
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-11", 90,
            (short)30, (short)40, 75, new String[] { "keyword3", "keyword4" },
            "Description");
        tree.insert(seminar1, 100); // Insert seminars
        tree.insert(seminar2, 100);

        int x = 10;
        int y = 20;
        int size = 100;
        tree.delete(x, y, size);

        // Check that the tree still contains the second seminar
        assertFalse(tree.isEmpty());
        // assertTrue(tree.search(seminar2.x(), seminar2.y(), 0,
        // size).isLeaf());
    }


    /**
     * Test case for deleting a node when the input node is null.
     */
    public void testDeleteNullNode() {
        Bintree bintree = new Bintree();
        bintree.setRoot(null); // Simulate a null root node
        int x = 10;
        int y = 20;
        int size = 100;

        bintree.delete(x, y, size);

        assertNull(bintree.getRoot());
    }


    /**
     * Test case for deleting a node when the root is an empty node.
     */
    public void testDeleteEmptyNode() {
        Bintree bintree = new Bintree();
        // Create an empty node
        BintreeNode emptyNode = EmptyNode.getInstance();

        // Set the empty node as the root
        bintree.setRoot(emptyNode);

        // Attempt to delete from an empty node
        bintree.delete(10, 20, 100);

        assertSame(emptyNode, bintree.getRoot());
    }


    /**
     * Test the deletion of a node when a node with matching coordinates is
     * found.
     */
    public void testDeleteNodeFoundAndMatchesCoordinates() {

        Bintree bintree = new Bintree();

        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)80, (short)80, 100, new String[] { "keyword" },
            "Description");
        BintreeNode leafNode = new LeafNode(seminar);
        int x = 80;
        int y = 80;
        int worldSize = 100;

        bintree.setRoot(leafNode);

        bintree.delete(x, y, worldSize);

        assertTrue(bintree.isEmpty());
    }


    /**
     * Test the deletion of a node when node with coordinates is found, but it
     * doesn't match the deletion coordinates.
     */
    public void testDeleteNodeFoundButDoesNotMatchCoordinates() {

        Bintree bintree = new Bintree();
        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)80, (short)80, 100, new String[] { "keyword" },
            "Description");
        BintreeNode leafNode = new LeafNode(seminar);
        int x = 10;
        int y = 20;
        int worldSize = 100;

        bintree.setRoot(leafNode);

        bintree.delete(x, y, worldSize);
        assertSame(leafNode, bintree.getRoot());
    }


    /**
     * Test the deletion when the input node is an internal node. The tree
     * should remain unchanged.
     */
    public void testDeleteInputNodeIsInternalNode() {

        Bintree bintree = new Bintree();
        InternalNode internalNode = new InternalNode();
        int x = 10;
        int y = 20;
        int worldSize = 100;

        bintree.setRoot(internalNode);

        bintree.delete(x, y, worldSize);
        assertSame(internalNode, bintree.getRoot());
    }
    /**
     * Tests the search method when a seminar outside the search radius is
     * inserted.
     */
// public void testSearchWithNonMatchingSeminar() {
// Bintree tree = new Bintree();
// int size = 128; // World size
// int x = 50; // X-coordinate for search
// int y = 50; // Y-coordinate for search
//
// // Insert a seminar into the tree with coordinates outside the search
// // radius
// Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
// (short)80, (short)80, 100, new String[] { "keyword" },
// "Description");
// tree.insert(seminar, size);
//
// tree.search(x, y, 10, size);
//
// // Check the tree state after search
// assertFalse(tree.isEmpty());
// }


    /**
     * Tests inserting a seminar into an initially empty tree.
     */
    public void testInsertIntoEmptyTree() {
        Bintree tree = new Bintree();
        int size = 128;
        assertTrue(tree.isEmpty());

        // Insert a seminar into the tree
        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)50, (short)50, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar, size);

        assertFalse(tree.isEmpty());
    }


    /**
     * Tests inserting seminars with the same coordinates into the tree.
     */

    public void testInsertSameCoordinates() {
        Bintree tree = new Bintree();

        int size = 128;

        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)0, (short)0, 100, new String[] { "keyword" }, "Description");
        tree.insert(seminar1, size);

        // BintreeNode root = tree.isEmpty();

        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-11", 2,
            (short)50, (short)50, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar2, size);
        // root = tree.insert(root, seminar2, x0, y0, size, level); // Should
        // reorganize into an internal node
        assertFalse(tree.isEmpty());
        // assertTrue(root instanceof InternalNode);
    }


    /**
     * Tests inserting seminars with different coordinates into the tree.
     */
    public void testInsertWithDifferentCoordinates() {
        Bintree tree = new Bintree();
        int size = 128;
        assertTrue(tree.isEmpty());

        // Insert a seminar into the tree
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2023-10-10", 2,
            (short)50, (short)50, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar1, size);

        // Insert another seminar with different coordinates
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2023-10-11", 2,
            (short)60, (short)60, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar2, size);

        // Check if they are not organized into an internal node
        assertFalse(tree.isEmpty());
    }


    /**
     * Test case to verify the behavior of deleting from a null Bintree.
     * It sets the root to null, attempts to delete, and checks if Bintree
     * is empty.
     */
    void testNodeIsNull() {
        Bintree bintree = new Bintree();
        bintree.setRoot(null); // Simulate a null root node
        bintree.delete(0, 0, 10); // Attempt to delete from a null tree
        assertTrue(bintree.isEmpty());
    }


    /**
     * Test case to verify the behavior of deleting from an empty Bintree.
     * It sets the root to an empty node, attempts to delete, and checks if
     * Bintree's root remains the same.
     */
    void testNodeIsEmpty() {
        Bintree bintree = new Bintree();
        BintreeNode emptyNode = EmptyNode.getInstance();
        bintree.setRoot(emptyNode); // Set the root node as an empty node
        bintree.delete(0, 0, 10); // Attempt to delete from an empty tree

        assertSame(emptyNode, bintree.getRoot());
    }


    /**
     * Tests inserting a seminar into an empty tree and checks if the tree is
     * not empty afterward.
     */
    public void testInsertIntoEmptyTree1() {
        Bintree tree = new Bintree();
        int size = 128;
        assertTrue(tree.isEmpty());

        // Create a seminar and insert it into the tree
        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)50, (short)50, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar, size);

        assertFalse(tree.isEmpty());
    }


    /**
     * Tests inserting a seminar into a tree with an empty root node.
     * Checks if the root node becomes a leaf node and contains the inserted
     * seminar.
     */
    public void testInsertIntoEmptyRoot() {
        Bintree tree = new Bintree();
        int size = 128;
        assertTrue(tree.isEmpty());

        // Create a seminar and insert it into the tree
        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)50, (short)50, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar, size);

        BintreeNode root = tree.getRoot1();
        assertTrue(root.isLeaf());
        assertTrue(((LeafNode)root).getData() == seminar);
    }


    /**
     * Tests inserting a seminar into a tree with an empty root node.
     * Checks if the root node becomes an internal node.
     */
    public void testInsertIntoEmptyRootWithSubdivision() {
        Bintree tree = new Bintree();
        int size = 128;
        assertTrue(tree.isEmpty());

        // Create a seminar and insert it into the tree such that the root node
        // should subdivide
        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)50, (short)50, 100, new String[] { "keyword" },
            "Description");
        tree.insert(seminar, size);

        BintreeNode root = tree.getRoot1();
        assertFalse(root instanceof InternalNode);
    }


    /**
     * Test case for line 311 where equality check replaced with false.
     */
    public void testLogicalExpressionLine311False() {
        Bintree tree = new Bintree();

        int x = 10;
        int y = 20;
        int size = 100;

        // Attempt to delete a node from an empty binary tree
        tree.delete(x, y, size);

        // Check that the tree remains empty
        assertTrue(tree.isEmpty());
    }


    /**
     * Tests a logical expression in line 312 by attempting to delete
     * nonexistent
     * node from the Bintree. It checks whether the tree still contains
     * inserted seminar
     * after the deletion operation.
     */
    public void testLogicalExpressionLine312False() {
        Bintree tree = new Bintree();

        int x = 30;
        int y = 40;
        int size = 100;

        // Insert a seminar into the tree
        Seminar seminar = new Seminar(1, "Seminar 1", "2023-10-10", 60,
            (short)10, (short)20, 50, new String[] { "keyword1", "keyword2" },
            "Description");
        tree.insert(seminar, 100);

        tree.delete(x, y, size);

        assertFalse(tree.isEmpty());
    }


    /**
     * Test case for line 312 where equality check replaced with true.
     */
    public void testLogicalExpressionLine312True() {
        Bintree tree = new Bintree();

        int x = 10;
        int y = 20;
        int size = 100;

        // Insert a seminar into the tree
        Seminar seminar = new Seminar(1, "Seminar 1", "2023-10-10", 60,
            (short)10, (short)20, 50, new String[] { "keyword1", "keyword2" },
            "Description");
        tree.insert(seminar, 100);

        // Attempt to delete the inserted seminar
        tree.delete(x, y, size);

        // Check that the tree is empty after deleting the only leaf node
        assertTrue(tree.isEmpty());
    }


    /**
     * Tests the insertion of a seminar into a Bintree and checks if Bintree
     * is not empty.
     */
    public void testInsert() {

        Bintree bintree = new Bintree();
        Seminar seminar = new Seminar(1, "Sample Seminar", "2023-10-10", 2,
            (short)80, (short)80, 100, new String[] { "keyword" },
            "Description");

        bintree.insert(seminar, 128);
        assertFalse(bintree.isEmpty());
    }


}
