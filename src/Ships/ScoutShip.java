package Ships;
// ScoutShip subclass (empty shell)
public class ScoutShip extends Ship {

    public ScoutShip() {
        super(
            "Scout Ship", 
            100,
            100,
            1,
            4,
            40,
            null
        );
    } 

    public String getDescription() {
        return "Fast and fuel-efficient, but limited cargo capacity.";
    }
}
