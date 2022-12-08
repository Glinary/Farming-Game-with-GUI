/**
 * This class represents the Carrot object and inherits the methods/attributes of the Crop superclass.
 */
public class Carrot extends Crop{

    /**
     * This constructor sets the attributes/methods of the Carrot class which contains its crop type,
     * minimum/maximum water/fertilizer requirements, minimum/maximum produces, base selling price, 
     * and experience gained from harvesting it.
     */
    public Carrot() {
        super("Root crop", 3, 1, 2, 0, 1, 1, 1, 9, 7.5);
        
    }
}
