import java.util.ArrayList;

public class Order {
    double revenue;
    int customers;
    int completed;
    ArrayList<Item> completed_items;
    Queue<Item> queue;
    SinglyLinkedList<Item> kitchens;

    public Order() {
        customers = 0;
        revenue = 0;
        completed = 0;
        queue = new Queue(500);
        kitchens = new SinglyLinkedList<>();
        completed_items = new ArrayList<>();

        kitchens.append(null);
        kitchens.append(null);
    }

    public void addItem(Item item) {
        queue.enqueue(item);
        revenue += item.price;
        customers++;
    }

    public void step() {
        Node<Item> node = kitchens.head;

        while (node != null) {
            Item item = (Item) node.data;

            if (item != null) {
                if (!item.steps.isEmpty()) {
                        System.out.println(item.steps.pop());
                        if (item.steps.peek() == null) {
                            completed++;
                            completed_items.add(item);
                            node.data = null;
                        }
                } else {
                    completed++;
                    completed_items.add(item);
                    //System.out.println("Order completed! Total completed: " + completed);
                    node.data = null;
                }
            }

            node = node.next;
        }

        node = kitchens.head;
        while (node != null) {
            if (node.data == null && !queue.isEmpty()) {
                node.data = queue.dequeue();
            }
            node = node.next;
        }
    }

    public static void main(String[] args) {
        int cycles = 10;

        ArrayList<Item> menu = new ArrayList<>();
        menu.add(new Hamburger());
        menu.add(new Fries());
        
        DineInOrder DIorder = new DineInOrder();
        TakeoutOrder TAorder = new TakeoutOrder();

        for (int i = 0; i < cycles; i++) {
            System.out.println("# Timestep " + i);
            // % chance customer orders random item per step per station
            if (Math.random() < 0.4) {
                System.out.println("Customer enters");
                DIorder.DIOrder(menu.get(1));
            } 
            if (Math.random() < 0.4) {
                System.out.println("Car pulls up to drive through");
                TAorder.TAOrder(new Hamburger());
            }

            DIorder.step();
            TAorder.step();
        }

        System.out.println();
        System.out.println("Statistics");
        System.out.println("Total Customers Processed: " + (TAorder.customers + DIorder.customers));
        System.out.println("Total Orders Completed: " + (DIorder.completed + TAorder.completed));
        System.out.println("Total Orders Completed (From ArrayList): " + (DIorder.completed_items.size() + TAorder.completed_items.size()));
        System.out.println("Dine-in Order Count: " + DIorder.customers);
        System.out.println("Takeaway Order Count: " + TAorder.customers);
        System.out.println("Total Revenue: $" + (TAorder.revenue + DIorder.revenue));
    }
}
