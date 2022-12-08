import java.util.Random;

/**
 * This class represents a crop object that will be planted on a tile and returns a random
 * number of harvested plant based on the attributes of the crop type. This also contains the
 * attributes of a crop, whether it is withered, harvestable, its cost price, its experience value,
 * its minimum and maximum water requirements, and its minimum and maximum fertilizer requirements
 */
public class Crop {
    Random rand = new Random();
    private int daysLeft;
    private boolean isWithered = false;
    private boolean isHarvestable = false;
    private int harvestYield;
    private double baseSellingPrice;
    private int timesCropWasWatered = 0;
    private int timesCropAddedFertilizer = 0;
    private int minWaterRequirement;
    private int bonusWaterMaximum;
    private int minFertilizerRequirement;
    private int bonusFertilizerMaximum;
    private String cropType;
    private double experienceYield;

    /**
     * This constructor sets the values for the attributes of the crop depending on its type and
     * is inherited by its subclasses turnip, carrot, potato, rose, tulips, sunflower, mango, or apple
     * 
     * @param cropType - the type of the crop which may be a root crop, flower, or fruit tree
     * @param daysLeft - the number of days left for the crop to be harvestable
     * @param minWaterRequirement - the minimum number of times the crop should be watered to be harvestable
     * @param bonusWaterMaximum - the maximum number of times the crop can be watered as a bonus to the computation price of the harvested crop
     * @param minFertilizerRequirement - the minimum number of times the crop should be fertilized to be harvestable
     * @param bonusFertilizerMaximum - the maximum number of times the crop can be fertilized as a bonus to the computation price of the harvested crop
     * @param minimumYield - the minimum number of produces depending on the seed once harvested
     * @param extraYield - the additional number of produces depending on the seed once harvested
     * @param baseSellingPrice - the base selling price of the crop to be sold
     * @param experienceYield - the experience that may be generated from harvesting the crop
     */
    public Crop(String cropType, int daysLeft, int minWaterRequirement, int bonusWaterMaximum, int minFertilizerRequirement, int bonusFertilizerMaximum, 
                int minimumYield, int extraYield, double baseSellingPrice, double experienceYield) {
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

    /**
     * This method adds to the number of times the crop has been watered
     */
    public void addTimesCropWasWatered() {
        this.timesCropWasWatered++;
    }

    /** 
     * This method adds to the number of times the crop has been fertilized
    */
    public void addTimesCropAddedFertilizer() {
        this.timesCropAddedFertilizer++;
    }

    /**
     * This method withers a crop that was not harvested on harvest day
     */
    public void witherIfNotHarvestedOnHarvestDay() {
        //crop becomes withered if it was not harvested on harvest day
        if(this.daysLeft < 0 && 
          (this.timesCropWasWatered>=minWaterRequirement && this.timesCropAddedFertilizer >= minFertilizerRequirement)) {
            if(this.daysLeft < 0) {
                System.out.println("A crop has withered because it was not harvested on harvest day");
                this.isHarvestable = false;
                this.isWithered = true;
            }
        }
    }
    
    /**
     * This method updates the crop as withered if it does not meet the requirements on harvest day
     */
    public void witherIfUnhealthyOnHarvestDay () {
        if (this.daysLeft == 0 && 
            (this.timesCropWasWatered < this.minWaterRequirement || this.timesCropAddedFertilizer < this.minFertilizerRequirement)) {
                System.out.println("A crop has withered because it did not meet its requirements on harvest day");
                this.isHarvestable = false;
                this.isWithered = true;
        }

    }

    /**
     * This method checks if the crop can be harvested based on the number of days left
     * and the minimum water/fertilizer requirements
     */
    public void checkIfHarvestable() {
        if(daysLeft == 0) {
            if (this.timesCropWasWatered >= this.minWaterRequirement && 
                this.timesCropAddedFertilizer >= this.minFertilizerRequirement) {
                    System.out.println("A harvestable crop exists");
                    this.isHarvestable = true;
            }
        }
            
    }

    /**
     * This method subtracts the number of days left for the crop to be harvestable
     */
    public void subtractDaysLeft() {
        this.daysLeft--;
    }

    /**
     * This method returns the number of produces the crop has resulted to once harvested
     * 
     * @return - the number of produces from the crop
     */
    public int getHarvestYield() {
        return this.harvestYield;
    }

    /**
     * This method returns the base selling price of the crop to be harvested
     * 
     * @return - the value of the base selling price of the crop
     */
    public double getBaseSellingPrice() {
        return this.baseSellingPrice;
    }

    /**
     * This method returns the number of days left for the crop to be harvestable
     * 
     * @return - the number of days left
     */
    public int getDaysLeft() {
        return this.daysLeft;
    }

    /**
     * This method returns the number of times the crop has been watered
     * 
     * @return - the number of times the crop was watered
     */
    public int getTimesCropWasWatered() {
        return this.timesCropWasWatered;
    }

    /**
     * This method returns the number of times the crop has been fertilized
     * 
     * @return - the numbeof times the crop was fertilized
     */
    public int getTimesCropAddedFertilizer() {
        return this.timesCropAddedFertilizer;
    }

    /**
     * This method returns the minimum amount of time required by the crop to be watered
     * 
     * @return - the minimum amount of times the crop needs to be watered
     */
    public int getMinWaterRequirement() {
        return this.minWaterRequirement;
    }

    /**
     * This method returns the maximum amount of times the crop can be watered when computing its final harvest price
     * 
     * @return - the maximum amount of times the water can be watered when computing the bonus price
     */
    public int getBonusWaterMaximum() {
        return this.bonusWaterMaximum;
    }

    /**
     * This method returns the minimum amount of time required by the crop to be fertilized
     * 
     * @return - the minimum amount of times the crop needs to be fertilized
     */
    public int getMinFertilizerRequirement() {
        return this.minFertilizerRequirement;
    }

    /**
     * This method returns the maximum amount of times the crop can be fertilized when computing its final harvest price
     * 
     * @return - the maximu of times the water can be fertilized when computing the bonus price
     */
    public int getBonusFertilizerMaximum() {
        return this.bonusFertilizerMaximum;
    }

    /**
     * This method returns the crop type of the crop
     * 
     * @return - the String value equal to the type of the crop
     */
    public String getCropType() {
        return this.cropType;
    }

    /**
     * This method returns the experience that may be added to the player's stats based on the crop
     * @return
     */
    public double getExperienceYield() {
        return this.experienceYield;
    }

    /**
     * This method returns the value on whether or not the crop has withered
     * 
     * @return - the boolean value related to the wither status of the crop
     */
    public boolean getIsWithered() {
        return this.isWithered;
    }

    /**
     * This method returns the value on whether or not the crop can be harvested
     * 
     * @return - the boolean value related to the crop's status of being harvestable
     */
    public boolean getIsHarvestable() {
        return this.isHarvestable;
    }
}
