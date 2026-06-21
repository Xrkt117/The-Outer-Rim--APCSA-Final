package Planets;

import Items.*;

public class StarterPlanet extends Planet {
    public StarterPlanet(String name) {
        super(name, "The center of the Outer Rim", 0);
    }

    @Override
    public void generateMarket() {
        addMarketItem(new Food(12));
        addMarketItem(new Medicine(8));
        addMarketItem(new Machinery(4));
        addMarketItem(new FuelCanister(80));
        addMarketItem(new ShipParts(4));
    }
}
