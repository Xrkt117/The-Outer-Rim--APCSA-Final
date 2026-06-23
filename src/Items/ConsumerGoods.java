package Items;

public class ConsumerGoods extends Item {

    public ConsumerGoods(int quantity) {
        // Good profit item for outer colonies, but not something mining worlds produce.
        super(
            "Consumer Goods",
            55,
            quantity,
            "Everyday goods that distant colonies pay well for."
        );
    }
}
