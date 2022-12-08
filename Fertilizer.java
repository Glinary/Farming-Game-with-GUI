/**
 * This class represents the fertilizer object that can be used by the player for the game.
 * It contains attributes related to the cost of using the fertilizer and the experience 
 * generated from using the plow
 */
public class Fertilizer extends Tool{
    private double costFromUsage = 10;
    private double experienceFromUsage = 4;

    /**
     * This constructor currently does not set any of the fertilizer's attributes to a default value
     */
    public Fertilizer() {

    }

    /**
     * This method only activates when player has enough money to use fertilizer,
     * represents the action of using the fertilizer on a tile,
     * and can only be used when the tile has been plowed and has a crop
     * 
     * @param tile - the tile to be fertilized
     * @return - returns true if the Fertilizer was successfully used
     */
    public boolean useTool(Tile tile) {
        if(tile.getIsPlowed() && tile.getHasCrop()) {
            tile.getCrop().addTimesCropAddedFertilizer();
            System.out.println("You used the Fertilizer.");
            return true;
        } else {
            System.out.println("You could not use the Fertilizer.");
            return false;
        }
    }

    /**
     * This returns the cost to be deducted from using the fertilizer
     * 
     * @return - the cost from using the fertilizer
     */
    public double getCostFromUsage() {
        return this.costFromUsage;
    }

    /**
     * This returns the experienced to be added from using the fertilizer
     */
    public double getExperienceFromUsage() {
        return this.experienceFromUsage;
    }
}