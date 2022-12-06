public class Fertilizer extends Tool{
    private int costFromUsage = 10;
    private double experienceFromUsage = 4;

    public Fertilizer() {

    }

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
    public double getCostFromUsage() {
        return this.costFromUsage;
    }
    public double getExperienceFromUsage() {
        return this.experienceFromUsage;
    }
}