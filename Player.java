import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the player object that plays the game. It contains the stats of a player
 * such as its inventory of coins, its current level, experience, tools, farmer type, and bonus
 * features depending on the player's current class/farmer type
 */
public class Player {
    private double objectCoins = 100.0; 
    private int level = 0;
    private double experience = 0.0;
    private ArrayList<Tool> toolList;
    private String farmerType = "Farmer";
    private double bonusEarningsPerProduce = 0;
    private double seedCostReduction = 0;
    private int waterBonusLimitIncrease = 0;
    private int fertilizerBonusLimitIncrease = 0;

    /**
     * This constructor creates the tools for playing the game and assigns them to the toolList ArrayList.
     */
    public Player () {
        toolList = new ArrayList<Tool>();
        toolList.add(new Plow());
        toolList.add(new WateringCan());
        toolList.add(new Fertilizer());
        toolList.add(new Pickaxe());
        toolList.add(new Shovel());
    }

    /**
     * This method adds the accepted number value to the current amount of objectCoins the player has
     * 
     * @param num - the amount of money to be added
     */
    public void increaseObjectCoins (double num) {
        this.objectCoins += num;
    }

    /**
     * This method deducts the accepted number value from the current amount of objectCoins the player has
     * 
     * @param num - the amount of money to be deducted
     */
    public void spendObjectCoins (double num) {
        this.objectCoins -= num;
    }

    /**
     * This method sets the current level of the player that increases every 100 amount of experience
     */
    public void setLevel () {
        double num = this.experience / 100;
        this.level = (int)num;
    }

    /**
     * This method increases the amount of experience the player has based on its accepted value
     * 
     * @param num - the amount of experience to be added to the current experience value
     */
    public void increaseExperience (double num) {
        this.experience += num;
        setLevel();
    }

    /**
     * This method sets the current class/farmer type of the player according to the accepted String value
     * 
     * @param name - the new class/farmer type of the player
     */
    public void setFarmerType (String name) {
        this.farmerType = name;
    }

    /**
     * This method checks the current stats of the player and if eligible, prompts them to register to 
     * a new class/farmer type provided that they have enough money for the registration fee.
     */
    public void checkStatusBeforeNextDay () {
        int input = -1;
        Scanner sc = new Scanner(System.in);
        if (this.level >= 5 && this.farmerType.equals("Farmer")) { //prompt for RegisteredFarmer
            System.out.println("Congratulations! You have reached the stats needed to become a Registered Farmer");
            System.out.println("Would you like to register as a Registered Farmer?");
            System.out.println("Enter [1] to become a Registered Farmer at the cost of 200");
            System.out.println("Enter [0] to proceed without registering");

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
                
        } else if (this.level >= 10 && this.farmerType.equals("Registered Farmer")) {
            System.out.println("Congratulations! You have reached the stats needed to become a Distinguished Farmer");
            System.out.println("Would you like to register as a Distinguished Farmer?");
            System.out.println("Enter [1] to become a Distinguished Farmer at the cost of 300");
            System.out.println("Enter [0] to proceed without registering");

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
                
        } else if (this.level >= 15 && this.farmerType.equals("Distinguished Farmer")) {
            System.out.println("Congratulations! You have reached the stats needed to become a Legendary Farmer");
            System.out.println("Would you like to register as a Legendary Farmer?");
            System.out.println("Enter [1] to become a Legendary Farmer at the cost of 400");
            System.out.println("Enter [0] to proceed without registering");

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
                
            
        }

        //sc.close();
    }

    /**
     * This method resets the player's stats should the player decided to play again
     */
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

    /**
     * This method lets the player plow a tile and increases the experience of the player
     * 
     * @param tile - the tile that will be plowed
     */
    public void plow (Tile tile) {

        if (toolList.get(0).useTool(tile)) {
            this.objectCoins -= toolList.get(0).getCostFromUsage();
            this.experience += toolList.get(0).getExperienceFromUsage();
        }
            
    }

    /**
     * This method lets the player water the tile and increases the experience of the player
     * 
     * @param tile - the tile to be watered
     */
    public void water (Tile tile) {
        if (toolList.get(1).useTool(tile)) {
            this.objectCoins -= toolList.get(1).getCostFromUsage();
            this.experience += toolList.get(0).getExperienceFromUsage();
        }
    }

    /**
     * This method lets the player fertilize the crop only if they have enough money, 
     * if the tile has been plowed, if there is a crop on the tile, 
     * and then deducts their money and increases the player's experience
     * 
     * @param tile - the tile of the crop to be fertilized
     */
    public void fertilize (Tile tile) {
        if (this.objectCoins < 10)
            System.out.println("You do not have enough coins to use fertilizer");
        else if (toolList.get(2).useTool(tile)) {
            this.objectCoins -= toolList.get(2).getCostFromUsage();
            this.experience += toolList.get(2).getExperienceFromUsage();
        }
    }

    /**
     * This method lets the player use the pickaxe tool on the tile if they have enough money,
     * if there are rocks on the tile, and then deducts their money and increases the player's experience
     * 
     * @param tile - the tile who's rocks will be removed by the pickaxe
     */
    public void pickaxe (Tile tile) {
        if (this.objectCoins < 50)
            System.out.println("You do not have enough coins to use pickaxe");
        else if (toolList.get(3).useTool(tile)) {
            this.objectCoins -= toolList.get(3).getCostFromUsage();
            this.experience += toolList.get(3).getExperienceFromUsage();
        }
    }

    /**
     * This method lets the player use the shovel tool on the tile if they have enough money, 
     * and then deducts their money and increases the player's experience.
     * 
     * @param tile - the tile to be shoveled
     */
    public void shovel (Tile tile) {

        if (this.objectCoins < 7)
            System.out.println("You do not have enough coins to use shovel");
        else if (toolList.get(4).useTool(tile)){
            this.objectCoins -= toolList.get(4).getCostFromUsage();
            this.experience += toolList.get(4).getExperienceFromUsage();
        }
    }

    /**
     * This method lets the player harvest the crop on the tile, provided that the tile
     * has a harvestable crop. Then, corresponding coins and experience, including bonuses,
     * will be given to the player
     * 
     * @param tile - the tile that will be harvested
     */
    public void harvest (Tile tile) {
        if (!tile.getHasCrop())
            System.out.println("Tile does not have a crop");
        else if(tile.getCrop().getIsHarvestable()) {
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

            if (tile.getCrop().getCropType().equals("Flower"))
                finalHarvestPrice *= 1.1;

            System.out.println("You harvested the crop and earned " + finalHarvestPrice + " ObjectCoins.");
            tile.resetStats();
            increaseObjectCoins(finalHarvestPrice);
        } else {
            System.out.println("You cannot harvest this crop.");
        }
    }

    /**
     * This method plants a seed on the tile in the farm, provided that the player has enough money,
     * that the tile is plowed, that the tile does not have rocks, and that the tile has no current crop.
     * 
     * @param tile - the tile to be planted upon
     * @param farm - the farm containing the tile
     */
    public void plantSeed (Tile tile, Farm farm) {
        boolean proceed = true;
        Scanner sc = new Scanner(System.in);

        if (tile.getHasRock())
            System.out.println("There's a rock!");

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

            
            int input = sc.nextInt();
            while (input < 1 || input > 8) 
                sc.nextInt();

            double totalCost = 0;
            switch(input) {
                case 1: 
                    if (this.objectCoins < (5 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant turnip");
                        proceed = false;
                    } else
                        totalCost = 5 - this.seedCostReduction; 
                    break;
                case 2: 
                    if (this.objectCoins < (10 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant carrot");
                        proceed = false;
                    } else
                        totalCost = 10 - this.seedCostReduction;
                    break;
                case 3: 
                    if (this.objectCoins < (20 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant potato");
                        proceed = false;
                    } else
                        totalCost = 20 - this.seedCostReduction;
                    break;
                case 4: 
                    if (this.objectCoins < (5 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant rose");
                        proceed = false;
                    } else
                        totalCost = 5 - this.seedCostReduction;
                    break;
                case 5: 
                    if (this.objectCoins < (10 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant tulips");
                        proceed = false;
                    } else
                        totalCost = 10 - this.seedCostReduction;
                    break;
                case 6: 
                    if (this.objectCoins < (20 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant sunflower");
                        proceed = false;
                    } else
                        totalCost = 20 - this.seedCostReduction;
                    break;
                case 7:
                    if (farm.hasAdjacentCrop(tile)) {
                        System.out.println("You cannot plant on this tile because fruit trees need more space");
                        proceed = false;
                    } else if (this.objectCoins < (100 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant mango");
                        proceed = false;
                    } else
                        totalCost = 100 - this.seedCostReduction;
                    break;
                case 8: 
                    if (farm.hasAdjacentCrop(tile)) {
                        System.out.println("You cannot plant on this tile because fruit trees need more space");
                        proceed = false;
                    } else if (this.objectCoins < (200 - this.seedCostReduction)) {
                        System.out.println("You do not have enough money to plant apple");
                        proceed = false;
                    } else 
                        totalCost = 200 - this.seedCostReduction;
                    break;
                }

            if (proceed) {
                spendObjectCoins(totalCost);
                tile.createCrop(input);
                tile.setHasCrop(true);
                System.out.println("You have successfully planted on the tile");
            } else
                System.out.println("You could not plant a seed on this tile");
  
        } else {
            System.out.println("You could not plant a seed on this tile");
        }
        
        //sc.close();
    }

    /**
     * This method displays the current stats of the player at the start of each day
     */
    public void displayPlayerInformation () {
        System.out.println("ObjectCoins: " + this.objectCoins);
        System.out.println("Level: " + this.level);
        System.out.println("Experience: " + this.experience);
        System.out.println("Farmer Type: " + this.farmerType);
    }
    
    /**
     * This method returns the amount of objectCoins the player has
     * @return - the value of the objectCoins
     */
    public double getObjectCoins () {
        return this.objectCoins;
    }

    /**
     * This method returns the current class/farmer type of the player
     * @return
     */
    public String getFarmerType () {
        return this.farmerType;
    }
}