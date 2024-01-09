/**
 * A class to manage the Bintree implementation and its various operations.
 * An interface representing a node in a binary tree.
 * 
 * Provides methods to check if the node is a leaf node or empty.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

interface BintreeNode {
    /**
     * Checks if the node is a leaf node.
     *
     * @return True if the node is a leaf node, false otherwise.
     */
    boolean isLeaf();


    /**
     * Checks if the node is empty.
     *
     * @return True if the node is empty, false otherwise.
     */
    boolean isEmpty();

}




/**
 * An implementation of the BintreeNode interface representing an empty node.
 * 
 * This class provides a single instance of empty node, and methods to check
 * if it is a leaf node or empty.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
class EmptyNode implements BintreeNode {
    private static EmptyNode instance = new EmptyNode();

    private EmptyNode() {
    }


    /**
     * Returns the singleton instance of an empty node.
     *
     * @return The singleton instance of an empty node.
     */
    public static EmptyNode getInstance() {
        return instance;
    }


    /**
     * Checks if the node is a leaf node (always returns false for an empty
     * node).
     *
     * @return Always false for an empty node.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Checks if the node is empty (always returns true for an empty node).
     *
     * @return Always true for an empty node.
     */
    public boolean isEmpty() {
        return true;
    }

}




/**
 * An implementation of the BintreeNode interface representing internal node.
 * 
 * This class represents an internal node with children for each quadrant in
 * two-dimensional space. It provides methods to access and set its children.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

class InternalNode implements BintreeNode {
    private BintreeNode[] children;

    /**
     * Constructs an internal node with space for children in four quadrants.
     */
    public InternalNode() {
        children = new BintreeNode[4]; // Four quadrants

    }


    /**
     * Checks if the node is a leaf node (always returns false for internal
     * node).
     *
     * @return Always false for an internal node.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Checks if the node is empty (always returns false for internal node).
     *
     * @return Always false for an internal node.
     */
    public boolean isEmpty() {
        return false;
    }


    /**
     * Gets the child node in the specified quadrant.
     *
     * @param quadrant
     *            The quadrant index (0-3) representing the child's position.
     * @return The child node in the specified quadrant.
     */
    public BintreeNode getChild(int quadrant) {
        return children[quadrant];
    }


    /**
     * Sets the child node in the specified quadrant.
     *
     * @param quadrant
     *            The quadrant index (0-3) representing the child's position.
     * @param child
     *            The child node to set in the specified quadrant.
     */
    public void setChild(int quadrant, BintreeNode child) {
        children[quadrant] = child;
    }


    /**
     * Gets an array of all the children nodes.
     *
     * @return An array containing the children nodes.
     */
    public BintreeNode[] getChildren() {
        return children;
    }

}




/**
 * An implementation of the BintreeNode interface representing a leaf node.
 * 
 * This class represents leaf node containing seminar data. It always returns
 * true for the isLeaf() method, indicating that it's a leaf node.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

class LeafNode implements BintreeNode {
    private Seminar data;

    /**
     * Constructs a leaf node with the provided seminar data.
     *
     * @param data
     *            The seminar data to be stored in the leaf node.
     */
    public LeafNode(Seminar data) {
        this.data = data;
    }


    /**
     * Checks if the node is a leaf node (always returns true for leaf node).
     *
     * @return Always true for a leaf node.
     */
    public boolean isLeaf() {
        return true;
    }


    /**
     * Checks if the node is empty (always returns false for a leaf node).
     *
     * @return Always false for a leaf node.
     */
    public boolean isEmpty() {
        return false;
    }


    /**
     * Gets the seminar data stored in the leaf node.
     *
     * @return The seminar data stored in the leaf node.
     */
    public Seminar getData() {
        return data;
    }

}




/**
 * A binary tree implementation used for storing and searching seminar
 * locations.
 * 
 * This class represents a bintree structure for efficiently storing seminar
 * locations based on their coordinates. The binary tree is used to
 * organize seminars into quadrants, making location-based searches more
 * efficient.
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */
public class Bintree {
    private BintreeNode root;

    /**
     * Constructs a new Bintree with an empty root node.
     */
    public Bintree() {

        setRoot(EmptyNode.getInstance());
    }


    /**
     * Checks whether the binary tree is empty.
     *
     * @return true if the binary tree is empty, false
     *         otherwise.
     */
    public boolean isEmpty() {
        return getRoot() instanceof EmptyNode;
    }


    /**
     * Inserts a seminar into the binary tree.
     * 
     * @param data
     *            The seminar data to insert.
     * @param worldSize
     *            The size of the world (bounding area).
     */
    public void insert(Seminar data, int worldSize) {
        setRoot(insert(getRoot(), data, (short)0, (short)0, worldSize, 0));
    }


    private BintreeNode insert(
        BintreeNode node,
        Seminar data,
        short x0,
        short y0,
        int size,
        int level) {
        if (node.isEmpty()) {
            return createLeafNode(data);
        }
        else if (node.isLeaf()) {
            return insertLeafNode((LeafNode)node, data, x0, y0, size, level);
        }
        else if (node instanceof InternalNode) {
            return insertInternalNode((InternalNode)node, data, x0, y0, size,
                level);
        }
        else {
            throw new IllegalStateException("Unexpected node type encountered");
        }
    }


    private LeafNode createLeafNode(Seminar data) {
        return new LeafNode(data);
    }


    private BintreeNode insertLeafNode(
        LeafNode leafNode,
        Seminar data,
        short x0,
        short y0,
        int size,
        int level) {
        if (leafNode.getData().x() == data.x() && leafNode.getData().y() == data
            .y()) {
            return convertToInternalNode(leafNode, data, x0, y0, size, level);
        }
        else {
            return createInternalNodeWithExistingAndNewData(leafNode, data, x0,
                y0, size, level);
        }
    }


    private BintreeNode insertInternalNode(
        InternalNode internalNode,
        Seminar data,
        short x0,
        short y0,
        int size,
        int level) {
        int quadrant = getQuadrant(data.x(), data.y(), x0, y0, size, level);
        int newSize = size / 2;
        short newX0 = (short)(x0 + newSize * (quadrant % 2));
        short newY0 = (short)(y0 + newSize * (quadrant / 2));
        internalNode.setChild(quadrant, insert(internalNode.getChild(quadrant),
            data, newX0, newY0, newSize, level + 1));
        return internalNode;
    }


    private InternalNode convertToInternalNode(
        LeafNode leafNode,
        Seminar data,
        short x0,
        short y0,
        int size,
        int level) {
        InternalNode internalNode = new InternalNode();
        internalNode.setChild(getQuadrant(leafNode.getData().x(), leafNode
            .getData().y(), x0, y0, size, level), leafNode);
        internalNode.setChild(getQuadrant(data.x(), data.y(), x0, y0, size,
            level), new LeafNode(data));
        return internalNode;
    }


    private InternalNode createInternalNodeWithExistingAndNewData(
        LeafNode leafNode,
        Seminar data,
        short x0,
        short y0,
        int size,
        int level) {
        InternalNode internalNode = new InternalNode();
        internalNode.setChild(getQuadrant(leafNode.getData().x(), leafNode
            .getData().y(), x0, y0, size, level), leafNode);
        internalNode.setChild(getQuadrant(data.x(), data.y(), x0, y0, size,
            level), new LeafNode(data));
        return internalNode;
    }


    private int getQuadrant(
        int dataX,
        int dataY,
        short x0,
        short y0,
        int size,
        int level) {
        int k = 2; // Number of dimensions

        int discriminator = (level % k == 0) ? dataX : dataY;
        int midpoint = (level % k == 0) ? x0 + size / 2 : y0 + size / 2;

        if (discriminator < midpoint) {
            if (level % k == 0) {
                return 0; // Left-bottom quadrant
            }
            else {
                return 2; // Left-top quadrant
            }
        }
        else {
            if (level % k == 0) {
                return 1; // Right-bottom quadrant
            }
            else {
                return 3; // Right-top quadrant
            }
        }
    }


    /**
     * Performs a preorder traversal of the binary tree, starting from root
     * node.
     * Prints the tree structure and its elements.
     */
    public void preorderTraversal() {
        System.out.println("Location Tree:");
        if (getRoot() == null || getRoot().isEmpty()) {
            System.out.println("E");
        }
        else {
            preorderTraversal(getRoot(), 0);
        }
    }


    private void preorderTraversal(BintreeNode node, int depth) {
        if (node != null) {
            StringBuilder indentation = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                indentation.append("  ");
            }

            if (node.isLeaf()) {
                LeafNode leafNode = (LeafNode)node;
                Seminar seminar = leafNode.getData();
                int id = seminar.id();

                System.out.println(indentation.toString()
                    + "Leaf with 1 objects: " + id);
            }
            else if (node instanceof InternalNode) {
                InternalNode internalNode = (InternalNode)node;
                System.out.println(indentation.toString() + "I");
                for (BintreeNode child : internalNode.getChildren()) {
                    preorderTraversal(child, depth + 1);
                }
            }
            else {
                System.out.println(indentation.toString() + "E");
            }
        }
    }


    /**
     * Deletes a leaf node with the specified coordinates (x, y) from binary
     * tree.
     * 
     * @param x
     *            The x-coordinate of the node to delete.
     * @param y
     *            The y-coordinate of the node to delete.
     * @param size
     *            The worldsize.
     */
    public void delete(int x, int y, int size) {
        setRoot(delete(getRoot(), x, y, size));
    }


    private BintreeNode delete(BintreeNode node, int x, int y, int worldSize) {
        if (node == null || node.isEmpty()) {
            // Node not found or tree is empty, nothing to delete
            return node;
        }

        if (node.isLeaf()) {
            LeafNode leafNode = (LeafNode)node;
            if (leafNode.getData().x() == x && leafNode.getData().y() == y) {
                // Found the node to delete, return an empty node
                return EmptyNode.getInstance();
            }
        }

        if (node instanceof InternalNode) {
            InternalNode internalNode = (InternalNode)node;
            // Calculate the quadrant for the given (x, y) coordinates
            int quadrant = getQuadrant(x, y, (short)0, (short)0, worldSize, 0);
            // Recursively delete the node in the appropriate quadrant
            internalNode.setChild(quadrant, delete(internalNode.getChild(
                quadrant), x, y, worldSize));
        }

        return node;
    }


    /**
     * Retrieves the root node of the Bintree.
     *
     * @return The root node of the Bintree.
     */
    public BintreeNode getRoot() {
        return root;
    }


    /**
     * Sets the root node of the Bintree to the provided node.
     *
     * @param root
     *            The new root node to be set in the Bintree.
     */
    public void setRoot(BintreeNode root) {
        this.root = root;
    }


    /**
     * Gets the root node of the Bintree as a LeafNode.
     *
     * @return The root node of the Bintree as a LeafNode.
     */
    public LeafNode getRoot1() {

        return (LeafNode)root;
    }


    /**
     * Gets the root node of the Bintree as an InternalNode.
     *
     * @return The root node of the Bintree as an InternalNode.
     */
    public InternalNode getRoot2() {

        return (InternalNode)root;
    }

}
