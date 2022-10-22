import java.util.Random;

public class Turnip {
    Random rand = new Random();
    private int daysLeft = 2;
    private boolean isWatered = false;
    private boolean isWithered = false;
    private boolean isHarvestable = false;
    private int harvestYield = rand.nextInt(1) + 1;
    private int sellingPrice = 6;

    public Turnip () {
        
    }

    public void setIsWatered(boolean isWatered) {
        this.isWatered = isWatered;
    }

    public void checkIfWithered() {
        if(this.daysLeft < 0 || this.isWatered == false) {
            isHarvestable = false;
            isWithered = true;
        }
    }

    public void checkIfHarvestable() {
        if(daysLeft == 0)
            isHarvestable = true;
    }

    public void subtractDaysLeft() {
        this.daysLeft--;
    }

    public int getHarvestYield() {
        return harvestYield;
    }
}
