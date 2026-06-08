package Planets;

import Items.*;

public class TradePlanet extends Planet {

    public TradePlanet(String name, int distance) {
        super(name, "A balanced commercial hub with stable prices.", distance);
    }

    @Override
    public void generateMarket() {
        addMarketItem(new Ore(5));
        addMarketItem(new Food(5));
        addMarketItem(new Medicine(5));
        addMarketItem(new Fuel(5));
        addMarketItem(new ShipParts(5));
    }
}