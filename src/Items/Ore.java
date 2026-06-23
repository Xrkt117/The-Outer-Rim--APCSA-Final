package Items;

public class Ore extends Item {

    public Ore(int quantity) {
        // Ore is common at mining planets but becomes worth more at trade hubs.
        super(
            "Ore",
            10,
            quantity,
            "Just rocks. Very expensive rocks."
        );
    }
}
