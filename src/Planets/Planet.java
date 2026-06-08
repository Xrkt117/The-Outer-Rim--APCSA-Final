package Planets;

import java.util.*;
import Items.Item;

public abstract class Planet {
    private String name;
    private String description;
    private int distance;
    private ArrayList<Item> market;

    public Planet(String name, String description, int distance) {
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.market = new ArrayList<>();
        generateMarket();
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDistance() {
        return distance;
    }

    public ArrayList<Item> getMarket() {
        return market;
    }

    public void addMarketItem(Item item) {
        market.add(item);
    }

        public void displayMarket() {
        System.out.println("Market on " + name + ":");

        for (Item item : market) {
            System.out.println("- " + item.getName() + " | Price: " + item.getBasePrice());
        }
    }

    public abstract void generateMarket();
}
