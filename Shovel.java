/**
 * This class represents the Shovel object and enherits the methods of the Tool superclass.
 * It contains double values that contain the cost players spend and experiences earned
 * from using the object
 */
public class Shovel extends Tool {
    private double costFromUsage = 7;
    private double experienceFromUsage = 2;
    
    /**
     * This constructor currently does not set any of the class' attributes to a default value
     */
    public Shovel () {

    }

    /**
     * This method activates when player has enough money to shovel the tile. 
     * If the tile has a crop, the plant would be removed and reverts the tile back to an unplowed tile. 
     * If there is a withered crop, it removes the withered crop on the tile. 
     * If used on an unplowed tile or a tile with rocks, the player still spends money to use the shovel.
     * 
     * @param - the tile to be shoveled
     * @return - returns true if shovel tool was successfully used
     */
    public boolean useTool (Tile tile) {
        if (!tile.getIsPlowed() || tile.getHasRock()) {
            System.out.println("You shoveled the tile");
            return true;
        } else if (tile.getHasCrop()) {
            if (tile.getCrop().getIsWithered()) {
                System.out.println("You shoveled the withered crop");
                tile.resetStats();
            } 
            else {
                System.out.println("Oh no! You shoveled a plant");
                tile.resetStats();
            }
            
            return true;
        } else {
            System.out.println("You cannot use the shovel");
            return false;
        }
    }

    /**
     * This method gets the cost spent by the user to use the shovel
     * 
     * @return - the cost of using the shovel
     */
    public double getCostFromUsage () {
        return this.costFromUsage;
    }

    /**
     * This method gets the experience earned by the user from using the shovel
     * 
     * @return - the experienced gained from using the shovel
     */
    public double getExperienceFromUsage () {
        return this.experienceFromUsage;
    }
}
