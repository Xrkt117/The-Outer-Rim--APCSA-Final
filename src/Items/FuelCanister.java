package Items;
// Medicine item (empty shell)
public class FuelCanister extends Item {
    private int fuelAmount = 5;

    public FuelCanister(int quantity) {
        super(
            "Fuel", 
            5,
            quantity, 
            "Fluid propellant" 
        );
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

}
