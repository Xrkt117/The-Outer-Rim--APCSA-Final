package Items;

public class Electronics extends Item {

    public Electronics(int quantity) {
        // Expensive cargo with strong demand in the Outer Worlds.
        super(
            "Electronics",
            90,
            quantity,
            "Delicate circuits and devices for advanced settlements."
        );
    }
}
