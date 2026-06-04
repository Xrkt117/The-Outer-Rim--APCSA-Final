package Planets;

import Items.Food;
import Items.Fuel;
import Items.Item;
import Items.Medicine;
import Items.Ore;
import Items.ShipParts;

// MiningPlanet (empty shell)
public class MiningPlanet extends Planet {

    public MiningPlanet(String name, int distance) {
        super(name, "A resource-heavy mining colony rich in raw ore.", distance);
    }

    @override
    public void generateMarket() {
        addMarketItem(new Ore(10));
        addMarketItem(new Food(3));
        addMarketItem(new Medicine(2));
        addMarketItem(new Fuel(5));
        addMarketItem(new ShipParts(2));
    }
}
