public class Plow extends Tool{
    private double costFromUsage = 0;
    private double experienceFromUsage = 0.5;
    
    public Plow () {

    }

    public boolean useTool(Tile tile) {
        if (!tile.getIsPlowed()) {
            tile.setIsPlowed(true);
            System.out.println("You used the plow tool.");
            return true;
        } else {
            System.out.println("Tile cannot be plowed.");
            return false;
        }
    }

    public double getCostFromUsage() {
        return this.costFromUsage;
    }
    public double getExperienceFromUsage () {
        return this.experienceFromUsage;
    }
}
