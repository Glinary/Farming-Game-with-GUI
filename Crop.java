import java.util.Random;

public class Crop {
    Random rand = new Random();
    private int daysLeft;
    private boolean isWithered = false;
    private boolean isHarvestable = false;
    private int harvestYield;
    private int baseSellingPrice;
    private int timesCropWasWatered = 0;
    private int timesCropAddedFertilizer = 0;
    private int minWaterRequirement;
    private int bonusWaterMaximum;
    private int minFertilizerRequirement;
    private int bonusFertilizerMaximum;
    private String cropType;
    private double experienceYield;

    public Crop(String cropType, int daysLeft, int minWaterRequirement, int bonusWaterMaximum, int minFertilizerRequirement, int bonusFertilizerMaximum, 
                int minimumYield, int extraYield, int baseSellingPrice, double experienceYield) {
        this.cropType = cropType;
        this.daysLeft = daysLeft;
        this.minWaterRequirement = minWaterRequirement;
        this.bonusWaterMaximum = bonusWaterMaximum;
        this.minFertilizerRequirement = minFertilizerRequirement;
        this.bonusFertilizerMaximum = bonusFertilizerMaximum;
        this.harvestYield = rand.nextInt(extraYield + 1) + minimumYield;
        this.baseSellingPrice = baseSellingPrice;
        this.experienceYield = experienceYield;
    }

    public void addTimesCropWasWatered() {
        this.timesCropWasWatered++;
    }

    public void addTimesCropAddedFertilizer() {
        this.timesCropAddedFertilizer++;
    }

    public void checkIfWithered() {
        if(this.isHarvestable == true) {
            if(this.timesCropWasWatered < this.minWaterRequirement && this.timesCropAddedFertilizer < this.minFertilizerRequirement || this.daysLeft < 0) {
                this.isHarvestable = false;
                this.isWithered = false;
            }
        }
    }

    public void checkIfHarvestable() {
        if(daysLeft == 0)
            this.isHarvestable = true;
    }

    public void subtractDaysLeft() {
        this.daysLeft--;
    }

    public int getHarvestYield() {
        return this.harvestYield;
    }

    public int getBaseSellingPrice() {
        return this.baseSellingPrice;
    }

    public int getDaysLeft() {
        return this.daysLeft;
    }

    public int getTimesCropWasWatered() {
        return this.timesCropWasWatered;
    }
    public int getTimesCropAddedFertilizer() {
        return this.timesCropAddedFertilizer;
    }

    public int getMinWaterRequirement() {
        return this.minWaterRequirement;
    }

    public int getBonusWaterMaximum() {
        return this.bonusWaterMaximum;
    }

    public int getMinFertilizerRequirement() {
        return this.minFertilizerRequirement;
    }

    public int getBonusFertilizerMaximum() {
        return this.bonusFertilizerMaximum;
    }

    public String getCropType() {
        return this.cropType;
    }

    public double getExperienceYield() {
        return this.experienceYield;
    }

    public boolean getIsWithered() {
        return this.isWithered;
    }

    public boolean getIsHarvestable() {
        return this.isHarvestable;
    }
}
