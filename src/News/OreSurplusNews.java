package News;

public class OreSurplusNews extends GalacticNews {

    public OreSurplusNews() {
        // A surplus makes ore cheap, which can set up a later sell route.
        super(
            "Mining Colony Ore Surplus: Ore prices are down 50%.",
            "Ore",
            0.5
        );
    }
}
