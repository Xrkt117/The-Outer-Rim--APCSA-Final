import java.util.ArrayList;
import java.util.Scanner;
import Planets.Planet;
import Ships.*;
import ConsoleUI.*;

public class TheOuterRimGame {
    private Player player;
    private ArrayList<Planet> planets;
    private Planet currentPlanet;
    private Scanner input;
    private static final int startingCredits = 1000;
    private static final int fuelPerAU = 2;

    public static void main(String[] args) {
        TheOuterRimGame game = new TheOuterRimGame();
        try {
            game.preGameMenu();
        } catch (Exception e) {
            ui.println("An error occurred during game setup: " + e.getMessage());
            return;
        }
        game.gameLoopBridge();
    }

    public TheOuterRimGame() {
        input = new Scanner(System.in);
        planets = new ArrayList<>();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void preGameMenu() {
        flush();
        ui.println("WELCOME TO THE OUTER RIM");
        ui.println("The ultimate space trading adventure- buy low, sell high, and upgrade your space vessel.");
        ui.println("");
        art.main();
        ui.println("");
        ui.println("[1] Start Adventure");
        ui.println("[2] Tips & Tricks");
        ui.println("[3] Author's Note / Credits");
        ui.println("[0] Quit");
        ui.println("");
        input();

        int choice;
        try {
            choice = input.nextInt();
        } catch (Exception e) {
            input.nextLine(); // consume bad input
            ui.println("Please enter a number from the menu.");
            ui.pressAnyKey();
            return;
        }
        input.nextLine();

        switch (choice) {
            case 1:
                setupGame();
                return;
            case 2:
                flush();
                ui.println("--- Tips & Tricks ---");
                ui.println("- Make sure you have enough fuel before long trips. ;)");
                ui.println("- Keep ~10% of credits as an emergency buffer.");
                ui.println("- Prioritize cargo upgrades early for bigger per-trip profits.");
                ui.println("");
                ui.pressAnyKey();
                preGameMenu();
                break;
            case 3:
                flush();
                ui.println("Author: Matvii Tsariuk");
                ui.println("Course: APCSA — Final Project");
                ui.println("Version: 1.0");
                ui.println("");
                ui.println(
                        "Author's note: This project demonstrates OOP concepts: inheritance, polymorphism, and encapsulation.");
                ui.println("Thanks for playing — feedback welcomed!");
                ui.pressAnyKey();
                preGameMenu();
                break;
            case 0:
                ui.println("Goodbye — safe travels.");
                System.exit(0);
                return;
            default:
                ui.println("Invalid choice.");
                ui.pressAnyKey();
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void setupGame() {
        flush();
        String name = promptPlayerName();
        flush();
        ui.println("Welcome, " + name + "!");
        delay(1000);

        player = new Player(name, startingCredits, 0, new StarterShip());

        // introduces the start ship and its features
        introduceStarterShip();

        // planet declaration and initialization
        initPlanets();

        flush();

        // starting narrative and instructions
        ui.println("You are currently on " + currentPlanet.getName()
                + ", the center of the Outer Rim. From here, you can travel to various planets, trade goods, and build your reputation. Your goal is to become the most renowned trader in the Outer Rim. Good luck! :)");
        art.starterPlanet();
        ui.pressAnyKey();

        gameLoopBridge();
    }

    private String promptPlayerName() {
        ui.println("What is your name traveler?");
        input();
        return input.nextLine();
    }

    private void introduceStarterShip() {
        ui.println("\nMeet your ship:");
        delay(1000);
        player.getShip().displayShipInfo();
        delay(1500);
        ui.println("\nThis ship is modular, you can install Ship Parts to improve speed, cargo, or fuel.");
        ui.pressAnyKey();
    }

    private void initPlanets() {
        planets.clear();
        currentPlanet = new Planets.StarterPlanet("Nova-9");
        planets.add(currentPlanet);
        Planet miningPlanet = new Planets.MiningPlanet("Caelum", 5);
        planets.add(miningPlanet);
        Planet militaryPlanet = new Planets.MilitaryPlanet("Fortress-7", 10);
        planets.add(militaryPlanet);
        Planet tradingPlanet = new Planets.TradePlanet("Aurora", 20);
        planets.add(tradingPlanet);
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // seperate bridge console display if I want to add more features to the
    // selectable menu through progression, reputation, or other factors
    public void displayMenu() {
        flush();
        ui.println("=== Bridge Console ===");
        ui.println("\nLocation: " + currentPlanet.getName() + "\n");
    }

    public void gameLoopBridge() {
        displayMenu();
        ui.println("[1] Ship Console");
        ui.println("[2] Market");
        ui.println("[3] Travel");
        ui.println("[0] Quit");
        input();
        int choice = input.nextInt();
        input.nextLine(); // prevents crashing if the user inputs a non-integer value
        switch (choice) {
            case 1:
                shipConsole();
                break;
            case 2:
                marketConsole();
                break;
            case 3:
                travelConsole();
                break;
            case 0:
                ui.println("Goodbye — safe travels.");
                System.exit(0);
            default:
                ui.println("Invalid choice.");
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // displays vital ship information, cargo, and other features that may be added
    // in the future such as refueling, repairing, or upgrading the ship.
    private void shipConsole() {
        flush();
        ui.println("--- Ship Console ---");
        ui.println("[1] View ship info");
        ui.println("[2] View cargo");
        ui.println("[3] Refuel (stub)");
        ui.println("[0] Return to Bridge");
        input();
        int c = input.nextInt();
        input.nextLine();
        switch (c) {
            case 1:
                player.getShip().displayShipInfo();
                ui.pressAnyKey();
                shipConsole();
                break;
            case 2:
                player.getShip().displayCargo();
                ui.pressAnyKey();
                shipConsole();
                break;
            case 3:
                ui.println("\nRefuel feature not implemented yet.");
                ui.pressAnyKey();
                shipConsole();
                break;
            case 0:
                gameLoopBridge(); // back to Bridge
            default:
                ui.println("Invalid choice.");
                ui.pressAnyKey();
                shipConsole();
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void marketConsole() {
        flush();
        ui.println("--- " + currentPlanet.getName() + " Market ---\n");
        ui.println("Credits: " + player.getCredits() + "\n");

        ArrayList<Items.Item> market = currentPlanet.getMarket();
        for (int i = 0; i < market.size(); i++) {
            Items.Item it = market.get(i);
            ui.println(
                    "[" + (i + 1) + "] " + it.getName() + " x" + it.getQuantity() + " | Price: " + it.getBasePrice());
        }

        ui.println("\n[1] Buy item");
        ui.println("[2] Sell item");
        ui.println("[0] Return to Bridge");
        input();
        int c = input.nextInt();
        input.nextLine();
        switch (c) {
            case 1: {
                ui.println("Enter item number to buy: ");
                input();
                int itemNumber = input.nextInt();
                input.nextLine();

                if (itemNumber <= 0 || itemNumber > market.size()) {
                    ui.println("Invalid item number.");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                Items.Item selectedItem = market.get(itemNumber - 1);
                ui.println("Enter quantity: ");
                input();
                int quantity = input.nextInt();
                input.nextLine();

                if (quantity <= 0) {
                    ui.println("Invalid quantity.");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                if (selectedItem.getQuantity() < quantity) {
                    ui.println("Not enough stock to buy " + quantity + " " + selectedItem.getName() + ".");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                int freeSlots = player.getShip().getCargoCapacity() - player.getShip().getCargo().size();
                if (freeSlots < quantity) {
                    ui.println("Not enough cargo space to buy " + quantity + " " + selectedItem.getName() + ".");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                int cost = selectedItem.getBasePrice() * quantity;
                if (player.getCredits() < cost) {
                    ui.println("Not enough credits to buy " + selectedItem.getName() + ".");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                // All checks passed, proceed with purchase
                Items.Item toAdd = new Items.Item(selectedItem.getName(), selectedItem.getBasePrice(), quantity,
                        selectedItem.getDescription()) {
                };
                boolean ok = player.getShip().addItem(toAdd);
                if (!ok) {
                    ui.println("Not enough cargo space to buy " + quantity + " " + selectedItem.getName() + ".");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                // finalize: deduct credits and reduce market stock
                player.spendCredits(cost);
                selectedItem.setQuantity(selectedItem.getQuantity() - quantity);
                ui.println("You bought " + quantity + " " + selectedItem.getName() + " for " + cost + " credits.");
                ui.pressAnyKey();
                marketConsole();
                break;
            }
            case 2:
                ArrayList<Items.Item> cargo = player.getShip().getCargo();
                if (cargo.isEmpty()) {
                    ui.println("You have no cargo to sell.");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                ui.println("\n--- Your Cargo ---");
                for (int i = 0; i < cargo.size(); i++) {
                    Items.Item it = cargo.get(i);
                    ui.println("[" + (i + 1) + "] " + it.getName() + " x" + it.getQuantity() + " | Base: "
                            + it.getBasePrice());
                }

                ui.println("\nEnter cargo item number to sell:");
                input();
                int cargoNumber = input.nextInt();
                input.nextLine();

                if (cargoNumber <= 0 || cargoNumber > cargo.size()) {
                    ui.println("Invalid cargo selection.");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                Items.Item cargoItem = cargo.get(cargoNumber - 1);
                ui.println("Enter quantity to sell:");
                input();
                int sellQty = input.nextInt();
                input.nextLine();

                if (sellQty <= 0 || sellQty > cargoItem.getQuantity()) {
                    ui.println("Invalid quantity.");
                    ui.pressAnyKey();
                    marketConsole();
                    return;
                }

                // compute sell value (80% of base price)
                int sellValue = (int) Math.round(cargoItem.getBasePrice() * sellQty * 0.8);

                // remove from cargo
                cargoItem.setQuantity(cargoItem.getQuantity() - sellQty);
                if (cargoItem.getQuantity() <= 0) {
                    cargo.remove(cargoNumber - 1);
                }

                // credit player
                player.earnCredits(sellValue);

                // add as a new stack to market (do NOT merge into existing stock)
                market.add(new Items.Item(cargoItem.getName(), cargoItem.getBasePrice(), sellQty,
                        cargoItem.getDescription()) {
                });

                ui.println("Sold " + sellQty + " " + cargoItem.getName() + " for " + sellValue + " credits.");
                ui.pressAnyKey();
                marketConsole();
                break;

            case 0:
                gameLoopBridge(); // back to Bridge
                break;
            default:
                ui.println("Invalid choice.");
                marketConsole();
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void travelConsole() {
        flush();

        ui.println("\n--------------------- Travel ---------------------");

        ui.print(" .              +   .                .   . .     .  . *\r\n" + //
                "  .       *                        . . . .  .   .  + .\r\n" + //
                "            \"You Are Here\"            .   .  +  . . .\r\n" + //
                "                  |           .     .     . +.    +  .\r\n" + //
                "                 \\|/             .       .   . .\r\n" + //
                "        . .       V          .    * . . .  .  +   .\r\n" + //
                "           +      .           .   .      +\r\n" + //
                "                " + currentPlanet.getName() + "   .       . +  .+. .\r\n" + //
                "  .                      .     . + .  . .     .      .\r\n" + //
                "           .      .    .     . .   . . .        ! /\r\n" + //
                "      *             .    . .  +    .  .       - O -\r\n" + //
                "          .     .    .  +   . .  *  .       . / |\r\n" + //
                ".      .  .  .  *   .  *  . +..  .            *\r");
        ui.println("\n--------------------------------------------------");
        ui.println("\nFrom here, you can travel to the following planets:");

        for (Planet planet : planets) {
            if (planet != currentPlanet) {
                ui.println("- " + planet.getName() + " ");
            }
        }
        ui.println(" ");

        for (int i = 0; i < planets.size(); i++) {
            Planet planet = planets.get(i);
            ui.println("[" + (i + 1) + "] Travel to " + planet.getName() + " | Distance: "
                    + Math.abs(currentPlanet.getDistance() - planet.getDistance()) + " AU");
        }
        ui.println("[0] Return to Bridge");
        input();
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 0) {
            gameLoopBridge(); // back to Bridge
        } else if (choice > 0 && choice <= planets.size()) {
            Planet selectedPlanet = planets.get(choice - 1);
            if (selectedPlanet == currentPlanet) {
                ui.println("You are already on " + selectedPlanet.getName() + ".");
                ui.pressAnyKey();
                travelConsole();
            } else {
                final int travelTime = Math.abs(currentPlanet.getDistance() - selectedPlanet.getDistance()); // time calculation based on distance
                currentPlanet = selectedPlanet;
                player.getShip().useFuel(travelTime * fuelPerAU); // fuel usage is calculated based on travel time
                flush();
                art.travelling();
                ui.println("Plotting course to next destination...");
                    for (int i = 0; i < travelTime; i++) {
                        System.out.print(".");
                        System.out.flush();
                        delay(1000 / player.getShip().getSpeed()); // travel time is reduced based on ship speed
                    }
                    ui.println(" ");

                flush();
                art.arrived();
                ui.println("Arrived at " + currentPlanet.getName() + "!");
                ui.pressAnyKey();
                travelConsole();
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void title() {
        ui.println(
                " ______________.__              _________            __                     __________ .__           \r\n"
                        + //
                        " \\__    ___/|  |__    ____      \\_____  \\   __ __ _/  |_   ____ _______     \\______   \\|__|  _____   \r\n"
                        + //
                        "   |    |   |  |  \\ _/ __ \\      /   |   \\ |  |  \\\\   __\\_/ __ \\\\_  __ \\     |       _/|  | /     \\  \r\n"
                        + //
                        "   |    |   |   Y  \\\\  ___/     /    |    \\|  |  / |  |  \\  ___/ |  | \\/     |    |   \\|  ||  Y Y  \\ \r\n"
                        + //
                        "   |____|   |___|  / \\___  >    \\_______  /|____/  |__|   \\___  >|__|        |____|_  /|__||__|_|  / \r\n"
                        + //
                        "                 \\/      \\/             \\/                    \\/                    \\/           \\/  ");
    }

    public void input() {
        System.out.print("> ");
    }

    public void flush() {
        ui.flush();
        title();
    }

    public void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}
