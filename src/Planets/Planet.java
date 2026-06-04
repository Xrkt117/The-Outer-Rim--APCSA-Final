package Planets;

import java.util.*;
import Items.Item;

// Abstract Planet class (empty shell)
public abstract class Planet {
    private String name;
    private ArrayList<Item> market;

    public Planet(String name, ArrayList<Item> market) {
        this.name = name;
        this.market = market == null ? new ArrayList<>() : market;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getMarket() {
        return market;
    }

}
