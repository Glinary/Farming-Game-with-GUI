/**
 * This class represents the plow tool object that can be used by the player for the game.
 * It contains attributes related to the cost of using the plow and the experience generated from using the plow
 */
public class Plow extends Tool{
    private double costFromUsage = 0;
    private double experienceFromUsage = 100.0;
    
    /**
     * This constructor currently does not set any of the plow's attributes to a default value
     */
    public Plow () {

    }

    /**
     * This method represents the action of using the plow tool on a tile,
     * and can only be used when the tile has no rocks and is unplowed
     * 
     * @param tile - the tile to be plowed
     * @return - returns true if the Plow tool was successfully used
     */
    public boolean useTool(Tile tile) { 
        if (!tile.getIsPlowed() && !tile.getHasRock()) {
            tile.setIsPlowed(true);
            System.out.println("You used the plow tool.");
            return true;
        } else {
            System.out.println("Tile cannot be plowed.");
            return false;
        }
    }

    /**
     * This returns the cost to be deducted from using the plow tool
     * 
     * @return - the cost from using the plow
     */
    public double getCostFromUsage() {
        return this.costFromUsage;
    }

    /**
     * This returns the experience to be added from using the plow tool
     * 
     * @return - the experience gained from using the plow
     */
    public double getExperienceFromUsage () {
        return this.experienceFromUsage;
    }
}
