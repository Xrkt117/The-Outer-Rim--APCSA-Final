package Planets;

import Items.*;

public class MiningPlanet extends Planet {

    public MiningPlanet(String name, int distance) {
        super(name, "A resource-heavy mining colony rich in raw ore.", distance);
    }

    //@override
    public void generateMarket() {
        addMarketItem(new Ore(18));
        addMarketItem(new Titanium(8));
        addMarketItem(new Copper(12));
        addMarketItem(new FuelCanister(10));
    }
}
