class Item {
    Stack<String> steps;
    public double price;
    public String name;
    public Item() {
        steps = new Stack<>(4);
    }
}

class Hamburger extends Item {
    public Hamburger() {
        price = 4.99;
        name = "Hamburger";
        steps.push("DONE: Burger assembled");
        steps.push("Cheese added to patty");
        steps.push("Patty cooked");
        steps.push("Bun prepped");
    }
}

class Fries extends Item {
    public Fries() {
        price = 2.99;
        name = "Fries";
        steps.push("DONE: Fried potatoes");
        steps.push("Potatoes added to oil");
    }
}