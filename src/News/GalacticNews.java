package News;

public abstract class GalacticNews {
    private String headline;
    // Only one item is affected at a time so the player can quickly read the market shift.
    private String affectedItem;
    private double priceMultiplier;

    public GalacticNews(String headline, String affectedItem, double priceMultiplier) {
        this.headline = headline;
        this.affectedItem = affectedItem;
        this.priceMultiplier = priceMultiplier;
    }

    public String getHeadline() {
        return headline;
    }

    public boolean affectsItem(String itemName) {
        return itemName.equals(affectedItem);
    }

    public double adjustPrice(String itemName, double price) {
        if (affectsItem(itemName)) {
            return price * priceMultiplier;
        } else {
            return price;
        }
    }

    public static GalacticNews randomNews() {
        // News changes trade routes by making one good unusually good or bad for a while.
        int news = (int) (Math.random() * 4);

        if (news == 0) {
            return new OreSurplusNews();
        } else if (news == 1) {
            return new MachineryShortageNews();
        } else if (news == 2) {
            return new ElectronicsDemandNews();
        } else {
            return new FoodConvoyNews();
        }
    }
}
