package Planets;

import Items.*;

public class TradePlanet extends Planet {

    public TradePlanet(String name, int distance) {
        super(name, "A balanced commercial hub with stable prices.", distance);
    }

    @Override
    public void generateMarket() {
        // Trade outposts are better stocked with finished goods and a few rare parts.
        addMarketItem(new ConsumerGoods(10));
        addMarketItem(new Electronics(6));
        addMarketItem(new Machinery(5));
        addMarketItem(new FuelCanister(5));
        addMarketItem(new ShipParts(2));
    }

    @Override
    public String getTradeRole() {
        return "trade hub with finished goods and rare parts";
    }
}
