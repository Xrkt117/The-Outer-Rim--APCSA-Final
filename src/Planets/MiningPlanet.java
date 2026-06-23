package Planets;

import Items.*;

public class MiningPlanet extends Planet {

    public MiningPlanet(String name, int distance) {
        super(name, "A resource-heavy mining colony rich in raw ore.", distance);
    }

    @Override
    public void generateMarket() {
        // Mining worlds are where raw metals are easiest to buy.
        addMarketItem(new Ore(18));
        addMarketItem(new Titanium(8));
        addMarketItem(new Copper(12));
        // Fuel is useful here, but not common enough to ignore planning.
        addMarketItem(new FuelCanister(3));
    }
}
