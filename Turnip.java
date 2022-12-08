/**
 * This class represents a Turnip object and inherits the attributes/methods of the Crop superclass
 */
public class Turnip extends Crop{

    /**
     * This constructor sets the attributes/methods of the Turnip class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Turnip() {
        super("Root crop", 2, 1, 2, 0, 1, 1, 1, 6, 5);
        
    }
}
