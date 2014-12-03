import java.util.Random;

/**
 * TestBinarySearchTree.java
 *
 * Created by Calvin Wong on 11/10/14.
 *
 * Testing all the methods in the Binary Search Tree
 */
public class TestBinarySearchTree {

    public static void main(String[] args) {

        final int MAX_INT = 100;

        BinarySearchTree tree = new BinarySearchTree();

        Random rand = new Random();

        int[] array = new int[]{rand.nextInt(MAX_INT), rand.nextInt(MAX_INT), rand.nextInt(MAX_INT),
                rand.nextInt(MAX_INT), rand.nextInt(MAX_INT), rand.nextInt(MAX_INT), rand.nextInt(MAX_INT),
                rand.nextInt(MAX_INT), rand.nextInt(MAX_INT), rand.nextInt(MAX_INT)};

        for (int number : array) {
            tree.add(number);
        }

        System.out.println("InOrder String: " + tree.inOrderToString());
        System.out.println("Adding 30: " + tree.add(30));
        System.out.println("Remove 99: " + tree.remove(99));
        System.out.println("InOrder toString: " + tree.inOrderToString());
        System.out.println("InOrder toList: " + tree.toList());
        System.out.println("Contains 43: " + tree.contains(43));
        System.out.println("Find 99: " + tree.find(99));
    }

} // end of class
