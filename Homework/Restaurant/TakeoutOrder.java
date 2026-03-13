public class TakeoutOrder extends Order {
    public int takeoutCount;
    
    public TakeoutOrder() {

    }

    public void TAOrder(Item item) {
        System.out.println("Customer orders "+item.name+" at drive through");
        addItem(item);
    }
}