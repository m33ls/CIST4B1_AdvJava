public class Week6 {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>(6);
        myStack.push(10);
        myStack.push(11);
        myStack.push(12);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        NodePQ<Integer> root = new NodePQ<>(72);
        root.left = new NodePQ<>(52);
        root.right = new NodePQ<>(63);
    }
}