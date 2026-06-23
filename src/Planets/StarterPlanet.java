package Planets;

import Items.*;

public class StarterPlanet extends Planet {
    public StarterPlanet(String name) {
        super(name, "The center of the Outer Rim", 0);
    }

    @Override
    public void generateMarket() {
        // Earth starts with safe starter goods plus only a little fuel and one upgrade part.
        addMarketItem(new Food(12));
        addMarketItem(new Medicine(8));
        addMarketItem(new Machinery(4));
        addMarketItem(new FuelCanister(6));
        addMarketItem(new ShipParts(1));
    }
}
