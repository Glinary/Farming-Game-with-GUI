/**
 * This abstract class represents a Tool object and serves as a template
 * for all tool objects in the game
 */
abstract public class Tool {

    /**
     * This constructor is currently not used to set any default values for attributes
     */
    public Tool () {

    }

    /**
     * This method represents the action of using the tool object and returns true
     * if it was successfully used.
     * @param tile - the tile that will receive the action of the tools
     * @return - returns true if tool was successfully used
     */
    abstract public boolean useTool(Tile tile);

    /**
     * This method returns the cost from using the tool
     * @return - returns the cost of using the tool
     */
    abstract public double getCostFromUsage();

    /**
     * This method returns the experience gained from using the tool
     * @return - returns the experience gained
     */
    abstract public double getExperienceFromUsage();
}