package News;

public class NoMajorNews extends GalacticNews {

    public NoMajorNews() {
        // Keeps the market from always having a special event active.
        super(
            "No major market news right now.",
            "",
            1.0
        );
    }
}
