import java.util.*;
import Planets.Planet;
import Items.*;
import Ships.*;
import ConsoleUI.*;

public class TheOuterRimGame {
    private Player player;
    private ArrayList<Planet> planets;
    private Planet currentPlanet;
    private Scanner input;

    public static void main(String[] args) {
        TheOuterRimGame game = new TheOuterRimGame();
        try {
            game.setupGame();
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

    public void input() {
        System.out.print("> ");
    }

    public void flush() {
        ui.flush();
        title();
    }

    public void setupGame() throws Exception {
        flush();
          ui.println("What is your name traveler?");
          input();
            String name = input.nextLine();
            flush();
            ui.println("Welcome, " + name + "!");
            Thread.sleep(500);
            ui.println(" ");
            ui.println("Select your spacecraft- \n [1] Scout \n [2] Balanced \n [3] Cargo");
            input();
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        player = new Player(name, 1000, 0, new ScoutShip());
                        break;
                    case 2:
                        player = new Player(name, 1000, 0, new BalancedShip());
                        break;
                    case 3:
                        player = new Player(name, 1000, 0, new CargoShip());
                        break;
                    default:
                        ui.println("Invalid choice. Defaulting to Balanced.");
                        player = new Player(name, 1000, 0, new BalancedShip());
                }

                ui.println("\nYou have selected the " + player.getShip().getName() + ". It has a cargo capacity of " + player.getShip().getCargoCapacity() + " units and a fuel capacity of " + player.getShip().getMaxFuel() + " units.");
                ui.pressAnyKey();

            //planet declaration and initialization
            currentPlanet = new Planets.StarterPlanet("Nova-9");
            planets.add(currentPlanet);
            Planet miningPlanet = new Planets.MiningPlanet("Caelum", 5);
            planets.add(miningPlanet);
            Planet militaryPlanet = new Planets.MilitaryPlanet("Fortress-7", 10);
            planets.add(militaryPlanet);
            Planet tradingPlanet = new Planets.TradePlanet("Aurora", 20);
            planets.add(tradingPlanet);

            flush();
            ui.println("You are currently on " + currentPlanet.getName() + ", the center of the Outer Rim. From here, you can travel to various planets, trade goods, and build your reputation. Your goal is to become the most renowned trader in the Outer Rim. Good luck!");
            ui.pressAnyKey();
            gameLoopBridge();      
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //seperate bridge console display if I want to add more features to the selectable menu through progression, reputation, or other factors
    public void displayMenu() {
        flush();
        ui.println("=== Bridge Console ===");
        ui.println("\nLocation: " +  currentPlanet.getName() + "\n");
    }

    public void gameLoopBridge() {
        displayMenu();
        ui.println("[1] Ship Console");
        ui.println("[2] Market");
        ui.println("[3] Travel");
        ui.println("[0] Quit");
        input();
        int choice = input.nextInt();
        input.nextLine(); //prevents crashing if the user inputs a non-integer value
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
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //displays vital ship information, cargo, and other features that may be added in the future such as refueling, repairing, or upgrading the ship.
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

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    private void marketConsole() {
            flush();
            ui.println("--- " + currentPlanet.getName() + " Market ---");

            ui.println(" ");
            currentPlanet.displayMarket();
            ui.println(" ");

            ui.println("[1] Buy item ()");
            ui.println("[2] Sell item ()");
            ui.println("[0] Return to Bridge");
            input();
            int c = input.nextInt();
            input.nextLine();
            switch (c) {
                case 1:
                    ui.println("\nBuy flow not implemented yet.");
                    break;
                case 2:
                    ui.println("\nSell flow not implemented yet.");
                    break;
                case 0:
                    gameLoopBridge(); // back to Bridge
                default:
                    ui.println("Invalid choice.");
            }
        }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    private void travelConsole() {
        flush();

        ui.println("\n------------------ Travel ------------------");

        ui.print(" .              +   .                .   . .     .  .\r\n" + //
                        "                   .                    .       .     *\r\n" + //
                        "  .       *                        . . . .  .   .  + .\r\n" + //
                        "            \"You Are Here\"            .   .  +  . . .\r\n" + //
                        ".                 |             .  .   .    .    . .\r\n" + //
                        "                  |           .     .     . +.    +  .\r\n" + //
                        "                 \\|/            .       .   . .\r\n" + //
                        "        . .       V          .    * . . .  .  +   .\r\n" + //
                        "           +      .           .   .      +\r\n" + //
                        "                " + currentPlanet.getName() + "           .       . +  .+. .\r\n" + //
                        "  .                      .     . + .  . .     .      .\r\n" + //
                        "           .      .    .     . .   . . .        ! /\r\n" + //
                        "      *             .    . .  +    .  .       - O -\r\n" + //
                        "          .     .    .  +   . .  *  .       . / |\r\n" + //
                        "               . + .  .  .  .. +  .\r\n" + //
                        ".      .  .  .  *   .  *  . +..  .            *\r\n" + //
                        " .      .   . .   .   .   . .  +   .    .            +");
        ui.println("\n--------------------------------------------");
        ui.println("\n\nFrom here, you can travel to the following planets:");

        for (Planet planet : planets) {
            if (planet != currentPlanet) {
                ui.println("- " + planet.getName() + " ");
            }
        }
        ui.println(" ");

        for (int i = 0; i < planets.size(); i++) {
            Planet planet = planets.get(i);
            ui.println("[" + (i + 1) + "] Travel to " + planet.getName() + " | Distance: " + Math.abs(currentPlanet.getDistance() - planet.getDistance()) + " AU");
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
                final int travelTime = Math.abs(currentPlanet.getDistance() - selectedPlanet.getDistance()); //time calculation based on distance
                currentPlanet = selectedPlanet;
                player.getShip().useFuel(travelTime * 2); //fuel usage is calculated based on travel time, 2 fuel per AU
                flush();
                ui.println("   .       . \r\n" + //
                                        " +  :      .\r\n" + //
                                        "           :       _\r\n" + //
                                        "       .   !   '  (_)\r\n" + //
                                        "          ,|.' \r\n" + //
                                        "-  -- ---(-O-`--- --  -\r\n" + //
                                        "         ,`|'`.\r\n" + //
                                        "       ,   !    .\r\n" + //
                                        "           :       :  \" \r\n" + //
                                        "           .     --+--\r\n" + //
                                        " .:        .       !");
                ui.println("Plotting course to next destination...");
                try {
                    for (int i = 0; i < travelTime; i++) {
                        System.out.print(".");
                        System.out.flush();
                        Thread.sleep( 1000 / player.getShip().getSpeed()); //travel time is reduced based on ship speed
                    }
                    ui.println(" ");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                
                flush();
                ui.println("Arrived at " + currentPlanet.getName() + "!");
                ui.pressAnyKey();
                gameLoopBridge();
            }       
        }
    }

    public void title() {
        ui.println(" ______________.__              _________            __                     __________ .__           \r\n" + //
                    " \\__    ___/|  |__    ____      \\_____  \\   __ __ _/  |_   ____ _______     \\______   \\|__|  _____   \r\n" + //
                    "   |    |   |  |  \\ _/ __ \\      /   |   \\ |  |  \\\\   __\\_/ __ \\\\_  __ \\     |       _/|  | /     \\  \r\n" + //
                    "   |    |   |   Y  \\\\  ___/     /    |    \\|  |  / |  |  \\  ___/ |  | \\/     |    |   \\|  ||  Y Y  \\ \r\n" + //
                    "   |____|   |___|  / \\___  >    \\_______  /|____/  |__|   \\___  >|__|        |____|_  /|__||__|_|  / \r\n" + //
                    "                 \\/      \\/             \\/                    \\/                    \\/           \\/  ");
    }     
}
