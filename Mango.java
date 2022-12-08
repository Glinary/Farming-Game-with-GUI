/**
 * This class represents the Mango object and inherits the attributes/methods of the Crop superclass.
 */
public class Mango extends Crop{

    /**
     * This constructor sets the attributes/methods of the Mango class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Mango() {
        super("Fruit tree", 10, 7, 7, 4, 4, 5, 10, 8, 25);
        
    }
    
}
