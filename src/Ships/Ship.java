package Ships;
import Java.util.*; import java.util.*;
// Abstract Ship class (empty shell for now)
public abstract class Ship {
    private ArrayList<Item> cargo;
    private String name;
    private int fuel;
    private int maxFuel;
    private int fuelUseRate;
    private int speed;
    private int cargoCapacity;

    public Ship(String name, int fuel, int maxFuel, int fuelUseRate, int speed, int cargoCapacity) {
        this.name = name;
        this.fuel = fuel;
        this.maxFuel = fuel;
        this.fuelUseRate = fuelUseRate;
        this.speed = speed;
        this.cargoCapacity = cargoCapacity;
    }

    public String name() {
        return name;
    }

    public int fuel() {
        return fuel;
    }

    public int maxFuel() {
        return maxFuel;
    }

    public int fuelUseRate() {
        return fuelUseRate;
    }

    public int speed() {
        return speed;
    }

    public int cargoCapacity() {
        return cargoCapacity;
    }

    public void 
}
