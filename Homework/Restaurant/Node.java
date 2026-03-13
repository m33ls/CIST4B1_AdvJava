public class Node<T> {
    // Data
    public T data;
    // Link
    public Node<T> next;

    Node(T data){
        this.data = data;
        this.next = null;
    }
}