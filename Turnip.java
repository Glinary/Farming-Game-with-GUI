import java.util.Random;

public class Turnip {
    Random rand = new Random();
    private int daysLeft = 2;
    private boolean isWithered;
    private boolean isHarvestable = false;
    private int harvestYield;
    private int sellingPrice = 6;
    private int waterCount = 0;
    private int minWaterRequirement = 2;

    public Turnip () {
        int randomYield = rand.nextInt(1) + 1;
        this.harvestYield = randomYield;
        this.isWithered = false;
    }

    public void addWaterCount() {
        this.waterCount++;
    }

    public void checkIfWithered() {
        if(this.daysLeft < 0) {
            this.isHarvestable = false;
            this.isWithered = true;
        }
    }

    public boolean checkIfHarvestable() {
        if(daysLeft == 0)
            this.isHarvestable = true;

        return this.isHarvestable;
    }

    public void subtractDaysLeft() {
        this.daysLeft--;
    }

    public int getHarvestYield() {
        return this.harvestYield;
    }

    public int getSellingPrice() {
        return this.sellingPrice;
    }

    public int getDaysLeft() {
        return this.daysLeft;
    }

    public int getWaterCount() {
        return this.waterCount;
    }

    public int getMinWaterRequirement() {
        return this.minWaterRequirement;
    }

    public boolean getIsWithered() {
        return this.isWithered;
    }

    public void setIsWithered(boolean status) {
        this.isWithered = status;
    }

}
