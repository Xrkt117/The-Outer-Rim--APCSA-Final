import java.util.ArrayList;
import ConsoleUI.ui;
import Planets.Planet;

public class ReputationMilestones {
    private ArrayList<String> visitedPlanets;
    private int totalCreditsEarned;
    private int profitableRoutes;
    private boolean oneThousandCreditsReached;
    private boolean fiveThousandCreditsReached;
    private boolean tenThousandCreditsReached;
    private boolean tradeOutpostReached;
    private boolean outerWorldReached;
    private boolean farthestPlanetReached;
    private boolean firstRouteReached;
    private boolean majorRouteReached;

    public ReputationMilestones() {
        visitedPlanets = new ArrayList<>();
    }

    public int getTotalCreditsEarned() {
        return totalCreditsEarned;
    }

    public void startAt(String planetName) {
        visitedPlanets.clear();
        visitedPlanets.add(planetName);
    }

    public int checkPlanetDiscovery(Planet planet) {
        int reputation = 0;

        if (!visitedPlanets.contains(planet.getName())) {
            visitedPlanets.add(planet.getName());
            ui.println("New planet visited!");
            ui.println("+25 reputation");
            reputation += 25;
        }

        if (planet.getName().contains("Trade Outpost") && !tradeOutpostReached) {
            tradeOutpostReached = true;
            ui.println("Trade Outpost reached!");
            ui.println("+50 reputation");
            reputation += 50;
        }

        if (planet.getDistance() >= 45 && !outerWorldReached) {
            outerWorldReached = true;
            ui.println("Outer Worlds reached!");
            ui.println("+75 reputation");
            reputation += 75;
        }

        if (planet.getName().contains("Erebus") && !farthestPlanetReached) {
            farthestPlanetReached = true;
            ui.println("Farthest planet reached!");
            ui.println("+100 reputation");
            reputation += 100;
        }

        return reputation;
    }

    public int recordCreditsEarned(int credits) {
        int reputation = 0;
        totalCreditsEarned += credits;

        if (totalCreditsEarned >= 1000 && !oneThousandCreditsReached) {
            oneThousandCreditsReached = true;
            ui.println("1,000 credits earned!");
            ui.println("+25 reputation");
            reputation += 25;
        }

        if (totalCreditsEarned >= 5000 && !fiveThousandCreditsReached) {
            fiveThousandCreditsReached = true;
            ui.println("5,000 credits earned!");
            ui.println("+50 reputation");
            reputation += 50;
        }

        if (totalCreditsEarned >= 10000 && !tenThousandCreditsReached) {
            tenThousandCreditsReached = true;
            ui.println("10,000 credits earned!");
            ui.println("+100 reputation");
            reputation += 100;
        }

        return reputation;
    }

    public int recordTradeRoute() {
        int reputation = 0;
        profitableRoutes++;

        if (profitableRoutes >= 1 && !firstRouteReached) {
            firstRouteReached = true;
            ui.println("First successful trade route!");
            ui.println("+20 reputation");
            reputation += 20;
        }

        if (profitableRoutes >= 5 && !majorRouteReached) {
            majorRouteReached = true;
            ui.println("Major trade route established!");
            ui.println("+50 reputation");
            reputation += 50;
        }

        return reputation;
    }
}
