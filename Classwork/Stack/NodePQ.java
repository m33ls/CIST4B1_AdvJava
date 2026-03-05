// Priority Queue Node
public class NodePQ<T> {
    // Data
    public T data;
    // Link
    public NodePQ left; 
    public NodePQ right;

    NodePQ(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}