package Ships;
// CargoShip subclass (empty shell)
public class CargoShip extends Ship {

     public CargoShip() {
        super(
            "Cargo Ship", 
            50,
            50,
            3,
            1,
            100,
            null
        );
    } 

    public String getDescription() {
        return "Large cargo capacity but slow speed";
    }

}
