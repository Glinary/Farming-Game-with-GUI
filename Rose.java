/**
 * This represents a Rose object and inherits the attributes/methods of the Crop superclass.
 */
public class Rose extends Crop{

    /**
     * This constructor sets the attributes/methods of the Rose class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Rose() {
        super("Flower", 1, 1, 2, 0, 1, 1, 0, 5, 2.5);
        
    }
    
}
