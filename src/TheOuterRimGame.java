import java.util.ArrayList;
import java.util.Scanner;
import Planets.Planet;
import Ships.*;
import ConsoleUI.*;
import News.*;

public class TheOuterRimGame {
    private Player player;
    private ArrayList<Planet> planets;
    private Planet currentPlanet;
    private ReputationMilestones milestones;
    private GalacticNews currentNews;
    private Scanner input;
    private static final int startingCredits = 1000;
    private static final int fuelPerAU = 1;
    private static final int fuelPerCanister = 5;
    private static final int shipPartsPerUpgrade = 1;

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
        input = ui.getScanner();
        planets = new ArrayList<>();
        milestones = new ReputationMilestones();
        currentNews = new NoMajorNews();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // initial menu with options for starting the game, viewing tips, and credits
    public void preGameMenu() {
        //background music
        sound.playLoop("Sounds/soundtrack.wav");

        //ensures theres no recursion and potential stack overflow from repeated invalid inputs in the menu
        while (true) {
            flush();
            ui.println("WELCOME TO THE OUTER RIM");
            ui.println("The ultimate space trading adventure- explore, upgrade, and build your name.");
            ui.println("");
            art.main();
            ui.println("");
            ui.println("[1] Start Adventure");
            ui.println("[2] Tips & Tricks");
            ui.println("[3] Author's Note / Credits");
            ui.println("[0] Quit");
            ui.println("");

            // input validation loop
            int choice = readIntLoop();

            // switch statement to handle menu choices
            switch (choice) {
                case 1:
                    setupGame();
                    return;
                case 2:
                    flush();
                    ui.println("--- Tips & Tricks ---");
                    ui.println("- A Fuel Canister (5 fuel) can be bought and stored in cargo and used to refuel at any port.");
                    ui.println("- Ship Parts are required (plus credits) to install ship upgrades, keep at least one per upgrade.");
                    ui.println("- Upgrade cargo capacity early to increase profit per trip; larger hauls beat many small runs.");
                    ui.println("- Use Market News headlines to spot high-demand markets before you commit to a route.");
                    ui.println("- Keep ~10% of your credits as an emergency buffer (refuel, repairs, or last-minute upgrades).");
                    ui.println("- Check destination requirements: some outposts and Outer Worlds need fuel-system upgrades.");
                    ui.println("");
                    ui.pressAnyKey();
                    break;
                case 3:
                    flush();
                    ui.println("Author: Matvii Tsariuk");
                    ui.println("Course: APCSA — Final Project");
                    ui.println("Version: 2.0");
                    ui.println("");
                    ui.println(
                            "Author's note: This project demonstrates OOP concepts: inheritance, polymorphism, and encapsulation.");
                    ui.println("Thanks for playing — feedback welcomed!");
                    ui.pressAnyKey();
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
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void setupGame() {
        flush();
        String name = promptPlayerName(); //prompt for player name with input validation
        flush();
        ui.println("Welcome aboard, " + name + ".");
        delay(1000);
        ui.printSoundln("\nThe Outer Rim is a harsh place. Beyond the Core Worlds, distant colonies depend on independent pilots to move goods between the stars.");
        ui.pressAnyKey();
        flush();
        ui.println("You have a small cargo ship, a handful of credits, and no reputation to your name");
        delay(2000);
        ui.printSoundln("\nNo one knows who you are.");
        delay(1000);
        ui.printSoundln("\nYet.");
        delay(1000);
        ui.printSoundln("\nThe hangar doors slowly open.");
        ui.pressAnyKey();

        player = new Player(name, startingCredits, 0, new StarterShip());

        // introduces the start ship and its features
        introduceStarterShip();

        // planet declaration and initialization
        initPlanets();

        flush();

        ui.println("You are docked at " + currentPlanet.getName()
                + ". From here, you can trade near Earth, upgrade your ship, unlock distant markets, and build the reputation needed to become the most renowned trader in the Outer Rim.");
        art.starterPlanet();
        ui.pressAnyKey();
    }

    private String promptPlayerName() {
        ui.println("What is your name, traveler?");
        input();
        return input.nextLine();
    }

    private void introduceStarterShip() {
         flush();
        ui.println("A soft hum fills the hangar. Through the mist of docking lights, your ship waits-steady and familiar.");
        delay(2000);
        sound.sfxReveal();
        art.starterShip();
        delay(700);
        player.getShip().displayShipInfo();
        delay(700);
        ui.printSoundln("\nThis vessel can be upgraded with ship parts to improve speed, cargo capacity, or fuel efficiency.");
        ui.pressAnyKey();
    }

    private void initPlanets() {
        planets.clear();
        currentPlanet = new Planets.StarterPlanet("Earth");
        planets.add(currentPlanet);
        milestones.startAt(currentPlanet.getName());
        Planet planetOne = new Planets.MiningPlanet("Luna Mining Colony", 5);
        planets.add(planetOne);
        Planet planetTwo = new Planets.MiningPlanet("Caelum Mining Colony", 10);
        planets.add(planetTwo);
        Planet tradeOutpost = new Planets.TradePlanet("Vesta Trade Outpost", 25);
        planets.add(tradeOutpost);
        Planet outerWorld = new Planets.MilitaryPlanet("Helios Outer World", 45);
        planets.add(outerWorld);
        Planet farthestPlanet = new Planets.MilitaryPlanet("Erebus Frontier", 60);
        planets.add(farthestPlanet);
        generateGalacticNews();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // seperate bridge console display if I want to add more features to the
    // selectable menu through progression, reputation, or other factors
    public void displayMenu() {
        flush();
        ui.println("\n========================= Bridge Console =========================");
        art.bridge();
        ui.println("\nLocation: " + currentPlanet.getName());
        ui.println("Credits: " + player.getCredits());
        ui.println("Reputation: " + player.getReputation() + " - " + player.getReputationTitle() + "\n");
        ui.println("GALACTIC NEWS");
        ui.println(currentNews.getHeadline() + "\n");
    }

    public void gameLoopBridge() {
		// Persistent bridge loop: submenus return to this loop instead of terminating the process.
		while (true) {
			displayMenu();
			ui.println("[1] Ship Console");
			ui.println("[2] Market");
			ui.println("[3] Travel");
			ui.println("[4] Galactic News");
			ui.println("[0] Quit");
			int choice = readIntLoop();
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
			case 4:
				newsConsole();
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
	}

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // displays vital ship information, cargo, features that may be added
    // in the future such as refueling, repairing, or upgrading the ship.
    private void shipConsole() {
		// iterative ship console to avoid recursion and stack growth
		while (true) {
			flush();
			ui.println("--- Ship Console ---");
			ui.println("[1] View ship info");
			ui.println("[2] View cargo");
			ui.println("[3] Refuel");
			ui.println("[4] Upgrade ship");
			ui.println("[0] Return to Bridge");
			int c = readIntLoop();
			switch (c) {
			case 1:
				player.getShip().displayShipInfo();
				ui.pressAnyKey();
				break;
			case 2:
				player.getShip().displayCargo();
				ui.pressAnyKey();
				break;
			case 3:
				refuelShip();
				ui.pressAnyKey();
				break;
			case 4:
				upgradeConsole();
				break;
			case 0:
				return; // return to bridge loop
			default:
				ui.println("Invalid choice.");
				ui.pressAnyKey();
			}
		}
	}

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void marketConsole() {
        while (true) {
            displayMarket();

            ui.println("\n[1] Buy item");
            ui.println("[2] Sell item");
            ui.println("[0] Return to Bridge");
            int choice = readIntLoop();

            switch (choice) {
                case 1:
                    buyItem();
                    break;
                case 2:
                    sellItem();
                    break;
                case 0:
                    return;
                default:
                    ui.println("Invalid choice.");
                    ui.pressAnyKey();
            }
        }
    }

    private void displayMarket() {
        flush();
        ui.println("--- " + currentPlanet.getName() + " Market ---\n");
        ui.println("Credits: " + player.getCredits());
        ui.println("Cargo Space: " + getCargoUsed() + "/" + player.getShip().getCargoCapacity() + "\n");
        ui.println("Market News: " + currentNews.getHeadline() + "\n");

        ArrayList<Items.Item> market = currentPlanet.getMarket();
        for (int i = 0; i < market.size(); i++) {
            Items.Item it = market.get(i);
            ui.println("[" + (i + 1) + "] " + it.getName() + " x" + it.getQuantity()
                    + " | Buy Price: " + getBuyPrice(it));
        }
    }

    private void buyItem() {
        ArrayList<Items.Item> market = currentPlanet.getMarket();
        displayMarket();
        ui.println("\nEnter item number to buy:");
        int itemNumber = readIntLoop();

        if (itemNumber <= 0 || itemNumber > market.size()) {
            ui.println("Invalid item number.");
            ui.pressAnyKey();
            return;
        }

        Items.Item selectedItem = market.get(itemNumber - 1);
        ui.println("Enter quantity:");
        int quantity = readIntLoop();

        if (quantity <= 0) {
            ui.println("Invalid quantity.");
            ui.pressAnyKey();
            return;
        }

        if (selectedItem.getQuantity() < quantity) {
            ui.println("Not enough stock to buy " + quantity + " " + selectedItem.getName() + ".");
            ui.pressAnyKey();
            return;
        }

        int freeSlots = player.getShip().getCargoCapacity() - getCargoUsed();
        if (freeSlots < quantity) {
            ui.println("Not enough cargo space to buy " + quantity + " " + selectedItem.getName() + ".");
            ui.pressAnyKey();
            return;
        }

        int cost = getBuyPrice(selectedItem) * quantity;
        if (player.getCredits() < cost) {
            ui.println("Not enough credits to buy " + selectedItem.getName() + ".");
            ui.pressAnyKey();
            return;
        }

        Items.Item toAdd = new Items.Item(selectedItem.getName(), selectedItem.getBasePrice(), quantity,
                selectedItem.getDescription()) {
        };

        if (!player.getShip().addItem(toAdd)) {
            ui.println("Not enough cargo space to buy " + quantity + " " + selectedItem.getName() + ".");
            ui.pressAnyKey();
            return;
        }

        player.spendCredits(cost);
        selectedItem.setQuantity(selectedItem.getQuantity() - quantity);
        sound.sfxTransaction();
        ui.println("You bought " + quantity + " " + selectedItem.getName() + " for " + cost + " credits.");
        ui.pressAnyKey();
    }

    private void sellItem() {
        ArrayList<Items.Item> cargo = player.getShip().getCargo();
        if (cargo.isEmpty()) {
            ui.println("You have no cargo to sell.");
            ui.pressAnyKey();
            return;
        }

        ui.println("\n--- Your Cargo ---");
        for (int i = 0; i < cargo.size(); i++) {
            Items.Item it = cargo.get(i);
            ui.println("[" + (i + 1) + "] " + it.getName() + " x" + it.getQuantity()
                    + " | Sell Price: " + getSellPrice(it));
        }

        ui.println("\nEnter cargo item number to sell:");
        int cargoNumber = readIntLoop();

        if (cargoNumber <= 0 || cargoNumber > cargo.size()) {
            ui.println("Invalid cargo selection.");
            ui.pressAnyKey();
            return;
        }

        Items.Item cargoItem = cargo.get(cargoNumber - 1);
        ui.println("Enter quantity to sell:");
        int sellQty = readIntLoop();

        if (sellQty <= 0 || sellQty > cargoItem.getQuantity()) {
            ui.println("Invalid quantity.");
            ui.pressAnyKey();
            return;
        }

        int sellValue = getSellPrice(cargoItem) * sellQty;
        int normalValue = cargoItem.getBasePrice() * sellQty;

        cargoItem.setQuantity(cargoItem.getQuantity() - sellQty);
        if (cargoItem.getQuantity() <= 0) {
            cargo.remove(cargoNumber - 1);
        }

        player.earnCredits(sellValue);
        addReputation(milestones.recordCreditsEarned(sellValue));

        ui.println("Sold " + sellQty + " " + cargoItem.getName() + " for " + sellValue + " credits.");
        if (sellValue > normalValue) {
            addReputation(milestones.recordTradeRoute());
        }
        ui.pressAnyKey();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void travelConsole() {
		// iterative travel console
		while (true) {
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
			ui.println("Fuel: " + player.getShip().getFuel() + "/" + player.getShip().getMaxFuel() + " kL");
			ui.println("\nFrom here, you can travel to the following planets:");
			for (Planet planet : planets) {
				if (planet != currentPlanet) {
					ui.println("- " + planet.getName() + " ");
				}
			}
			ui.println(" ");
			for (int i = 0; i < planets.size(); i++) {
				Planet planet = planets.get(i);
				int distance = Math.abs(currentPlanet.getDistance() - planet.getDistance());
				if (planet == currentPlanet) {
					ui.println("[" + (i + 1) + "] " + planet.getName() + " (current location)");
				} else {
					ui.println("[" + (i + 1) + "] Travel to " + planet.getName() + " | Distance: "
							+ distance + " AU | Fuel: " + (distance * fuelPerAU) + " kL");
				}
			}
			ui.println("[0] Return to Bridge");
			int choice = readIntLoop();
			if (choice == 0) {
				return; // back to Bridge loop
			} else if (choice > 0 && choice <= planets.size()) {
				Planet selectedPlanet = planets.get(choice - 1);
				if (selectedPlanet == currentPlanet) {
					ui.println("You are already on " + selectedPlanet.getName() + ".");
					ui.pressAnyKey();
					continue;
				} else {
					final int travelTime = Math.abs(currentPlanet.getDistance() - selectedPlanet.getDistance()); // time calculation based on distance
					int fuelNeeded = travelTime * fuelPerAU;
					if (!canTravelTo(selectedPlanet)) {
						ui.println(selectedPlanet.getName() + " requires a stronger fuel system.");
						ui.pressAnyKey();
						continue;
					}
					if (player.getShip().getFuel() < fuelNeeded) {
						ui.println("Not enough fuel. You need " + fuelNeeded + "kL for this trip.");
						ui.pressAnyKey();
						continue;
					}
					currentPlanet = selectedPlanet;
					player.getShip().useFuel(fuelNeeded); // fuel usage is calculated based on travel time
					flush();
					art.travelling();
					ui.println("Initializing warpdrive...");
					for (int i = 0; i < travelTime; i++) {
						System.out.print(".");
						System.out.flush();
						delay(1000 / player.getShip().getSpeed()); // travel time is reduced based on ship speed
					}
					ui.println(" ");
					flush();
					art.arrived();
					ui.println("Arrived at " + currentPlanet.getName() + "!");
					// record discovery milestones and apply reputation
					addReputation(milestones.checkPlanetDiscovery(currentPlanet));
					generateGalacticNews();
					ui.pressAnyKey();
					continue;
				}
			} else {
				ui.println("Invalid destination.");
				ui.pressAnyKey();
				continue;
			}
		}
	}

        private boolean canTravelTo(Planet planet) {
        if (planet.getName().contains("Trade Outpost") && player.getShip().getFuelUpgradeLevel() < 1) {
            return false;
        } else if (planet.getDistance() >= 45 && player.getShip().getFuelUpgradeLevel() < 2) {
            return false;
        } else {
            return true;
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void refuelShip() {
        int missingFuel = player.getShip().getMaxFuel() - player.getShip().getFuel();

        if (missingFuel <= 0) {
            ui.println("Fuel tank is already full.");
            return;
        }

        int fuelCanisters = getCargoQuantity("Fuel Canister");
        if (fuelCanisters > 0) {
            int fuelToAdd = Math.min(missingFuel, fuelCanisters * fuelPerCanister);
            int canistersUsed = (fuelToAdd + fuelPerCanister - 1) / fuelPerCanister;
            removeCargoQuantity("Fuel Canister", canistersUsed);
            player.getShip().refuel(fuelToAdd);
            ui.println("Used " + canistersUsed + " Fuel Canister(s) to refuel " + fuelToAdd + " kL.");
            return;
        }

        int cost = missingFuel * 5;
        if (player.spendCredits(cost)) {
            player.getShip().refuel(missingFuel);
            ui.println("Refueled " + missingFuel + " kL for " + cost + " credits.");
        }
    }

    private int getCargoUsed() {
        int used = 0;
        for (Items.Item item : player.getShip().getCargo()) {
            used += item.getQuantity();
        }
        return used;
    }

    private int getCargoQuantity(String itemName) {
        int total = 0;
        for (Items.Item item : player.getShip().getCargo()) {
            if (item.getName().equals(itemName)) {
                total += item.getQuantity();
            }
        }
        return total;
    }

    private boolean removeCargoQuantity(String itemName, int quantity) {
        int remaining = quantity;
        ArrayList<Items.Item> cargo = player.getShip().getCargo();

        for (int i = cargo.size() - 1; i >= 0 && remaining > 0; i--) {
            Items.Item item = cargo.get(i);
            if (item.getName().equals(itemName)) {
                int removeAmount = Math.min(item.getQuantity(), remaining);
                item.setQuantity(item.getQuantity() - removeAmount);
                remaining -= removeAmount;

                if (item.getQuantity() <= 0) {
                    cargo.remove(i);
                }
            }
        }

        return remaining == 0;
    }

    private boolean payForUpgrade(int cost) {
        if (getCargoQuantity("Ship Parts") < shipPartsPerUpgrade) {
            ui.println("You need " + shipPartsPerUpgrade + " Ship Parts in cargo for this upgrade.");
            return false;
        }

        if (player.getCredits() < cost) {
            ui.println("Not enough credits.");
            return false;
        }

        player.spendCredits(cost);
        removeCargoQuantity("Ship Parts", shipPartsPerUpgrade);
        return true;
    }

    private int getFuelUpgradeCost() {
        if (player.getShip().getFuelUpgradeLevel() == 0) {
            return 700;
        } else if (player.getShip().getFuelUpgradeLevel() == 1) {
            return 1600;
        } else {
            return 0;
        }
    }

    private int getSpeedUpgradeCost() {
        if (player.getShip().getSpeedUpgradeLevel() == 0) {
            return 600;
        } else if (player.getShip().getSpeedUpgradeLevel() == 1) {
            return 1500;
        } else {
            return 0;
        }
    }

    private int getCargoUpgradeCost() {
        if (player.getShip().getCargoUpgradeLevel() == 0) {
            return 500;
        } else if (player.getShip().getCargoUpgradeLevel() == 1) {
            return 1400;
        } else {
            return 0;
        }
    }

    private void upgradeConsole() {
		// iterative upgrade console with invalid-choice handling
		while (true) {
			flush();
			ui.println("--- Ship Upgrades ---\n");
			ui.println("Ship Parts in cargo: " + getCargoQuantity("Ship Parts"));
			ui.println("Each upgrade requires " + shipPartsPerUpgrade + " Ship Parts plus credits.\n");
			ui.println("[1] Fuel Upgrade | Cost: " + getFuelUpgradeCost());
			ui.println("[2] Speed Upgrade | Cost: " + getSpeedUpgradeCost());
			ui.println("[3] Cargo Upgrade | Cost: " + getCargoUpgradeCost());
			ui.println("[0] Return to Ship Console");
			int choice = readIntLoop();
			if (choice == 1) {
				int cost = getFuelUpgradeCost();
				if (cost == 0) {
					ui.println("Fuel is already fully upgraded.");
				} else if (payForUpgrade(cost)) {
					player.getShip().upgradeFuel();
					ui.println("Fuel upgraded. New max fuel: " + player.getShip().getMaxFuel() + "kL");
				}
				ui.pressAnyKey();
				continue;
			} else if (choice == 2) {
				int cost = getSpeedUpgradeCost();
				if (cost == 0) {
					ui.println("Speed is already fully upgraded.");
				} else if (payForUpgrade(cost)) {
					player.getShip().upgradeSpeed();
					ui.println("Speed upgraded. New speed: " + player.getShip().getSpeed() + " AU/s");
				}
				ui.pressAnyKey();
				continue;
			} else if (choice == 3) {
				int cost = getCargoUpgradeCost();
				if (cost == 0) {
					ui.println("Cargo is already fully upgraded.");
				} else if (payForUpgrade(cost)) {
					player.getShip().upgradeCargo();
					ui.println("Cargo upgraded. New cargo capacity: " + player.getShip().getCargoCapacity() + " units");
				}
				ui.pressAnyKey();
				continue;
			} else if (choice == 0) {
				return; // back to shipConsole
			} else {
				ui.println("Invalid choice.");
				ui.pressAnyKey();
				continue;
			}
		}
	}

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void newsConsole() {
        flush();
        ui.println("GALACTIC NEWS\n");
        ui.println(currentNews.getHeadline());
        ui.println("\nUse market news to find better trade routes.");
        ui.pressAnyKey();
        return;
     }

    private void generateGalacticNews() {
        // include occasional calm/no-major-news periods
        if (Math.random() < 0.25) {
            currentNews = new NoMajorNews();
        } else {
            currentNews = GalacticNews.randomNews();
        }
    }

    private int getBuyPrice(Items.Item item) {
        double price = item.getBasePrice();

        if (currentPlanet.getName().contains("Mining") && isMetal(item.getName())) {
            price *= 0.7;
        } else if (currentPlanet.getName().contains("Trade Outpost") && isOutpostGood(item.getName())) {
            price *= 0.8;
        }

        price = currentNews.adjustPrice(item.getName(), price);

        return (int) Math.round(price);
    }

    private int getSellPrice(Items.Item item) {
        double price = item.getBasePrice() * 0.8;

        if (currentPlanet.getName().contains("Mining") && needsMiningColony(item.getName())) {
            price = item.getBasePrice() * 2.0;
        } else if (currentPlanet.getName().contains("Trade Outpost") && isMetal(item.getName())) {
            price = item.getBasePrice() * 2.2;
        } else if (isOuterWorld(currentPlanet) && needsOuterWorld(item.getName())) {
            price = item.getBasePrice() * 2.5;
        }

        price = currentNews.adjustPrice(item.getName(), price);

        return (int) Math.round(price);
    }

    private boolean isMetal(String itemName) {
        return itemName.equals("Ore") || itemName.equals("Titanium") || itemName.equals("Copper");
    }

    private boolean isOutpostGood(String itemName) {
        return itemName.equals("Consumer Goods") || itemName.equals("Electronics");
    }

    private boolean needsMiningColony(String itemName) {
        return itemName.equals("Food") || itemName.equals("Machinery");
    }

    private boolean needsOuterWorld(String itemName) {
        return itemName.equals("Consumer Goods") || itemName.equals("Electronics") || itemName.equals("Medicine");
    }

    private boolean isOuterWorld(Planet planet) {
        return planet.getDistance() >= 45;
    }

    private void addReputation(int amount) {
        if (amount <= 0) {
            return;
        }

        player.addReputation(amount);
        ui.println("Reputation: " + player.getReputation() + " - " + player.getReputationTitle());

        // lower victory threshold to align with available milestone totals (adjustable)
        if (player.getReputation() >= 600) {
            ui.println("\nCongratulations " + player.getName() + "! You have achieved legendary status in the Outer Rim!");
            ui.println("You are now recognized as the most renowned trader in the galaxy.");
             System.exit(0);
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
        sound.playSound("Sounds/flush.wav");
        ui.flush();
        title();
    }

    public void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    private int readIntLoop() {
        while (true) {
            input(); // prints "> "
            String line = null;
            try {
                line = input.nextLine();
                int val = Integer.parseInt(line.trim());
                return val;
            } catch (Exception e) {
                sound.sfxError();
                ui.println("Please enter a valid number.");
                ui.pressAnyKey();
            }
        }
    }
}
