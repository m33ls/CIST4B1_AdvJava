public class SinglyLinkedList<T> {
    public Node<T> head;
    public int length;

    SinglyLinkedList() {
        this.head = null;
        length = 0;
    }

    public void append(<T> data) {
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
}