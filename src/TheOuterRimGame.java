import java.util.*;
import Planets.Planet;
import Items.*;
import Ships.*;

public class TheOuterRimGame {
    private Player player;
    private ArrayList<Planet> planets;
    private Planet currentPlanet;
    private Scanner input;

    String boldOn = "\u001B[1m";
    String reset = "\u001B[0m";

    public static void main(String[] args) {
        TheOuterRimGame game = new TheOuterRimGame();
        try {
            game.setupGame();
        } catch (Exception e) {
            System.out.println("An error occurred during game setup: " + e.getMessage());
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

    public void setupGame() throws Exception {
        System.out.println(" ______________.__              _________            __                     __________ .__           \r\n" + //
                        " \\__    ___/|  |__    ____      \\_____  \\   __ __ _/  |_   ____ _______     \\______   \\|__|  _____   \r\n" + //
                        "   |    |   |  |  \\ _/ __ \\      /   |   \\ |  |  \\\\   __\\_/ __ \\\\_  __ \\     |       _/|  | /     \\  \r\n" + //
                        "   |    |   |   Y  \\\\  ___/     /    |    \\|  |  / |  |  \\  ___/ |  | \\/     |    |   \\|  ||  Y Y  \\ \r\n" + //
                        "   |____|   |___|  / \\___  >    \\_______  /|____/  |__|   \\___  >|__|        |____|_  /|__||__|_|  / \r\n" + //
                        "                 \\/      \\/             \\/                    \\/                    \\/           \\/  ");
        
          System.out.println("What is your name traveler?");
          input();
            String name = input.nextLine();
            System.out.println("Welcome, " + name + "!");
            Thread.sleep(500);
            System.out.println("Select your spacecraft- \n [1] Scout \n [2] Balanced \n [3] Cargo");
            input();
            if (input.hasNextInt()) {
                int choice = input.nextInt();
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
                        System.out.println("Invalid choice. Defaulting to Balanced.");
                        player = new Player(name, 1000, 0, new BalancedShip());
                }
            } 
            flush();
            System.out.println("You are currently on Nova-9, the center of the Outer Rim. From here, you can travel to various planets, trade goods, and build your reputation. Your goal is to become the most renowned trader in the Outer Rim. Good luck!");
            gameLoopBridge();

            
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void displayMenu() {
        System.out.println("");
        System.out.println(boldOn + "=== Bridge Console ===" + reset);
        System.out.println(" ");
    }


    public void gameLoopBridge() {
            displayMenu();
            System.out.println("[1] Ship Console");
            System.out.println("[2] Market Console");
            System.out.println("[3] Travel");
            System.out.println("[0] Quit");
            input();
            if (!input.hasNextInt()) { input.nextLine();}
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    shipConsole();
                    flush();
                    break;
                case 2:
                    marketConsole();
                    flush();
                    break;
                case 3:
                    travel();
                    flush();
                    break;
                case 0:
                    System.out.println("Goodbye — safe travels.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
    private void shipConsole() {
        while (true) {
            System.out.println();
            System.out.println("--- Ship Console ---");
            System.out.println("[1] View ship info");
            System.out.println("[2] View cargo");
            System.out.println("[3] Refuel (stub)");
            System.out.println("[0] Return to Bridge");
            input();
            if (!input.hasNextInt()) { input.nextLine(); continue; }
            int c = input.nextInt();
            input.nextLine();
            switch (c) {
                case 1:
                    player.getShip().displayShipInfo();
                    System.out.println("You have no ship.");
                    break;
                case 2:
                    player.getShip().displayCargo();
                    System.out.println("You have no ship.");
                    break;
                case 3:
                    System.out.println("Refuel feature not implemented yet.");
                    break;
                case 0:
                    gameLoopBridge(); // back to Bridge
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    private void marketConsole() {
        while (true) {
            System.out.println();
            System.out.println("--- Market ---");    
            currentPlanet.displayMarket();
            System.out.println("[1] Buy item ()");
            System.out.println("[2] Sell item ()");
            System.out.println("[0] Return to Bridge");
            input();
            if (!input.hasNextInt()) { input.nextLine(); continue; }
            int c = input.nextInt();
            input.nextLine();
            switch (c) {
                case 1:
                    System.out.println("Buy flow not implemented yet.");
                    break;
                case 2:
                    System.out.println("Sell flow not implemented yet.");
                    break;
                case 0:

                    return; // back to Bridge
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void handleChoice() {

    }

    public void travel() {
        
    }

    public void flush() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }
// ______________.__              _________            __                     __________ .__           
// \__    ___/|  |__    ____      \_____  \   __ __ _/  |_   ____ _______     \______   \|__|  _____   
//   |    |   |  |  \ _/ __ \      /   |   \ |  |  \\   __\_/ __ \\_  __ \     |       _/|  | /     \  
//   |    |   |   Y  \\  ___/     /    |    \|  |  / |  |  \  ___/ |  | \/     |    |   \|  ||  Y Y  \ 
//   |____|   |___|  / \___  >    \_______  /|____/  |__|   \___  >|__|        |____|_  /|__||__|_|  / 
//                 \/      \/             \/                    \/                    \/           \/  
    


    
}
