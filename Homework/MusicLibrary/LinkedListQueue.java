public class LinkedListQueue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // front
    private Node<T> tail; // rear
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.err.println("Error: Attempted to dequeue from empty queue.");
            return null;
        }

        T value = head.data;
        head = head.next;

        // If queue becomes empty, reset tail
        if (head == null) {
            tail = null;
        }

        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) return null;
        return head.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}