package Planets;

import Items.*;

public class MilitaryPlanet extends Planet {

    public MilitaryPlanet(String name, int distance) {
        super(name, "A guarded military outpost with high demand for supplies.", distance);
    }

    @Override
    public void generateMarket() {
        addMarketItem(new Food(6));
        addMarketItem(new Medicine(8));
        addMarketItem(new Electronics(4));
        addMarketItem(new FuelCanister(4));
        addMarketItem(new ShipParts(1));
    }
}
