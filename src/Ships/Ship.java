package Ships;

import java.util.*;
import Items.Item;

public abstract class Ship {
    private ArrayList<Item> cargo;
    private String name;
    private int fuel;
    private int maxFuel;
    private int speed;
    private int cargoCapacity;

    public Ship(String name, int fuel, int maxFuel, int speed, int cargoCapacity, ArrayList<Item> cargo) {
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.speed = speed;
        this.cargoCapacity = cargoCapacity;
        this.cargo = cargo == null ? new ArrayList<>() : cargo;
    }

    public String getName() {
        return name;
    }

    public int getFuel() {
        return fuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public ArrayList<Item> getCargo() {
        return cargo;
    }


    public void refuel(int amount) {
        if(amount > maxFuel) {
            fuel = maxFuel;
        } else {
            fuel += amount;
        }
    }

    public boolean addItem(Item item) {
        if(cargo.size() < cargoCapacity ) {
            cargo.add(item);
            return true;
        } else {
                System.out.println("Not enough capacity!");
            return false;
        }
    }

    public boolean removeItem(Item item) {
        if(cargo.contains(item)) {
            cargo.remove(item);
            return true;
        } else {
                System.out.println("There is nothing to remove.");
            return false;
        }
    }

    public void useFuel(int amount) {
        if (amount > fuel) {
            System.out.println("Not enough fuel!");
        } else {
            fuel -= amount;
        }
    }

    public void displayCargo() {
        System.out.println("\nCargo:");
        if (cargo.isEmpty()) {
            System.out.println("- Empty");
        } else {
            for (Item item : cargo) {
                System.out.println("- " + item.getName() + " x" + item.getQuantity());
            }
        }
    }

    public void displayShipInfo() {
        System.out.println("\nShip Name: " + name);
        System.out.println("Fuel: " + fuel + "/" + maxFuel);
        System.out.println("Speed: " + speed + " AU/s");
        System.out.println("Cargo Capacity: " + cargoCapacity);
    }
}
