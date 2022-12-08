/**
 * This class represents an Apple object and inherits the attributes/methods of the Crop superclass.
 */
public class Apple extends Crop{

    /**
     * This constructor sets the attributes/methods of the Apple class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Apple() {
        super("Fruit tree", 10, 7, 7, 5, 5, 10, 5, 5, 25);
        
    }
    
}
