import java.util.Scanner;

// Binary Search Tree Implementation

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BST {
    Node root;

    public BST() {
        root = null;
    }

    void insert(int key) {
        root = insertNode(root, key);
    }

    Node insertNode(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertNode(root.left, key);
        } else if (key > root.key) {
            root.right = insertNode(root.right, key);
        }
        return root;
    }

    void delete(int key) {
        root = deleteNode(root, key);
    }

    Node deleteNode(Node root, int key) {
        if (root == null) return root;

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.key = minValue(root.right);
            root.right = deleteNode(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    boolean search(int key) {
        return searchNode(root, key);
    }

    boolean searchNode(Node root, int key) {
        if (root == null) return false;
        if (root.key == key) return true;
        if (root.key < key) return searchNode(root.right, key);
        return searchNode(root.left, key);
    }

    void inorder() {
        inorderTraversal(root);
    }

    void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.key + " ");
            inorderTraversal(root.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter number to insert: ");
                    int insertNum = scanner.nextInt();
                    tree.insert(insertNum);
                    System.out.println(insertNum + " inserted.");
                    break;

                case 2:
                    System.out.print("Enter number to delete: ");
                    int deleteNum = scanner.nextInt();
                    tree.delete(deleteNum);
                    System.out.println(deleteNum + " deleted.");
                    break;

                case 3:
                    System.out.print("Enter number to search: ");
                    int searchNum = scanner.nextInt();
                    boolean found = tree.search(searchNum);
                    if (found) {
                        System.out.println(searchNum + " is present in the tree.");
                    } else {
                        System.out.println(searchNum + " is not present in the tree.");
                    }
                    break;

                case 4:
                    System.out.println("BST elements in-order:");
                    tree.inorder();
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}