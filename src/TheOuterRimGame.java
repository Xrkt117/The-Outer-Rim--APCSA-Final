import java.util.*;
import Planets.Planet;
import Items.Item;
import Ships.*;

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
            System.out.println("An error occurred during game setup: " + e.getMessage());
            return;
        }
        game.gameLoop();
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

            System.out.println("You are currently on Earth, the center of the Outer Rim. From here, you can travel to various planets, trade goods, and build your reputation. Your goal is to become the most renowned trader in the Outer Rim. Good luck!");
             currentPlanet = new Earth();
             planets.add(currentPlanet);
            

            
    }

    public void gameLoop() {


    }

    public void displayMenu() {

    }

    public void handleChoice() {

    }

    public void travel() {
        
    }
// ______________.__              _________            __                     __________ .__           
// \__    ___/|  |__    ____      \_____  \   __ __ _/  |_   ____ _______     \______   \|__|  _____   
//   |    |   |  |  \ _/ __ \      /   |   \ |  |  \\   __\_/ __ \\_  __ \     |       _/|  | /     \  
//   |    |   |   Y  \\  ___/     /    |    \|  |  / |  |  \  ___/ |  | \/     |    |   \|  ||  Y Y  \ 
//   |____|   |___|  / \___  >    \_______  /|____/  |__|   \___  >|__|        |____|_  /|__||__|_|  / 
//                 \/      \/             \/                    \/                    \/           \/  
    


    
}
