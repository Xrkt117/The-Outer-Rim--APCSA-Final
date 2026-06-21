package Ships;

// StarterShip: the single ship players start with. It can be upgraded with parts.
public class StarterShip extends Ship {

    public StarterShip() {
        super(
            "Starter Ship",
            20,   // starting fuel
            20,   // max fuel
            1,    // speed
            20,   // cargo capacity
            null
        );
    }

    public String getDescription() {
        return "A reliable starter vessel. Upgrade it to specialize.";
    }
}
