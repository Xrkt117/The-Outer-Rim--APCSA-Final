package Ships;
import java.util.*;
import Items.Item;
// Abstract Ship class (empty shell for now)
public abstract class Ship {
    private ArrayList<Item> cargo;
    private String name;
    private int fuel;
    private int maxFuel;
    private int fuelUseRate;
    private int speed;
    private int cargoCapacity;

    public Ship(String name, int fuel, int maxFuel, int fuelUseRate, int speed, int cargoCapacity, ArrayList<Item> cargo) {
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.fuelUseRate = fuelUseRate;
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

    public int getFuelUseRate() {
        return fuelUseRate;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
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
                System.out.println(item.getName() + "has been added to cargo!");
            return true;
        } else {
                System.out.println("Not enough capacity!");
            return false;
        }
    }

    public boolean removeItem(Item item) {
        if(cargo.contains(item)) {
            cargo.remove(item);
                System.out.println(item.getName() + "has been removed from cargo!");
            return true;
        } else {
                System.out.println("There is nothing to remove.");
            return false;
        }
    }

    public void useFuel(int amount) {
        
    }

    public void displayCargo() {
        System.out.println("Cargo:");
        if (cargo.isEmpty()) {
            System.out.println("- Empty");
        } else {
            for (Item item : cargo) {
                System.out.println("- " + item.getName());
            }
        }
    }

    public void displayShipInfo() {
        System.out.println("Ship Name: " + name);
        System.out.println("Fuel: " + fuel + "/" + maxFuel);
        System.out.println("Speed: " + speed);
        System.out.println("Cargo Capacity: " + cargoCapacity);
    }


}
