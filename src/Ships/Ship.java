package Ships;
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

    }
}
