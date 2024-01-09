/**
 * Binary Search Tree (BST) implementation for key-value pairs.
 *
 * @param <T>
 *            Type of the keys (must be Comparable)
 * @param <V>
 *            Type of the values
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

class BST<T extends Comparable<T>, V> {
    /**
     * The `TreeNode` class represents a node in a binary tree structure used
     * for storing key-value pairs.
     * Each `TreeNode` contains a key-value pair (`KVPair`) as its data, and it
     * can have left and right child nodes.
     * This class is typically used in binary search tree (BST) implementations.
     */
    protected class TreeNode {

        private KVPair<T, V> data;

        /**
         * Gets the key-value pair data stored in this TreeNode.
         *
         * @return The key-value pair data
         */
        public KVPair<T, V> getData() {
            return data;
        }

        private TreeNode left;

        /**
         * Gets the left child node of this TreeNode.
         *
         * @return The left child node representing the sub-tree rooted at the
         *         left child of this node
         */
        public TreeNode getLeft() {
            return left;
        }

// /**
// * Sets the left child node.
// *
// * @param left
// * The left child node to set.
// */
// public void setLeft(TreeNode left) {
// this.left = left;
// }

        private TreeNode right;

        /**
         * Gets the right child node of this TreeNode.
         *
         * @return The right child node representing the sub-tree rooted at the
         *         right child of this node
         */
        public TreeNode getRight() {
            return right;
        }

// /**
// * Sets the right child node.
// *
// * @param right
// * The right child node to set.
// */
// public void setRight(TreeNode right) {
// this.right = right;
// }


        /**
         * Constructor for a TreeNode.
         *
         * @param data
         *            The key-value pair data
         */
        public TreeNode(KVPair<T, V> data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    /**
     * The root node of the binary tree structure.
     * This field holds a reference to the topmost node in the binary tree.
     * It represents the starting point for traversing the tree.
     */
    protected TreeNode root;
    /**
     * The count of nodes in the binary tree.
     * This field keeps track of the total number of nodes in the tree.
     * It is updated whenever nodes are added or removed from the tree.
     */
    protected int nodecount;

    /**
     * Constructor for an empty BST.
     */
    public BST() {
        root = null;
        nodecount = 0;
    }


    /**
     * Insert a key-value pair into the BST.
     *
     * @param data
     *            The key-value pair to insert
     */
    public void insert(KVPair<T, V> data) {
        root = inserthelp(root, data);
        nodecount++;
    }


    /**
     * Helper method to recursively insert a key-value pair into the BST.
     *
     * @param rootNode
     *            The root of the current subtree
     * @param data
     *            The key-value pair to insert
     * @return The updated root of the subtree
     */
    public TreeNode inserthelp(TreeNode rootNode, KVPair<T, V> data) {
        if (rootNode == null) {
            rootNode = new TreeNode(data);
            return rootNode;
        }

        if (data.compareTo(rootNode.data) <= 0) {
            rootNode.left = inserthelp(rootNode.left, data);
        }
        else {
            rootNode.right = inserthelp(rootNode.right, data);
        }

        return rootNode;

    }


    /**
     * Find a key-value pair in the BST by key.
     *
     * @param data
     *            The key-value pair to find
     * @return The found key-value pair, or null if not found
     */
    public KVPair<T, V> find(KVPair<T, V> data) {
        return findhelp(root, data);
    }


    /**
     * Helper method to recursively find a key-value pair by key.
     *
     * @param rootNode
     *            The root of the current subtree
     * @param data
     *            The key-value pair to find
     * @return The found key-value pair, or null if not found
     */
    private KVPair<T, V> findhelp(TreeNode rootNode, KVPair<T, V> data) {
        if (rootNode == null) {
            return null;
        }

        if (rootNode.data.compareTo(data) > 0) {
            return findhelp(rootNode.left, data);
        }
        else if (rootNode.data.compareTo(data) == 0) {
            return rootNode.data;
        }
        else {
            return findhelp(rootNode.right, data);
        }
    }


    /**
     * Check if a key-value pair exists in the BST by key.
     *
     * @param data
     *            The key-value pair to check for existence
     * @return True if the pair exists, false otherwise
     */
    public boolean containsValue(KVPair<T, V> data) {
        if (findhelp(root, data) != null) {
            return true;
        }
        else {
            return false;
        }

    }


    /**
     * Checks if the binary search tree contains a specified key.
     *
     * @param key
     *            The key to search for in the binary search tree.
     * @return True if the key is found in the binary search tree else false.
     */
    public boolean containsKey(T key) {
        return containsKey(root, key);
    }


    private boolean containsKey(TreeNode node, T key) {
        if (node == null) {
            return false;
        }

        int comparison = key.compareTo(node.data.key());

        if (comparison < 0) {
            return containsKey(node.left, key);
        }
        else if (comparison > 0) {
            return containsKey(node.right, key);
        }
        else {
            return true; // Key found
        }
    }


    /**
     * Get the number of nodes in the BST.
     *
     * @return The number of nodes
     */
    public int getSize() {
        return nodecount;
    }


    /**
     * Print the BST in an indented format.
     *
     * @param rootNode
     *            The root of the current subtree
     * @param level
     *            The current level of the tree
     */
    public void printhelp(TreeNode rootNode, int level) {
        if (rootNode == null) {
            printIndented("null", level);
            return;
        }
        printhelp(rootNode.right, level + 1);
        printIndented(rootNode.data.key().toString(), level);
        printhelp(rootNode.left, level + 1);
    }


    /**
     * Print an indented value with the specified level.
     *
     * @param value
     *            The value to print
     * @param level
     *            The current level of indentation
     */
    private void printIndented(String value, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  "); // Two spaces per level
        }
        System.out.println(value);
    }


    /**
     * Removes a node with specified key and value from the BST.
     *
     * @param dataToDelete
     *            The key-value pair to be removed from the BST.
     */
    public void removeSeminar(KVPair<T, V> dataToDelete) {
        root = removehelp(root, dataToDelete);
        nodecount--;
    }


    private TreeNode removehelp(TreeNode node, KVPair<T, V> data) {
        T key = data.key();
        if (node == null) {
            return null; // Node not found, nothing to delete
        }
        if (node.data.compareTo(key) > 0) {
            node.left = removehelp(node.left, data);
        }
        else if (node.data.compareTo(key) < 0) {
            node.right = removehelp(node.right, data);
        }
        else {
            if (node.data.value() == data.value()) {
                if (node.left == null) {
                    return node.right;
                }
                else if (node.right == null) {
                    return node.left;
                }
                else {
                    TreeNode temp = getMax(node.left);
                    node.data = temp.data;
                    node.left = deleteMax(node.left);
                }
            }
            else {
                node.left = removehelp(node.left, data);
            }
        }
        return node;
    }


    // Helper method to find the node with the maximum key in a subtree
    private TreeNode getMax(TreeNode node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }


    private TreeNode deleteMax(TreeNode node) {
        if (node.right == null)
            return node.left;
        node.right = deleteMax(node.right);
        return node;
    }

}




/**
 * A specialized Binary Search Tree (BST) for storing Seminars by their unique
 * ID.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

class IdBST extends BST<Integer, Seminar> {

    /**
     * Default constructor to create an empty IDBST.
     */
    public IdBST() {
        super();
    }


    /**
     * Insert a Seminar with a given ID into the tree.
     *
     * @param id
     *            The ID of the Seminar
     * @param seminar
     *            The Seminar to insert
     * @return True if insertion was successful, false if the ID is already in
     *         use
     */
    public boolean insertSeminar(int id, Seminar seminar) {
        KVPair<Integer, Seminar> data = new KVPair<>(id, seminar);

        if (!containsValue(data)) {
            insert((data));
            return true;
        }
        else {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + id);
            return false;
        }
    }


    /**
     * Get the number of records in the IDBST.
     *
     * @return The number of records in the tree
     */
    public int getRecordNumber() {
        return getSize();
    }


    /**
     * Print the ID Tree, including all seminars and their IDs.
     */
    public void printIDTree() {
        System.out.println("ID Tree:");

        if (root == null) {
            System.out.println("This tree is empty");
        }
        else {
            printhelp(root, 0); // Start printing from the root with level 0
            System.out.println("Number of records: " + getRecordNumber());
        }

    }


    /**
     * Search for a Seminar by its ID in the IDBST.
     *
     * @param id
     *            The ID of the Seminar to search for
     * @return The found Seminar or null if not found
     */
    public Seminar searchID(int id) {
        KVPair<Integer, Seminar> searchData = new KVPair<>(id, null);
        KVPair<Integer, Seminar> foundData = find(searchData);

        if (foundData != null) {
            return foundData.value();
        }
        else {
            return null; // ID not found
        }
    }

}




/**
 * A specialized Binary Search Tree (BST) for storing Seminars by their cost.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

class CostBST extends BST<Integer, Seminar> {
    /**
     * Default constructor to create an empty CostBST.
     */
    public CostBST() {
        super();
    }

    private int recordsInRangeCount = 0; // Initialize the count to 0

    /**
     * Insert a Seminar with a given cost into the tree.
     *
     * @param cost
     *            The cost of the Seminar
     * @param seminar
     *            The Seminar to insert
     * @return Always returns true
     */
    public boolean insertSeminar(int cost, Seminar seminar) {
        KVPair<Integer, Seminar> data = new KVPair<>(cost, seminar);

        insert(data);
        return true;

    }


    /**
     * Get the number of records in the CostBST.
     *
     * @return The number of records in the tree
     */
    public int getRecordNumber() {
        return getSize();
    }


    /**
     * Print the Cost Tree, including all seminars and their costs.
     */
    public void printCostTree() {
        System.out.println("Cost Tree:");

        if (root == null) {
            System.out.println("This tree is empty");
        }
        else {
            printhelp(root, 0); // Start printing from the root with level 0
            System.out.println("Number of records: " + getRecordNumber());
        }

    }


    /**
     * Search for and print Seminars within a specified cost range.
     *
     * @param minCost
     *            The minimum cost in the range
     * @param maxCost
     *            The maximum cost in the range
     */
    public void searchAndPrintCostRange(int minCost, int maxCost) {
        recordsInRangeCount = searchAndPrintCostRangeHelper(root, minCost,
            maxCost);
        System.out.println(recordsInRangeCount
            + " nodes visited in this search");
    }


    private int searchAndPrintCostRangeHelper(
        TreeNode node,
        int minCost,
        int maxCost) {
        int count = 1;

        if (node == null) {
            return count;
        }
        int nodeCost = node.getData().key();
        if (nodeCost >= minCost) {
            count += searchAndPrintCostRangeHelper(node.getLeft(), minCost,
                maxCost);
        }
        if (nodeCost >= minCost && nodeCost <= maxCost) {
            System.out.println(node.getData().value());
        }
        if (nodeCost < maxCost) {
            count += searchAndPrintCostRangeHelper(node.getRight(), minCost,
                maxCost);
        }

        return count;
    }

}




/**
 * A specialized Binary Search Tree (BST) for storing Seminars by their date.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
class DateBST extends BST<String, Seminar> {
    /**
     * Default constructor to create an empty DateBST.
     */
    public DateBST() {
        super();
    }


    /**
     * Insert a Seminar with a given date into the tree.
     *
     * @param date
     *            The date of the Seminar
     * @param seminar
     *            The Seminar to insert
     * @return Always returns true
     */
    public boolean insertSeminar(String date, Seminar seminar) {
        KVPair<String, Seminar> data = new KVPair<>(date, seminar);
        insert(data);
        return true;
    }


    /**
     * Get the number of records in the DateBST.
     *
     * @return The number of records in the tree
     */
    public int getRecordNumber() {
        return getSize();
    }


    /**
     * Print the Date Tree, including all seminars and their dates.
     */
    public void printDateTree() {
        System.out.println("Date Tree:");

        if (root == null) {
            System.out.println("This tree is empty");
        }
        else {
            printhelp(root, 0); // Start printing from the root with level 0
            System.out.println("Number of records: " + getRecordNumber());
        }
    }


    /**
     * Search for and print Seminars within a specified date range.
     *
     * @param minDate
     *            The minimum date in the range
     * @param maxDate
     *            The maximum date in the range
     */
    public void searchAndPrintDateRange(String minDate, String maxDate) {
        int nodesVisited = searchDateRangeHelper(root, minDate, maxDate);
        System.out.println(nodesVisited + " nodes visited in this search");

    }


    private int searchDateRangeHelper(
        TreeNode node,
        String minDate,
        String maxDate) {

        int count = 1;

        if (node == null) {
            return count;
        }

        String nodeDate = node.getData().key();
        int dateComparisonMin = nodeDate.compareTo(minDate);
        int dateComparisonMax = nodeDate.compareTo(maxDate);

        if (dateComparisonMin >= 0) {
            count += searchDateRangeHelper(node.getLeft(), minDate, maxDate);
        }
        if (dateComparisonMin >= 0 && dateComparisonMax <= 0) {
            System.out.println(node.getData().value().toString());
        }
        if (dateComparisonMax < 0) {
            count += searchDateRangeHelper(node.getRight(), minDate, maxDate);

        }
        return count;
    }

}




/**
 * A specialized Binary Search Tree (BST) for storing Seminars by keywords.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
class KeywordBST extends BST<String, Seminar> {
    /**
     * Default constructor to create an empty KeywordBST.
     */
    public KeywordBST() {
        super();
    }


    /**
     * Insert a Seminar with one or more keywords into the tree.
     *
     * @param seminar
     *            The Seminar to insert
     * @param keywords
     *            The keywords associated with the Seminar
     */
    public void insertSeminar(Seminar seminar, String... keywords) {
        for (String keyword : keywords) {
            KVPair<String, Seminar> data = new KVPair<>(keyword, seminar);
            insert(data);
        }
    }


    /**
     * Get the number of records in the KeywordBST.
     *
     * @return The number of records in the tree
     */
    public int getRecordNumber() {
        return getSize();
    }


    /**
     * Print the Keyword Tree, including all seminars and their keywords.
     */
    public void printKeywordTree() {
        System.out.println("Keyword Tree:");

        if (root == null) {
            System.out.println("This tree is empty");
        }
        else {
            printhelp(root, 0); // Start printing from the root with level 0
            System.out.println("Number of records: " + getRecordNumber());
        }

    }


    /**
     * Search for and print Seminars by a specific keyword.
     *
     * @param keyword
     *            The keyword to search for
     */
    public void searchAndPrintByKeyword(String keyword) {
        System.out.println("Seminars matching keyword " + keyword + ":");
        searchAndPrintKeywordHelper(root, keyword);
    }


    private void searchAndPrintKeywordHelper(TreeNode node, String keyword) {
        if (node == null) {
            return;
        }
        String nodeKeyword = node.getData().key();
        int keywordComparison = keyword.compareTo(nodeKeyword);
        if (keywordComparison < 0) {
            searchAndPrintKeywordHelper(node.getLeft(), keyword);
        }
        else if (keywordComparison > 0) {
            searchAndPrintKeywordHelper(node.getRight(), keyword);
        }
        else {
            searchAndPrintKeywordHelper(node.getLeft(), keyword);
            System.out.println(node.getData().value().toString());
            searchAndPrintKeywordHelper(node.getRight(), keyword);
        }
    }

}
