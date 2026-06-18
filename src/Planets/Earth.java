package Planets;

import Items.*;

public class Earth extends Planet {
    public Earth() {
        super("Earth", "The center of the Outer Rim", 0);
    }

    @Override
    public void generateMarket() {
        addMarketItem(new Food(5));
        addMarketItem(new Medicine(6));
        addMarketItem(new Fuel(4));
    }
}
