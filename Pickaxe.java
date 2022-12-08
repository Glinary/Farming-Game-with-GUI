/**
 * This represents a Pickaxe object and inherits the methods of the Tool superclass.
 * It contains double values that contain the cost players spend and experiences earned
 * from using the object
 */
public class Pickaxe extends Tool{
    private double costFromUsage = 50;
    private double experienceFromUsage = 15;

    /**
     * This constsructor currently does not set any of the class' atributes to a default value
     */
    public Pickaxe () {

    }

    /**
     * This method activates when player has enough money to use the pickaxe,
     * uses the pickaxe on the tile, and can only be used
     * when there are rocks on the tile.
     * 
     * @param tile - the tile object who's rocks will be removed by the pickaxe
     * @return - returns true if the Pickaxe was successfully used
     */
    public boolean useTool (Tile tile) {
        if (tile.getHasRock()) {
            tile.setHasRock(false);
            System.out.println("You used the pickaxe tool.");
            return true;
        } else {
            System.out.println("There is no rock in this tile.");
            return false;
        }
    }

    /**
     * This returns the cost to be deducted from the user's objectCoins when Pickaxe is used
     * 
     * @return - the cost from using the Pickaxe tool
     */
    public double getCostFromUsage () {
        return this.costFromUsage;
    }
    
    /**
     * This returns the experience gained by the user when Pickaxe is used
     * 
     * @return - the experience gained from using the Pickaxe tool
     */
    public double getExperienceFromUsage () {
        return this.experienceFromUsage;
    }
}
