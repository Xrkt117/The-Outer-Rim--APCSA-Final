import Ships.Ship;

public class Player {
    private String name;
    private int credits;
    private int reputation;
    private Ship ship;


    public Player (String name, int credits, int reputation, Ship ship) {
        this.name = name;
        this.credits = credits;
        this.reputation = reputation;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getReputation() {
        return reputation;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean spendCredits(int amount) {
        if (credits >= amount) {
            credits -= amount;
            return true;
        } else {
            System.out.println("Not enough credits.");
            return false;
        }
    }

    public void addReputation(int amount) {
        reputation += amount;
    }
}
