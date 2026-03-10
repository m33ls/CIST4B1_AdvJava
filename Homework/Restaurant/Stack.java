public class Stack<T> {
    private int maxSize;
    private int currSize;
    private Object[] data;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        data = new Object[maxSize];
        this.currSize = 0;
    }

    public void push(T item) {
        if (!this.isFull()) {
            data[this.currSize] = item;
            this.currSize++;
        } else {
            // need to grow array
            Object[] newData = new Object[maxSize * 2];
            for (int i = 0; i < this.maxSize; i++) {
                newData[i] = this.data[i];
            }
            newData[this.currSize] = item;
            this.maxSize *= 2;
            this.currSize++;
            this.data = newData;
        }
    }

    public T pop() {
        if (!this.isEmpty()) {
            currSize--;
            T retValue = (T) data[currSize];
            data[currSize] = null;
            return retValue;
        } else {
            System.err.println("Error: Attempted to pop from empty stack.");
            return null;
        }
    }

    public T peek() {
        if (!this.isEmpty()) {
            return (T) data[currSize - 1];
        } else {
            return null;
        }
    }

    public boolean isFull() {
        return currSize == this.maxSize;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }
}