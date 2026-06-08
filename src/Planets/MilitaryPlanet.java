package Planets;

import Items.*;

public class MilitaryPlanet extends Planet {

    public MilitaryPlanet(String name, int distance) {
        super(name, "A guarded military outpost with high demand for supplies.", distance);
    }

    @Override
    public void generateMarket() {
        addMarketItem(new Ore(3));
        addMarketItem(new Food(8));
        addMarketItem(new Medicine(8));
        addMarketItem(new Fuel(10));
        addMarketItem(new ShipParts(6));
    }
}