/**
 * This class represents a Sunflower object and inherits the attributes/methods of the Crop superclass.
 */
public class Sunflower extends Crop{

    /**
     * This constructor sets the attributes/methods of the Sunflower class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Sunflower() {
        super("Flower", 3, 2, 3, 1, 2, 1, 0, 19, 7.5);
        
    }
    
}
