public class Zoo {
    public static void main(String[] args) {
        HashTable hashtable = new HashTable(20);
        BinarySearchTree bst = new BinarySearchTree();

        hashtable.put("Skipper", "Penguin", 3);
        hashtable.put("Kowalski", "Penguin", 5);
        hashtable.put("Rico", "Penguin", 6);
        hashtable.put("Private", "Penguin", 4);
        hashtable.put("King Julian", "Lemur", 8);
        hashtable.put("Maurice", "Lemur", 6);
        hashtable.put("Mort", "Lemur", 7);
        hashtable.put("Mason", "Chimpanzee", 9);
        hashtable.put("Phil", "Chimpanzee", 2);
        hashtable.put("Marlene", "Otter", 1);

        bst.insert(hashtable.get("Skipper"));
        bst.insert(hashtable.get("Kowalski"));
        bst.insert(hashtable.get("Rico"));
        bst.insert(hashtable.get("Private"));
        bst.insert(hashtable.get("King Julian"));
        bst.insert(hashtable.get("Maurice"));
        bst.insert(hashtable.get("Mort"));
        bst.insert(hashtable.get("Mason"));
        bst.insert(hashtable.get("Phil"));
        bst.insert(hashtable.get("Marlene"));

        System.out.println("Displaying animals in list of priority high to low.");
        bst.displayReverseOrder();
    }
}
