import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private double objectCoins = 100.0; 
    private int level = 0;
    private double experience = 0.0;
    private ArrayList<Tool> toolList;
    private String farmerType = "Farmer";
    private int bonusEarningsPerProduce = 0;
    private int seedCostReduction = 0;
    private int waterBonusLimitIncrease = 0;
    private int fertilizerBonusLimitIncrease = 0;

    public Player () {
        toolList = new ArrayList<Tool>();

        toolList.add(new Plow());
        toolList.add(new WateringCan());
        toolList.add(new Fertilizer());
        toolList.add(new Pickaxe());
        toolList.add(new Shovel());
    }

    public void increaseObjectCoins (double num) {
        this.objectCoins += num;
    }

    public void setLevel () {
        double num = this.experience / 100; // TODO: not yet sure with the formula
        this.level = (int)num;
    }

    public void increaseExperience (double num) {
        this.experience += num;
        setLevel();
    }

    public void setFarmerType (String name) {
        this.farmerType = name;
    }

    //TODO: clarify if player must first become the lower farmerType before becoming eligible for the next farmerType
    public void checkStatusBeforeNextDay () {
        int input = -1;

        if (this.level >= 5 && this.farmerType.equals("Farmer")) { //prompt for RegisteredFarmer
            System.out.println("Congratulations! You have reached the stats needed to become a Registered Farmer");
            System.out.println("Would you like to register as a Registered Farmer?");
            System.out.println("Enter [1] to become a Registered Farmer at the cost of 200");
            System.out.println("Enter [0] to proceed without registering");
            Scanner sc = new Scanner(System.in);

            while (input != 1 && input != 0) {
                input = sc.nextInt();
            }

            if (input == 1) {
                if (this.objectCoins >= 200) {
                    setFarmerType("Registered Farmer");
                    this.objectCoins -= 200;
                    this.bonusEarningsPerProduce = 1;
                    this.seedCostReduction = -1;
                    this.waterBonusLimitIncrease = 0;
                    this.fertilizerBonusLimitIncrease = 0;
                    System.out.println("You have successfuly changed your status to a Registered Farmer");
                } else {
                    System.out.println("You do not have enough objectCoins to register as a Registered Farmer");
                }
            }
                
            sc.close();
        } else if (this.level >= 10 && this.farmerType.equals("Registered Farmer")) {
            System.out.println("Congratulations! You have reached the stats needed to become a Distinguished Farmer");
            System.out.println("Would you like to register as a Distinguished Farmer?");
            System.out.println("Enter [1] to become a Distinguished Farmer at the cost of 300");
            System.out.println("Enter [0] to proceed without registering");
            Scanner sc = new Scanner(System.in);

            while (input != 1 && input != 0) {
                input = sc.nextInt();
            }

            if (input == 1) {
                if (this.objectCoins >= 300) {
                    setFarmerType("Distinguished Farmer");
                    this.objectCoins -= 300;
                    this.bonusEarningsPerProduce = 2;
                    this.seedCostReduction = -2;
                    this.waterBonusLimitIncrease = 1;
                    this.fertilizerBonusLimitIncrease = 0;
                    System.out.println("You have successfuly changed your status to a Distinguished Farmer");
                } else {
                    System.out.println("You do not have enough objectCoins to register as a Distinguished Farmer");
                }
            }
                
            sc.close();
        } else if (this.level >= 15 && this.farmerType.equals("Distinguished Farmer")) {
            System.out.println("Congratulations! You have reached the stats needed to become a Legendary Farmer");
            System.out.println("Would you like to register as a Legendary Farmer?");
            System.out.println("Enter [1] to become a Legendary Farmer at the cost of 400");
            System.out.println("Enter [0] to proceed without registering");
            Scanner sc = new Scanner(System.in);

            while (input != 1 && input != 0) {
                input = sc.nextInt();
            }

            if (input == 1) {
                if (this.objectCoins >= 400) {
                    setFarmerType("Legendary Farmer");
                    this.objectCoins -= 400;
                    this.bonusEarningsPerProduce = 4;
                    this.seedCostReduction = -3;
                    this.waterBonusLimitIncrease = 2;
                    this.fertilizerBonusLimitIncrease = 1;
                    System.out.println("You have successfuly changed your status to a Legendary Farmer");
                } else {
                    System.out.println("You do not have enough objectCoins to register as a Legendary Farmer");
                }
            }
                
            sc.close();
        }
    }

    public void resetPlayerStats () {
        this.objectCoins = 100;
        this.level = 0;
        this.experience = 0.0;
        this.farmerType = "Farmer";
        this.bonusEarningsPerProduce = 0;
        this.seedCostReduction = 0;
        this.waterBonusLimitIncrease = 0;
        this.fertilizerBonusLimitIncrease = 0;
    }

    public void plow (Tile tile) {
        if (toolList.get(0).useTool(tile)) {
            this.objectCoins -= toolList.get(0).getCostFromUsage();
            this.experience += toolList.get(0).getExperienceFromUsage();
        }
            
    }

    public void water (Tile tile) {
        if (toolList.get(1).useTool(tile)) {
            this.objectCoins -= toolList.get(1).getCostFromUsage();
            this.experience += toolList.get(0).getExperienceFromUsage();
        }
    }

    public void fertilize (Tile tile) {
        if (toolList.get(2).useTool(tile)) {
            this.objectCoins -= toolList.get(2).getCostFromUsage();
            this.experience += toolList.get(2).getExperienceFromUsage();
        }
    }

    public void pickaxe (Tile tile) {
        if (toolList.get(3).useTool(tile)) {
            this.objectCoins -= toolList.get(3).getCostFromUsage();
            this.experience += toolList.get(3).getExperienceFromUsage();
        }
    }

    public void shovel (Tile tile) {
        if (toolList.get(4).useTool(tile)) {
            this.objectCoins -= toolList.get(4).getCostFromUsage();
            this.experience += toolList.get(4).getExperienceFromUsage();
        }
    }

    public void harvest (Tile tile) {
        if(tile.getCrop().getIsHarvestable()) {
            double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;
            int totalWaterCount, totalFertilizerCount;
            harvestTotal = tile.getCrop().getHarvestYield() * (tile.getCrop().getBaseSellingPrice() + this.bonusEarningsPerProduce);

            totalWaterCount = tile.getCrop().getTimesCropWasWatered();
            if(totalWaterCount > tile.getCrop().getBonusWaterMaximum() + this.waterBonusLimitIncrease) {
                totalWaterCount = tile.getCrop().getBonusWaterMaximum() + this.waterBonusLimitIncrease;
            }

            waterBonus = harvestTotal * 0.2 * totalWaterCount;

            totalFertilizerCount = tile.getCrop().getTimesCropAddedFertilizer();
            if(totalFertilizerCount > tile.getCrop().getBonusFertilizerMaximum() + this.fertilizerBonusLimitIncrease) {
                totalFertilizerCount = tile.getCrop().getBonusFertilizerMaximum() + this.fertilizerBonusLimitIncrease;
            }

            fertilizerBonus = harvestTotal * 0.5 * totalFertilizerCount;

            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

            System.out.println("You harvested the crop and earned " + finalHarvestPrice + " ObjectCoins.");
            tile.resetStats();
            increaseObjectCoins(finalHarvestPrice);
        } else {
            System.out.println("You cannot harvest this crop.");
        }
    }

    public void plantSeed (Tile tile) {

        if (!tile.getHasCrop() && tile.getIsPlowed() && !tile.getHasRock()) {
            System.out.println("Select which seed do you want to plant: ");
            System.out.println("[1] Turnip: " + (5 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[2] Carrot: " + (10 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[3] Potato: " + (20 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[4] Rose: " + (5 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[5] Tulips: " + (10 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[6] Sunflower: " + (20 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[7] Mango: " + (100 - this.seedCostReduction) + " ObjectCoins");
            System.out.println("[8] Apple: " + (200 - this.seedCostReduction) + " ObjectCoins");

            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            while (input < 1 || input > 8) 
                sc.nextInt();

            tile.createCrop(input);
            tile.setHasCrop(true);
            sc.close();

        } else {
            System.out.println("You cannot plant a seed on this tile");
        }
        
    }

    public void displayPlayerInformation () {
        System.out.println("ObjectCoins: " + this.objectCoins);
        System.out.println("Level: " + this.level);
        System.out.println("Experience: " + this.experience);
        System.out.println("Farmer Type: " + this.farmerType);
    }
}