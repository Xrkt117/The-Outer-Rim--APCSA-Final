package Items;

public class Titanium extends Item {

    public Titanium(int quantity) {
        // Higher value metal for longer trade routes once the player can travel farther.
        super(
            "Titanium",
            45,
            quantity,
            "Strong metal used for hulls and station frames."
        );
    }
}
