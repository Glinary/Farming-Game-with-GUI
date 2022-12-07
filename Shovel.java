public class Shovel extends Tool {
    private double costFromUsage = 7;
    private double experienceFromUsage = 2;
    
    public Shovel () {

    }

    public boolean useTool (Tile tile) {
        if(!(tile.getIsPlowed() == false || tile.getHasRock())) {
            tile.resetStats();
            System.out.println("You used a shovel.");
        } else
            System.out.println("You cannot use the shovel");
        return true;
    }
    public double getCostFromUsage () {
        return this.costFromUsage;
    }
    public double getExperienceFromUsage () {
        return this.experienceFromUsage;
    }
}
