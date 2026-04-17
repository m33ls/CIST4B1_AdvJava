class AVLTree {

    class Node {
        String word;
        Node left, right;
        int height;

        Node(String word) {
            this.word = word;
            this.height = 1;
        }
    }

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right Rotation
    Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert
    Node insert(Node node, String word) {
        if (node == null)
            return new Node(word);

        if (word.compareTo(node.word) < 0)
            node.left = insert(node.left, word);
        else if (word.compareTo(node.word) > 0)
            node.right = insert(node.right, word);
        else
            return node; // no duplicates

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Rotations
        if (balance > 1 && word.compareTo(node.left.word) < 0)
            return rotateRight(node);

        if (balance < -1 && word.compareTo(node.right.word) > 0)
            return rotateLeft(node);

        if (balance > 1 && word.compareTo(node.left.word) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && word.compareTo(node.right.word) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void insert(String word) {
        root = insert(root, word);
    }

    // Search
    boolean search(Node node, String word) {
        if (node == null)
            return false;

        if (word.equals(node.word))
            return true;

        if (word.compareTo(node.word) < 0)
            return search(node.left, word);
        else
            return search(node.right, word);
    }

    boolean search(String word) {
        return search(root, word);
    }

    // Inorder traversal with balance
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.word + " (BF=" + getBalance(node) + ")");
            inorder(node.right);
        }
    }

    void printTree() {
        inorder(root);
    }
}