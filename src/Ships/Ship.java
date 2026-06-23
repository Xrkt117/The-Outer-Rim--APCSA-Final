package Ships;

import java.util.*;
import Items.Item;
import ConsoleUI.*;

public abstract class Ship {
    // Cargo stores item stacks, while the game checks total item quantity before buying.
    private ArrayList<Item> cargo;
    private String name;
    private int fuel;
    private int maxFuel;
    private int speed;
    private int cargoCapacity;
    private int fuelUpgradeLevel;
    private int speedUpgradeLevel;
    private int cargoUpgradeLevel;

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

    public int getFuelUpgradeLevel() {
        return fuelUpgradeLevel;
    }

    public int getSpeedUpgradeLevel() {
        return speedUpgradeLevel;
    }

    public int getCargoUpgradeLevel() {
        return cargoUpgradeLevel;
    }

    public ArrayList<Item> getCargo() {
        return cargo;
    }


    public void refuel(int amount) {
        // Refueling never goes above the current max tank size.
        if(fuel + amount > maxFuel) {
            fuel = maxFuel;
        } else {
            fuel += amount;
        }
    }

    public boolean upgradeFuel() {
        // Upgrade levels are capped so the starter ship stays balanced.
        if (fuelUpgradeLevel < 2) {
            fuelUpgradeLevel++;
            maxFuel += 20;
            fuel = maxFuel;
            return true;
        } else {
            return false;
        }
    }

    public boolean upgradeSpeed() {
        if (speedUpgradeLevel < 2) {
            speedUpgradeLevel++;
            speed++;
            return true;
        } else {
            return false;
        }
    }

    public boolean upgradeCargo() {
        if (cargoUpgradeLevel < 2) {
            cargoUpgradeLevel++;
            cargoCapacity += 10;
            return true;
        } else {
            return false;
        }
    }

    public boolean addItem(Item item) {
        // Market code checks quantity capacity first; this is a final stack-count guard.
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
        // Travel checks fuel before calling this, so this should only subtract valid trips.
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
        sound.playSound("Sounds/printSound.wav");
        System.out.println("\nShip Name: " + name);
        System.out.println("Fuel: " + fuel + "/" + maxFuel + " kL");
        System.out.println("Speed: " + speed + " AU/s");
        System.out.println("Cargo Capacity: " + cargoCapacity);
        System.out.println("Fuel Upgrade Level: " + fuelUpgradeLevel);
        System.out.println("Speed Upgrade Level: " + speedUpgradeLevel);
        System.out.println("Cargo Upgrade Level: " + cargoUpgradeLevel);
    }
}
