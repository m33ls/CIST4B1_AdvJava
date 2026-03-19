public class BinarySearchTree {
    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public void addIterative(int value) {

        if (this.root == null) {
            this.root = new Node(value);
        } else {
            Node current = this.root;

            while (current.left != null || current.right != null) {
                if (value <= current.data) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (value <= current.data) {
                current.left = new Node(value);
            } else {
                current.right = new Node(value);
            }
        }
    }

    public boolean search(int target) {
        return searchRecursive(target, root);
    }

    private boolean searchRecursive(int target, Node current) {
        if (current == null) {
            return false;
        }
        if (target == current.data) {
            return true;
        } else if (target < current.data) {
            return searchRecursive(target, current.left);
        } else {
            return searchRecursive(target, current.right);
        }
    }

    /* * Start at root.
    * Compare
        * If equal -> ret
        * If less than go left
        * If greater than go right
    * Repeat until found or exhausted
    */
   public boolean searchIterative(int target) {
        Node current = this.root;
        while (current != null) {
            if (current.data == target) {
                return true;
            } else if (target < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void remove (int value) {
        // call helper
        root = removeRecursive(value, root);
    }

    // javabortion
    private Node removeRecursive(int value, Node current) {
        if (current == null) {
            return null;
        }
        if (value == current.data) { // found
            // case 1: no children
            if ( current.left == null && current.right == null) {
                return null;
            }

            // case 2: one child
            if ( current.left == null ) {
                return current.right;
            } 
            else if (current.right == null) {
                return current.left;
            }

            // case 3: two children
            int inOrderSuccessor = findMinValue(current.right);
            current.data = inOrderSuccessor;
            current.right = removeRecursive(inOrderSuccessor, current.right);

        } else if (value < current.data) { // search
            current.left = removeRecursive(value, current.left);
        } else {
            current.right = removeRecursive(value, current.right);
        }    
        return current;
    }

    private int findMinValue(Node subRoot) {
        while (subRoot.left != null) {
            subRoot = subRoot.left;
        }
        return subRoot.data;
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.left);
            System.out.println(current.data);
            inOrder(current.right);
        }
    }

    public void preOrder(Node current) {
        if (current != null) {
            System.out.println(current.data);
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void postOrder(Node current) {
        if (current != null) {
            postOrder(current.left);
            postOrder(current.right);
            System.out.println(current.data);
        }
    }
}
