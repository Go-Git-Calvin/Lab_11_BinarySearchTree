/**
 * BinaryTree.java
 *
 * Created by Calvin Wong on 11/3/14.
 *
 * Implementing leaves(), height(), and defoliate() methods for existing Generic BinaryTree
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * Class for a binary tree that stores type E objects.
 */
public class BinaryTree<E> implements Serializable {

    /**
     * Class to encapsulate a tree node.
     */
    protected static class Node<E> implements Serializable {
        // Data Fields

        /**
         * The information stored in this node.
         */
        public E data;
        /**
         * Reference to the left child.
         */
        public Node<E> left;
        /**
         * Reference to the right child.
         */
        public Node<E> right;

        // Constructors

        /**
         * Construct a node with given data and no children.
         *
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods

        /**
         * Returns a string representation of the node.
         *
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Data Field
    /**
     * The root of the binary tree
     */
    protected Node<E> root;

    /**
     * Construct an empty BinaryTree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a BinaryTree with a specified root.
     * Should only be used by subclasses.
     *
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree
     * as its left subtree and rightTree as its right subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     *
     * @return The left subtree or null if either the root or
     * the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     *
     * @return the right sub-tree or
     * null if either the root or the
     * right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     *
     * @return the data field of the root
     * or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a preorder traversal.
     *
     * @param node  The local root
     * @param depth The depth
     * @param sb    The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }


    /**
     * Method to read a binary tree.
     *
     * @param bR The input file
     * @return The binary tree
     * @throws IOException If there is an input error
     * @pre The input consists of a preorder traversal
     * of the binary tree. The line "null" indicates a null tree.
     */
    public static BinaryTree<String> readBinaryTree(BufferedReader bR)
            throws IOException {
        // Read a line and trim leading and trailing spaces.
        String data = bR.readLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(bR);
            BinaryTree<String> rightTree = readBinaryTree(bR);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

    /**
     * Method to return the preorder traversal of the binary tree
     * as a sequence of strings each separated by a space.
     *
     * @return A preorder traversal as a string
     */
    public String preorderToString() {
        StringBuilder stb = new StringBuilder();
        preorderToString(stb, root);
        return stb.toString();
    }

    private void preorderToString(StringBuilder stb, Node<E> root) {
        stb.append(root);
        if (root.left != null) {
            stb.append(" ");
            preorderToString(stb, root.left);
        }
        if (root.right != null) {
            stb.append(" ");
            preorderToString(stb, root.right);
        }
    }

    /**
     * Method to return the postorder traversal of the binary tree
     * as a sequence of strings each separated by a space.
     *
     * @return A postorder traversal as a string
     */
    public String postorderToString() {
        StringBuilder stb = new StringBuilder();
        postorderToString(stb, root);
        return stb.toString();
    }

    private void postorderToString(StringBuilder stb, Node<E> root) {
        if (root.left != null) {
            postorderToString(stb, root.left);
            stb.append(" ");
        }
        if (root.right != null) {
            postorderToString(stb, root.right);
            stb.append(" ");
        }
        stb.append(root);
    }

    /**
     * A method to display the inorder traversal of a binary tree
     * placeing a left parenthesis before each subtree and a right
     * parenthesis after each subtree. For example the expression
     * tree shown in Figure 6.12 would be represented as
     * (((x) + (y)) * ((a) / (b))).
     *
     * @return An inorder string representation of the tree
     */
    public String inorderToString() {
        StringBuilder stb = new StringBuilder();
        inorderToString(stb, root);
        return stb.toString();
    }

    private void inorderToString(StringBuilder stb, Node<E> root) {
        if (root.left != null) {
            stb.append("(");
            inorderToString(stb, root.left);
            stb.append(") ");
        }
        stb.append(root);
        if (root.right != null) {
            stb.append(" (");
            inorderToString(stb, root.right);
            stb.append(")");
        }
    }

    /**
     * Added methods leaves(), height(), and defoliate()
     *
     * @return An method to get sum of leaves of the tree, the height of tree,
     * and method to remove all nodes from tree
     */

    // returns the number of leaves in tree
    public int leaves() {
        return leaves(root);
    }

    public int leaves(Node<E> node) {

        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        } else {
            return leaves(node.left) + leaves(node.right);
        }
    }

    // returns the height of tree
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node<E> node) {

        if (node == null)
            return 0;
        else {
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    public void defoliate() {
        defoliate(root);
    }

    // removes all leaves from tree
    public void defoliate(Node<E> node) {
        if (node.left != null) {
            if (isLeaf()) {
                node.left = null;
            } else
                defoliate(node.left);
        }
        if (node.right != null) {
            if (isLeaf()) {
                node.right = null;
            } else
                defoliate(node.right);
        }
    }
} // end of class



