package Ships;

public class BalancedShip extends Ship {

    public BalancedShip() {
        super(
            "Balanced Ship", 
            75,
            75,
            2,
            70,
            null
        );
    } 

    public String getDescription() {
        return "Large cargo capacity but slow speed";
    }
}
