package Planets;

import Items.*;

/**
 * Simple Earth planet stub so TheOuterRimGame can instantiate a starting planet.
 */
public class Earth extends Planet {
    public Earth() {
        super("Earth", "The center of the Outer Rim", 0);
    }

    @Override
    public void generateMarket() {
        // to be implemented: populate market with starting items
    }
}
