/**
 * This represents a Watering Can object and inherits the methods of the Tool superclass.
 * It contains double values that contain the cost players spend and experiences  earned
 * from using the object.
 */
public class WateringCan extends Tool {
    private double costFromUsage = 0;
    private double experienceFromUsage = 0.5;
    
    public WateringCan() {
        
    }

    /**
     * This method waters the tile and calls the addTimesCropWasWatered method to contain
     * the amount of times a crop on a tile was watered.
     * 
     * @param tile - the tile object that will be watered
     */
    public boolean useTool (Tile tile) {
        System.out.println("tile read getHasCrop: " + tile.getHasCrop());
        if(tile.getIsPlowed() && tile.getHasCrop()) {
            
            tile.getCrop().addTimesCropWasWatered(); //TODO: GETCROP IS NULL
            System.out.println("You used the watering can.");
            return true;
        } else if (tile.getIsPlowed()) {
            System.out.println("You used the watering crop on an empty tile");
            return true;
        } 
        else if(tile.getIsPlowed() == false){
            System.out.println("The tile is not plowed.");
        } else {
            System.out.println("The tile has a rock.");
        }
        return false;
    }

    /**
     * This method gets the cost spent by the user to use the Watering Can object
     * 
     */
    public double getCostFromUsage() {
        return this.costFromUsage;
    }

    /**
     * This method gets the experience earned by the user from using the Watering Can object
     */
    public double getExperienceFromUsage() {
        return this.experienceFromUsage;
    }
}
