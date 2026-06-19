package Planets;

import Items.*;

public class StarterPlanet extends Planet {
    public StarterPlanet(String name) {
        super(name, "The center of the Outer Rim", 0);
    }

    @Override
    public void generateMarket() {
        addMarketItem(new Food(5));
        addMarketItem(new Medicine(6));
        addMarketItem(new Fuel(4));
    }
}
