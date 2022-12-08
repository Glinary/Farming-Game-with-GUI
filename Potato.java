/**
 * This class represents a Potato object and inherits the attributes/methods of the Crop superclass
 */
public class Potato extends Crop{

    /**
     * This constructor sets the attributes/methods of the Potato class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Potato() {
        super("Root crop", 5, 3, 4, 1, 2, 1, 9, 3, 12.5);
    }
    
}
