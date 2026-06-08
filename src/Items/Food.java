package Items;
// Medicine item (empty shell)
public class Food extends Item {

    public Food(int quantity) {
        super(
            "Food", 
            15, 
            quantity, 
            "Nobody knows what it's made of. Everyone keeps on eating it anyway."
        );
    }
}
