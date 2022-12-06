public class Pickaxe extends Tool{
    private double costFromUsage = 10;
    private double experienceFromUsage = 4;

    public Pickaxe () {

    }

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

    public double getCostFromUsage () {
        return this.costFromUsage;
    }
    
    public double getExperienceFromUsage () {
        return this.experienceFromUsage;
    }
}
