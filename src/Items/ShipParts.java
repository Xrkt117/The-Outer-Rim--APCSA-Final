package Items;

public class ShipParts extends Item {

    public ShipParts(int quantity) {
        // Upgrades consume these, so markets keep the stock low.
        super(
            "Ship Parts",
            65,
            quantity,
            "Rare upgrade components that every pilot wants and few markets stock."
        );
    }
}
