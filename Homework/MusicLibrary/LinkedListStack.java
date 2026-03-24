public class LinkedListStack<T> {
    private int currSize;
    private SinglyLinkedList<T> data;

    public LinkedListStack() {
        data = new SinglyLinkedList<T>();
        this.currSize = 0;
    }

    public void push(T item) {
        data.prepend(item);
        this.currSize++;
    }

    public T pop() {
        if (!this.isEmpty()) {
            currSize--;
            T retValue = (T) data.head.data;
            data.removeIndex(0);
            return retValue;
        } else {
            System.err.println("Error: Attempted to pop from empty stack.");
            return null;
        }
    }

    public T peek() {
        if (!this.isEmpty()) {
            return (T) data.head.data;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}