public class WateringCan extends Tool {
    private double costFromUsage = 0;
    private double experienceFromUsage = 0.5;
    
    public WateringCan() {
        
    }

    public boolean useTool (Tile tile) {
        if(tile.getIsPlowed() == true) {
            tile.getCrop().addTimesCropWasWatered();
            System.out.println("You used the watering can.");
            return true;
        } else if(tile.getIsPlowed() == false){
            System.out.println("The tile is not plowed.");
        } else {
            System.out.println("The tile has a rock.");
        }
        return false;
    }

    public double getCostFromUsage() {
        return this.costFromUsage;
    }

    public double getExperienceFromUsage() {
        return this.experienceFromUsage;
    }
}
