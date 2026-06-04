import java.util.*;
import Planets.Planet;
import Items.Item;

public class TheOuterRimGame {
    private Player player;
    private ArrayList<Planet> planets;
    private Planet currentPlanet;
    private Scanner input;

    public static void main(String[] args) {
        TheOuterRimGame game = new TheOuterRimGame();
        game.setupGame();
        game.gameLoop();
    }

    public TheOuterRimGame() {
        input = new Scanner(System.in);
        planets = new ArrayList<>();
    }

    public void setupGame() {
        System.out.println(" ______________.__              _________            __                     __________ .__           \r\n" + //
                        " \\__    ___/|  |__    ____      \\_____  \\   __ __ _/  |_   ____ _______     \\______   \\|__|  _____   \r\n" + //
                        "   |    |   |  |  \\ _/ __ \\      /   |   \\ |  |  \\\\   __\\_/ __ \\\\_  __ \\     |       _/|  | /     \\  \r\n" + //
                        "   |    |   |   Y  \\\\  ___/     /    |    \\|  |  / |  |  \\  ___/ |  | \\/     |    |   \\|  ||  Y Y  \\ \r\n" + //
                        "   |____|   |___|  / \\___  >    \\_______  /|____/  |__|   \\___  >|__|        |____|_  /|__||__|_|  / \r\n" + //
                        "                 \\/      \\/             \\/                    \\/                    \\/           \\/  ");
    }

    public void gameLoop() {

    }

    public void displayMenu() {

    }

    public void handleChoice() {

    }
// ______________.__              _________            __                     __________ .__           
// \__    ___/|  |__    ____      \_____  \   __ __ _/  |_   ____ _______     \______   \|__|  _____   
//   |    |   |  |  \ _/ __ \      /   |   \ |  |  \\   __\_/ __ \\_  __ \     |       _/|  | /     \  
//   |    |   |   Y  \\  ___/     /    |    \|  |  / |  |  \  ___/ |  | \/     |    |   \|  ||  Y Y  \ 
//   |____|   |___|  / \___  >    \_______  /|____/  |__|   \___  >|__|        |____|_  /|__||__|_|  / 
//                 \/      \/             \/                    \/                    \/           \/  
    


    
}
