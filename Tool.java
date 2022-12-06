abstract public class Tool {

    public Tool () {

    }

    abstract public boolean useTool(Tile tile);
    abstract public double getCostFromUsage();
    abstract public double getExperienceFromUsage();
}