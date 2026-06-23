package Items;
// Medicine item (empty shell)
public class FuelCanister extends Item {
    private int fuelAmount = 5;

    public FuelCanister(int quantity) {
        super(
            "Fuel Canister",
            25,
            quantity,
            "A portable fuel cell that can refill 5 kL of ship fuel."
        );
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

}
