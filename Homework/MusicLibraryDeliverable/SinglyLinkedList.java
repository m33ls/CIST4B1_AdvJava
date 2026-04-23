public class SinglyLinkedList<T> {
    public Node<T> head;
    public int length;

    SinglyLinkedList() {
        this.head = null;
        length = 0;
    }

    // ==== INSERTS ====
    public void append(T data) {
        if (head == null) {
            head = new Node<T>(data);
            this.length++;
        } else {
            Node<T> curr = head;
            while ( curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node<T>(data);
            this.length++;
        }
    }

    public void prepend(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.next = this.head;
        this.head = newNode;
        this.length++;
    }

    public void print() {
        Node<T> curr = head;
        while ( curr.next != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println(curr.data);
    }

    public void insertAt(T data, int index) {
        if ( index < 0 ) {
            index = this.length + index;
        }
        if ( index < 0 || index >= this.length) {
            System.err.println("Error: Index out of bounds");
            return;
        }
        if ( index == 0) {
            prepend(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> curr = head;
        for (int i = 0; i < index -1; i++) {
            curr = curr.next;
        }
        newNode.next = curr.next;
        curr.next = newNode;
        this.length++;
    }
    
    // ==== REMOVE ====
    public T removeValue(T data) {
        if (this.head == null) {
            return null;
        }
        // Remove head
        if ( this.head.data == data) {
            T returnData = head.data;
            head = head.next;
            this.length--;
            return returnData;
        }
        // Remove anywhere else
        Node<T> curr = this.head;
        while(curr.next != null) {
            if (curr.next.data == data) {
                T returnData = (T) curr.next.data;
                curr.next = curr.next.next;
                this.length--;
                return returnData;
            }
            curr = curr.next;
        }
        return null;
    }

    public T removeIndex(int idx) {
        if (this.head == null) {
            return null;
        }
        // Remove head
        if ( idx == 0) {
            T returnData = head.data;
            head = head.next;
            this.length--;
            return returnData;
        }
        
        // Remove anywhere else
        Node<T> curr = this.head; // NOT DONE
        for (int i = 0; i < idx; i++) {
            if (i == idx) {
                T returnData = (T) curr.next.data;
                curr.next = curr.next.next;
                this.length--;
                return returnData;
            }
            curr = curr.next;
        }
        return null;
    }

    // ==== search ====
    public boolean search(T target) {
        if (this.head == null) {
            return false;
        } 
        Node<T> curr = this.head;
        while (curr != null) {
            if (curr.data == target) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // ==== UTILS ====
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    public int getLength() {
        return this.length;
    }
}

