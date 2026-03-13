public class Queue<T> {
    private int size;
    private int head;
    private int tail;
    private Object[] data;

    public Queue(int size) {
        data = new Object[size];
        this.size = 0;
        head = 0;
        tail = 0;
    }

    public void enqueue(T item) {
        if (size != data.length) {
            this.data[this.tail] = item;
            this.tail = (this.tail + 1) % data.length;
            size++;
        } else {
            System.err.println("Error: Queue full");
        }
    }

    public T dequeue() {
        if (size != 0) {
            T retValue = (T) this.data[this.head];
            this.data[this.head] = null;
            this.head = (this.head + 1) % data.length;
            this.size--;
            return retValue;
        } else {
            System.err.println("Error: Attempted to pop from empty queue.");
            return null;
        }
    }

    public T peek() {
        if (!this.isEmpty()) {
            return (T) data[head];
        } else {
            return null;
        }
    }

    public boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}