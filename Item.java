// Abstract Item class (empty shell)
public abstract class Item {
    private String name;
    private int basePrice;
    private int quantity;
    //private String description;

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.basePrice = price;
        this.quantity = quantity;
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

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}