public class DineInOrder extends Order {
    public int dineInCount;

    public DineInOrder() {

    }   

    public void DIOrder(Item item) {
        System.out.println("Customer orders "+item.name+" inside");
        addItem(item);
    }
}