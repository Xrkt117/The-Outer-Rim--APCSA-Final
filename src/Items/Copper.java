package Items;

public class Copper extends Item {

    public Copper(int quantity) {
        // Cheap metal used for the mining-to-trade-outpost route.
        super(
            "Copper",
            25,
            quantity,
            "Useful metal for wiring, repairs, and machinery."
        );
    }
}
