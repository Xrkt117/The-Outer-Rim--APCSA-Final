package Items;

public class Food extends Item {

    public Food(int quantity) {
        // Basic supply item. It is cheap near Earth but useful for colony routes.
        super(
            "Food",
            15,
            quantity,
            "Nobody knows what it's made of. Everyone keeps on eating it anyway."
        );
    }
}
