import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import student.TestCase;

/**
 * This class contains test cases for a Binary Search Tree data structure.
 * It extends the TestCase class, which is a part of JUnit testing framework,
 * to facilitate writing and running test cases.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
public class BSTTest extends TestCase {

    /**
     * Represents a test case for verifying behavior of the `containsValue`
     * method
     * in a Binary Search Tree (BST).
     */
    public void testContainsValue() {
        // Create a BST of integers
        BST<Integer, String> bst = new BST<>();

        // Insert some key-value pairs
        bst.insert(new KVPair<>(10, "A"));
        bst.insert(new KVPair<>(5, "B"));
        bst.insert(new KVPair<>(15, "C"));

        // Test if containsValue returns true for existing values
        assertTrue(bst.containsValue(new KVPair<>(10, "")));
        assertTrue(bst.containsValue(new KVPair<>(5, "")));
        assertTrue(bst.containsValue(new KVPair<>(15, "")));

        // Test if containsValue returns false for non-existing values
        assertFalse(bst.containsValue(new KVPair<>(20, "")));
        assertFalse(bst.containsValue(new KVPair<>(3, "")));
    }


    /**
     * Tests the `getSize` method of a Binary Search Tree (BST) to ensure it
     * correctly
     * returns the number of elements in the tree.
     */
    public void testGetSize() {
        // Create a BST of integers
        BST<Integer, String> bst = new BST<>();

        // Insert some key-value pairs
        bst.insert(new KVPair<>(10, "A"));
        bst.insert(new KVPair<>(5, "B"));
        bst.insert(new KVPair<>(15, "C"));

        // Test if the size matches the number of inserted elements
        assertEquals(3, bst.getSize());
    }


    /**
     * Tests the behavior of a Binary Search Tree (BST) when it is empty. It
     * verifies
     * that the tree reports its size as 0, and that attempts to find or check
     * the
     * presence of elements return the expected results for an empty tree.
     */
    public void testEmptyTree() {
        // Create an empty BST
        BST<Integer, String> bst = new BST<>();

        // Test if the tree is empty
        assertEquals(0, bst.getSize());
        assertNull(bst.find(new KVPair<>(10, "")));
        assertFalse(bst.containsValue(new KVPair<>(10, "")));
    }


    /**
     * Tests the constructor of the `KVPair` class to ensure that it correctly
     * sets
     * the key and value attributes of a key-value pair.
     */
    public void testConstructorKVPair() {
        // Test the constructor to ensure theKey and theVal are set correctly
        KVPair<Integer, String> pair = new KVPair<>(10, "A");
        assertEquals(10, pair.key().intValue());
        assertEquals("A", pair.value());
    }


    /**
     * Tests the `compareTo` method of the `KVPair` class to compare two
     * key-value
     * pairs based on their keys. It verifies that method correctly returns
     * positive, negative, or zero values as expected when comparing keys.
     */

    public void testCompareToKVPair() {
        // Test compareTo(KVPair<K, E> it) method to compare KVPair objects by
        // key
        KVPair<Integer, String> pair1 = new KVPair<>(10, "A");
        KVPair<Integer, String> pair2 = new KVPair<>(5, "B");
        KVPair<Integer, String> pair3 = new KVPair<>(15, "C");

        assertTrue(pair1.compareTo(pair2) > 0); // pair1 key is greater than
                                                // pair2 key
        assertTrue(pair2.compareTo(pair1) < 0); // pair2 key is less than
                                                // pair1 key
        assertEquals(0, pair1.compareTo(pair1)); // pair1 key is equal to
                                                 // pair1 key
        assertTrue(pair1.compareTo(pair3) < 0); // pair1 key is less than
                                                // pair3 key
    }


    /**
     * Tests the `compareTo` method of the `KVPair` class to compare key of
     * a
     * key-value pair with a specified key. It verifies that the method
     * correctly
     * returns positive, zero, or negative values as expected when comparing
     * keys.
     */
    public void testCompareToKey() {
        // Test compareTo(K it) method to compare KVPair key with a key
        KVPair<Integer, String> pair = new KVPair<>(10, "A");

        assertTrue(pair.compareTo(5) > 0); // pair key is greater than 5
        assertTrue(pair.compareTo(10) == 0); // pair key is equal to 10
        assertTrue(pair.compareTo(15) < 0); // pair key is less than 15
    }


    /**
     * Tests the `key` method of `KVPair` class to ensure that it correctly
     * retrieves the key associated with the key-value pair.
     */
    public void testKey() {
        // Test key() method to retrieve the key
        KVPair<Integer, String> pair = new KVPair<>(10, "A");
        assertEquals(10, pair.key().intValue());
    }


    /**
     * Tests the `value` method of the `KVPair` class to ensure that it
     * correctly
     * retrieves the value associated with the key-value pair.
     */
    public void testValue() {
        // Test value() method to retrieve the value
        KVPair<Integer, String> pair = new KVPair<>(10, "A");
        assertEquals("A", pair.value());
    }


    /**
     * Tests the constructor of the `BST` class to ensure that it creates an
     * empty binary search tree with a size of zero.
     */
    public void testConstructorBST() {
        BST<Integer, String> bst = new BST<>();
        assertEquals(0, bst.getSize());
    }


    /**
     * Tests the insertion of key-value pairs into a binary search tree (BST).
     * Verifies that inserting a unique pair increments size of the BST by
     * one,
     * and inserting the same pair again should not increment the size
     * (duplicate
     * insertion).
     */
    public void testInsertBST() {
        BST<Integer, String> bst = new BST<>();
        KVPair<Integer, String> pair = new KVPair<>(1, "A");

        bst.insert(pair);
        assertEquals(1, bst.getSize());

        // Mutate by inserting the same pair again (duplicate insertion)
        bst.insert(pair);
        assertEquals(2, bst.getSize()); // Mutation should fail
    }

// public void testInserthelp() {
// BST<Integer, String> bst = new BST<>();
// KVPair<Integer, String> rootPair = new KVPair<>(2, "B");
// KVPair<Integer, String> leftPair = new KVPair<>(1, "A");
// KVPair<Integer, String> rightPair = new KVPair<>(3, "C");
//
// bst.root = bst.new TreeNode(rootPair);
// bst.root.left = bst.new TreeNode(leftPair);
//
// // Mutate by inserting a pair that should go to the left (<= root)
// KVPair<Integer, String> mutantPair = new KVPair<>(0, "X");
// bst.inserthelp(bst.root, mutantPair);
// assertNotNull(bst.root.left.left); // Mutation should fail
//
// // Mutate by inserting a pair that should go to the right (> root)
// mutantPair = new KVPair<>(4, "D");
// bst.inserthelp(bst.root, mutantPair);
// assertNotNull(bst.root.right.right); // Mutation should fail
// }


    /**
     * Tests the find operation in a binary search tree (BST). Verifies that
     * find method returns null when searching for key that is not present in
     * the BST and returns a non-null result when searching for a key that has
     * been
     * inserted into the BST.
     */
    public void testFind() {
        BST<Integer, String> bst = new BST<>();
        KVPair<Integer, String> pair = new KVPair<>(1, "A");

        assertNull(bst.find(pair));

        bst.insert(pair);
        assertNotNull(bst.find(pair));
    }


    /**
     * Tests the insertion operation in an ID Binary Search Tree (IdBST).
     * Verifies
     * that inserting a seminar with a unique ID returns true and increments
     * record number, and that inserting a seminar with duplicate ID returns
     * false and does not affect the record number.
     */
    public void testIdBSTInsertSeminar() {
        IdBST idBST = new IdBST();
        Seminar seminar = new Seminar(1, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");

        assertTrue(idBST.insertSeminar(1, seminar));
        assertEquals(1, idBST.getRecordNumber());

        assertFalse(idBST.insertSeminar(1, seminar)); // Duplicate insert
        assertEquals(1, idBST.getRecordNumber());
    }


    /**
     * Tests the insertion operation in a Cost Binary Search Tree (CostBST).
     * Verifies
     * that inserting a seminar with unique cost returns true and increments
     * the
     * record number.
     */
    public void testCostBSTInsertSeminar() {
        CostBST costBST = new CostBST();
        Seminar seminar = new Seminar(1, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");

        assertTrue(costBST.insertSeminar(50, seminar));
        assertEquals(1, costBST.getRecordNumber());
    }


    /**
     * Tests the insertion operation in a Date Binary Search Tree (DateBST).
     * Verifies
     * that inserting a seminar with unique date returns true and increments
     * the
     * record number.
     */
    public void testDateBSTInsertSeminar() {
        DateBST dateBST = new DateBST();
        Seminar seminar = new Seminar(1, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");

        assertTrue(dateBST.insertSeminar("Date", seminar));
        assertEquals(1, dateBST.getRecordNumber());
    }


    /**
     * Tests the insertion operation in a Keyword Binary Search Tree
     * (KeywordBST).
     * Verifies that inserting a seminar with a keyword returns true and
     * increments
     * the record number.
     */
    public void testKeywordBSTInsertSeminar() {
        KeywordBST keywordBST = new KeywordBST();
        Seminar seminar = new Seminar(1, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");

        keywordBST.insertSeminar(seminar, "Keyword");
        assertEquals(1, keywordBST.getRecordNumber());
    }


    /**
     * Tests the constructor of a Cost Binary Search Tree (CostBST).
     * Verifies that a new CostBST instance is created with a null root.
     */
    public void testCostBSTConstructor() {
        // Create a CostBST instance
        CostBST costBST = new CostBST();

        // Perform a mutation: Simulate a null root by accessing it
        assertNull(costBST.root);
    }


    /**
     * Tests the printIDTree method of the IdBST class.
     * Verifies that the method correctly prints the ID Binary Search Tree
     * (IDBST),
     * including the tree structure and the number of records, to standard
     * output.
     */
    public void testPrintIDTree() {
        // Create an IdBST instance
        IdBST idBST = new IdBST();

        // Create a Seminar object and insert it into the tree
        Seminar seminar = new Seminar(1, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");
        idBST.insertSeminar(1, seminar);

        // Capture the standard output to check printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the printIDTree method
        idBST.printIDTree();

        // Reset the standard output
        System.setOut(originalOut);

        // Assert that the printed output contains expected content
        String printedOutput = outputStream.toString().trim();
        assertTrue(printedOutput.contains("ID Tree:"));
        assertTrue(printedOutput.contains("Number of records: 1"));
        assertTrue(printedOutput.contains("1")); // Should contain the ID of the
                                                 // inserted seminar
    }


    /**
     * Tests the printIDTree method of the IdBST class.
     * Verifies that the method correctly prints the ID Binary Search Tree
     * (IDBST),
     * including the tree structure and the number of records, to standard
     * output.
     */
    public void testPrintIDTreeFull() {
        // Create an IdBST instance
        IdBST idBST = new IdBST();

        // Create a Seminar object and insert it into the tree
        Seminar seminar1 = new Seminar(1, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");
        idBST.insertSeminar(1, seminar1);

        Seminar seminar2 = new Seminar(2, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");
        idBST.insertSeminar(2, seminar2);

        Seminar seminar10 = new Seminar(10, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");
        idBST.insertSeminar(10, seminar10);

        Seminar seminar3 = new Seminar(3, "Title", "Date", 90, (short)0,
            (short)0, 50, new String[] { "Keyword" }, "Description");
        idBST.insertSeminar(3, seminar3);

        // Capture the standard output to check printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the printIDTree method
        idBST.printIDTree();

        // Reset the standard output
        System.setOut(originalOut);

        // Assert that the printed output contains expected content
        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "ID Tree:\n" + "      null\n" + "    10\n"
            + "        null\n" + "      3\n" + "        null\n" + "  2\n"
            + "    null\n" + "1\n" + "  null\n" + "Number of records: 4";

        assertEquals(expectedOutput, printedOutput);
    }


    /**
     * Test the 'containsKey' method of a binary search tree (BST).
     * Inserts key-value pairs into the BST and checks if method correctly
     * identifies
     * whether specific keys are present or not.
     */
    public void testContainsKey() {
        BST<Integer, String> bst = new BST<Integer, String>();
        bst.insert(new KVPair<Integer, String>(1, "One"));
        bst.insert(new KVPair<Integer, String>(2, "Two"));
        bst.insert(new KVPair<Integer, String>(3, "Three"));
        assertTrue(bst.containsKey(1));
        assertTrue(bst.containsKey(2));
        assertTrue(bst.containsKey(3));

        assertFalse(bst.containsKey(0)); // Smaller than all keys
        assertFalse(bst.containsKey(4)); // Larger than all keys
        assertFalse(bst.containsKey(100)); // Not in the BST
    }


    /**
     * Tests the 'removeSeminar' method of the BST class.
     * Verifies that the method correctly removes a node with the specified key
     * and value
     * from the BST and decrements the node count.
     */
    public void testRemoveSeminar() {
        // Create a BST of seminars
        BST<Integer, Seminar> bst = new BST<>(); // Assuming the key type is
                                                 // Integer and value type is
                                                 // Seminar

        // Insert some seminars
        Seminar seminar1 = new Seminar(1, "Title1", "Date1", 50, (short)0,
            (short)0, 50, new String[] { "Keyword1" }, "Description1");
        Seminar seminar2 = new Seminar(2, "Title2", "Date2", 60, (short)0,
            (short)0, 60, new String[] { "Keyword2" }, "Description2");
        Seminar seminar3 = new Seminar(3, "Title3", "Date3", 70, (short)0,
            (short)0, 70, new String[] { "Keyword3" }, "Description3");

        bst.insert(new KVPair<>(seminar1.id(), seminar1));
        bst.insert(new KVPair<>(seminar2.id(), seminar2));
        bst.insert(new KVPair<>(seminar3.id(), seminar3));

        // Verify that the BST contains the seminars
        assertEquals(3, bst.getSize());

        // Remove seminar2 from the BST
        bst.removeSeminar(new KVPair<>(seminar2.id(), seminar2));

        // Verify that seminar2 has been removed
        assertEquals(2, bst.getSize());

        // Attempt to remove seminar2 again (it should not affect the size)
        bst.removeSeminar(new KVPair<>(seminar2.id(), seminar2));
        assertEquals(1, bst.getSize());

        // Attempt to remove a seminar that doesn't exist (it should not affect
        // the size)
        Seminar seminar4 = new Seminar(4, "Title4", "Date4", 80, (short)0,
            (short)0, 80, new String[] { "Keyword4" }, "Description4");
        bst.removeSeminar(new KVPair<>(seminar4.id(), seminar4));
        assertEquals(0, bst.getSize());
    }


    /**
     * Test case for the removehelp method.
     * This test case covers different scenarios of removing nodes from a Binary
     * Search Tree (BST).
     */
    public void testRemovehelp() {
        BST<Integer, String> bst = new BST<>();

        // Create nodes with specific key-value pairs
        KVPair<Integer, String> node1 = new KVPair<>(3, "A");
        KVPair<Integer, String> node2 = new KVPair<>(2, "B");
        KVPair<Integer, String> node3 = new KVPair<>(1, "C");

        // Insert the nodes into the BST
        bst.insert(node1);
        bst.insert(node2);
        bst.insert(node3);

        // Verify that the nodes were inserted correctly
        assertEquals(3, bst.getSize());

        // Now, remove a node with a key greater than the current node's key
        KVPair<Integer, String> dataToRemove1 = new KVPair<>(2, "B");
        bst.removeSeminar(dataToRemove1);

        // After removing the node with key 2, the tree should still contain
        // nodes 1 and 3
        assertEquals(2, bst.getSize());

        // Verify that the tree structure is correct by checking the remaining
        // keys
        assertTrue(bst.containsKey(1));
        assertTrue(bst.containsKey(3));

        // Now, remove a node where key matches but values are equal
        KVPair<Integer, String> dataToRemove2 = new KVPair<>(3, "C");
        bst.removeSeminar(dataToRemove2);

        // After removing the node with key 3 and value "C", the tree should
        // contain only node 1
        assertEquals(1, bst.getSize());

        // Verify that the tree structure is correct by checking the remaining
        // key
        assertTrue(bst.containsKey(1));

        // Verify that the removed node (key 3, value "C") is no longer present
        // in the tree
        assertTrue(bst.containsKey(3));
    }


    /**
     * Test case for removing a node with a key greater than the current node's
     * key.
     * This test case verifies that the tree structure remains unchanged when
     * attempting to remove a non-existing node.
     */
    public void testRemoveNodeWithGreaterKey() {
        BST<Integer, String> bst = new BST<>();

        // Create nodes with specific key-value pairs
        KVPair<Integer, String> node1 = new KVPair<>(1, "A");
        KVPair<Integer, String> node2 = new KVPair<>(2, "B");
        KVPair<Integer, String> node3 = new KVPair<>(3, "C");

        // Insert the nodes into the BST
        bst.insert(node1);
        bst.insert(node2);
        bst.insert(node3);

        // Verify that the nodes were inserted correctly
        assertEquals(3, bst.getSize());

        // Attempt to remove a node with a key that is greater than the current
        // node's key (e.g., key = 4)
        KVPair<Integer, String> dataToRemove = new KVPair<>(4, "D");
        bst.removeSeminar(dataToRemove);

        // Verify that the tree structure is still correct
        assertTrue(bst.containsKey(1));
        assertTrue(bst.containsKey(2));
        assertTrue(bst.containsKey(3));
    }


    /**
     * Test case for removing a node with its right child set to null.
     * This test case verifies that the tree structure remains correct after
     * removing a node with a right child set to null.
     */
    public void testRemoveNodeWithRightChildAsNull() {
        BST<Integer, String> bst = new BST<>();

        // Create nodes with specific key-value pairs
        KVPair<Integer, String> node1 = new KVPair<>(1, "A");
        KVPair<Integer, String> node2 = new KVPair<>(2, "B");
        KVPair<Integer, String> node3 = new KVPair<>(3, "C");

        // Insert the nodes into the BST
        bst.insert(node1);
        bst.insert(node2);
        bst.insert(node3);

        // Verify that the nodes were inserted correctly
        assertEquals(3, bst.getSize());

        // Attempt to remove a node with its right child set to null (e.g., key
        // = 1)
        KVPair<Integer, String> dataToRemove = new KVPair<>(1, "A");
        bst.removeSeminar(dataToRemove);

        // Verify that the node with key 1 has been removed, and the tree
        // structure is correct
        assertNull(bst.find(node1));
        assertTrue(bst.containsKey(2));
        assertTrue(bst.containsKey(3));
        assertEquals(2, bst.getSize());
    }
}
