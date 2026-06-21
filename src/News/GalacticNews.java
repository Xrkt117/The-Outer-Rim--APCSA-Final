package News;

public abstract class GalacticNews {
    private String headline;
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
