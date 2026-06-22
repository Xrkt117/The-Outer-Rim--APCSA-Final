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

    public void earnCredits(int amount) {
        credits += amount;
    }

    public void addReputation(int amount) {
        reputation += amount;
    }

    public String getReputationTitle() {
        if (reputation >= 1000) {
            return "Renowned Trader of the Outer Rim";
        } else if (reputation >= 750) {
            return "Trade Magnate";
        } else if (reputation >= 500) {
            return "Sector Merchant";
        } else if (reputation >= 250) {
            return "Established Trader";
        } else if (reputation >= 100) {
            return "Local Merchant";
        } else {
            return "Stranger";
        }
    }
}
