package Items;

public abstract class Item {
    private String name;
    public String description;
    private int basePrice;
    private int quantity;

    public Item(String name, int price, int quantity, String description) {
        this.name = name;
        this.basePrice = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
