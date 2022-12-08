/**
 * This represents a Watering Can object and inherits the methods of the Tool superclass.
 * It contains double values that contain the cost players spend and experiences earned
 * from using the object.
 */
public class WateringCan extends Tool {
    private double costFromUsage = 0;
    private double experienceFromUsage = 0.5;
    
    /**
     * This constructor currently does not set any of the class' attributes to a default value
     */
    public WateringCan() {
        
    }

    /**
     * This method waters the tile and calls the addTimesCropWasWatered method to contain
     * the amount of times a crop on a tile was watered, provided that the tile is plowed
     * and has a crop. However, watering a tile without a crop still increases the experience of the player,
     * but does not currently affect the final computation of the crop that will be planted in the future.
     * 
     * @param tile - the tile object that will be watered
     * @return - returns true if watering can was successfully used
     */
    public boolean useTool (Tile tile) {
        
        if(tile.getIsPlowed()) {
            if (tile.getHasCrop()) {
                tile.getCrop().addTimesCropWasWatered();
                System.out.println("You used the watering can.");
            } else
                System.out.println("You watered an empty tile");
            
            return true;
        } 
        else if(tile.getIsPlowed() == false){
            System.out.println("The tile is not plowed.");
            return false;
        } else {
            System.out.println("The tile has a rock.");
            return false;
        }
    }

    /**
     * This method gets the cost spent by the user to use the Watering Can object
     * 
     * @return - the cost of using the watering can
     */
    public double getCostFromUsage() {
        return this.costFromUsage;
    }

    /**
     * This method gets the experience earned by the user from using the Watering Can object
     * 
     * @return - the experience gained from using the watering can
     */
    public double getExperienceFromUsage() {
        return this.experienceFromUsage;
    }
}
