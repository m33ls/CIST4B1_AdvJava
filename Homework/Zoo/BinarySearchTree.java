import java.util.ArrayList;

class BinarySearchTree {
    class Node {
        int careLevel;
        ArrayList<Animal> animals;
        Node left, right;

        Node(int careLevel) {
            this.careLevel = careLevel;
            this.animals = new ArrayList<>();
            this.left = this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(String name, String species, int careLevel) {
        root = insertRecursive(root, name, species, careLevel);
    }

    public void insert(Animal animal) {
        root = insertRecursive(root, animal);
    }

    private Node insertRecursive(Node node, String name, String species, int careLevel) {
        if (node == null) {
            node = new Node(careLevel);
            node.animals.add(new Animal(species, careLevel));
            return node;
        }

        if (careLevel < node.careLevel) {
            node.left = insertRecursive(node.left, name, species, careLevel);
        } else if (careLevel > node.careLevel) {
            node.right = insertRecursive(node.right, name, species, careLevel);
        } else {
            node.animals.add(new Animal(species, careLevel));
        }
        return node;
    }

    private Node insertRecursive(Node node, Animal animal) {
        int careLevel = animal.careLevel;
        String species = animal.species;
        if (node == null) {
            node = new Node(careLevel);
            node.animals.add(animal);
            return node;
        }

        if (careLevel < node.careLevel) {
            node.left = insertRecursive(node.left, animal);
        } else if (careLevel > node.careLevel) {
            node.right = insertRecursive(node.right, animal);
        } else {
            node.animals.add(animal);
        }
        return node;
    }

    public void remove(int careLevel) {
        root = removeRecursive(root, careLevel);
    }

    private Node removeRecursive(Node node, int careLevel) {
        if (node == null) return null;

        if (careLevel < node.careLevel) {
            node.left = removeRecursive(node.left, careLevel);
        } else if (careLevel > node.careLevel) {
            node.right = removeRecursive(node.right, careLevel);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            int minCareLevel = findMin(node.right);
            node.careLevel = minCareLevel;
            node.right = removeRecursive(node.right, minCareLevel);
        }
        return node;
    }

    private int findMin(Node node) {
        return (node.left == null) ? node.careLevel : findMin(node.left);
    }

    public ArrayList<Animal> search(int careLevel) {
        return searchRecursive(root, careLevel);
    }

    private ArrayList<Animal> searchRecursive(Node node, int careLevel) {
        if (node == null) return null;
        if (careLevel == node.careLevel) return node.animals;
        return careLevel < node.careLevel ? searchRecursive(node.left, careLevel) : searchRecursive(node.right, careLevel);
    }

    public void displayInOrder() {
        displayInOrderRecursive(root);
    }

    private void displayInOrderRecursive(Node node) {
        if (node != null) {
            displayInOrderRecursive(node.left);
            System.out.println("Care Level " + node.careLevel + ": " + node.animals);
            displayInOrderRecursive(node.right);
        }
    }

    public void displayReverseOrder() {
        displayReverseRecursive(root);
    }

    private void displayReverseRecursive(Node node) {
        if (node != null) {
            displayReverseRecursive(node.right);
            System.out.println("Care Level " + node.careLevel + ": " + node.animals);
            displayReverseRecursive(node.left);
        }
    }
}