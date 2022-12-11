import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a farm object. It contains the player object, the farmLot tiles,
 * three condition checkers to end the game, and a day count for the progress of the game.
 */
public class Farm {
    private Player player;
    private ArrayList<Tile> tileList = new ArrayList<Tile>();
    private int farmLotSize = 50;
    private boolean endGameConditionOne = false;
    private boolean endGameConditionTwo = false;
    private boolean endGameConditionThree = false;
    private int dayCount = 1;

    /**
     * This constructor creates the player object, contains a rand variable to for generating the rocks,
     * contains a counter for how many rocks are currently in the farm, and a lot number representing a tile.
     * 
     * @param rockCount - is the number of rocks currently scattered at the farm lot;
     */
    public Farm (int rockCount) {
        Random rand = new Random();
        int i;
        int rocksPlaced = 0;
        int lotNumber;
        
        player = new Player();
        for(i = 0; i < this.farmLotSize; i++) {
            tileList.add(new Tile());
        }

        while(rocksPlaced < rockCount) {
            lotNumber = rand.nextInt(this.farmLotSize);
            if(tileList.get(lotNumber).getHasRock() == false) {
                tileList.get(lotNumber).setHasRock(true);
                rocksPlaced++;
            }
        }
    }

    /**
     * This method sets the attributes that need to be reset or updated when player decides to sleep
     * and proceed to the next day.
     */
    public void goToNextDay () {
        int i;

        this.endGameConditionOne = false;
        this.endGameConditionTwo = false;
        this.endGameConditionThree = false;

        this.dayCount++;

        //updates a crop's amount of days left to be harvestable
        for (i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getHasCrop()) {
                tileList.get(i).getCrop().subtractDaysLeft();
            }
        }
        //updates a crop as withered if it was not harvested on harvest day
        for (i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getHasCrop()) {

                tileList.get(i).getCrop().witherIfNotHarvestedOnHarvestDay();
            }
        }

        //withers a crop that does not meet requirements on harvest day
        for (i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getHasCrop()) {
                tileList.get(i).getCrop().witherIfUnhealthyOnHarvestDay();
            }
        }

        //updates a crop to be harvestable right after the day count is updated
        for (i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getHasCrop()) {
                tileList.get(i).getCrop().checkIfHarvestable();
            }
        }
    }

    /**
     * This method checks if the game should end by checking three game conditions
     * @return - a boolean value responsible for continuing or ending the game
     */
    public boolean checkIfGameShouldEnd () {
        boolean res = false;

        int i;
        int counter = 0,
            witheredCounter = 0;

        //check endGameConditionOne - if there are any active/growing crops
        for (i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getHasCrop()) {
                if (!tileList.get(i).getCrop().getIsWithered())
                    counter++;
                else
                    witheredCounter++;
            }
        }
        
        if (counter == 0)
            this.endGameConditionOne = true;
        else 
            this.endGameConditionOne = false;

        // check endGameConditionThree - if all tiles have withered crops
        if (witheredCounter == 50)
            this.endGameConditionThree = true;
        else
            this.endGameConditionThree = false;

        //check endGameConditionTwo if player does not have enough money to buy a seed
        if (player.getFarmerType().equals("Farmer")) {
            if (player.getObjectCoins() < 5)
                this.endGameConditionTwo = true;
        } else if (player.getFarmerType().equals("Registered Farmer")) {
            if (player.getObjectCoins() < 4)
                this.endGameConditionTwo = true;
        } else if (player.getFarmerType().equals("Distinguished Farmer")) {
            if (player.getObjectCoins() < 3)
                this.endGameConditionTwo = true;
        } else if (player.getFarmerType().equals("Legendary Farmer")) {
            if (player.getObjectCoins() < 2)
                this.endGameConditionTwo = true;
        } else
            this.endGameConditionTwo = false;

        if (this.endGameConditionOne && this.endGameConditionTwo)
            res = true;
        
        if (this.endGameConditionThree)
            res = true;

        return res;
    }

    /**
     * This method resets all stats of the game once the player decides to play again
     */
    public void resetGame () {
        int i;

        this.endGameConditionOne = false;
        this.endGameConditionTwo = false;
        this.endGameConditionThree = false;

        player.resetPlayerStats();
        
        for (i = 0; i < this.farmLotSize; i++) {
            tileList.get(i).resetStats();
        }
        this.dayCount = 1;
    }

    /**
     * This method displays what day it is inside the game
     */
    public void displayFarmInformation () {
        System.out.println("Day " + this.dayCount);
    }

    /**
     * This method returns the player object contained inside the farm
     * @return - a player object
     */
    public Player getPlayer () {
        return this.player;
    }

    /**
     * This method returns the tile object corresponding to the index
     * 
     * @param index - the index of the tile from the toolList ArrayList
     * @return - a tile object
     */
    public Tile getTile (int index) {
        return this.tileList.get(index);
    }

    /**
     * This method determines if the tile has enough space for a fruit tree to be planted on
     * 
     * @param tile - the tile who's surroundings will be checked
     * @return - a boolean value to let the game know if the fruit tree to be planted meets its space requirements
     */
    public boolean hasAdjacentCrop (Tile tile) {
        int index = tileList.indexOf(tile);
        boolean result = false;

        //check if left edge
        if (index % 5 == 0)
            result = true;
        //check if right edge
        else if (index % 5 == 4)
            result = true;
        //check if bottom edge
        else if (index >= 45 && index <= 49)
            result = true;
        //check if top edge
        else if (index >= 0 && index <= 4)
            result = true;
        else {
            //check left
            if (tileList.get(index-1).getHasCrop() || tileList.get(index-1).getHasRock())
                result = true;
            //check right
            if (tileList.get(index+1).getHasCrop() || tileList.get(index+1).getHasRock())
                result = true;
            //check below
            if (tileList.get(index+5).getHasCrop() || tileList.get(index+5).getHasRock())
                result = true;
            //check lower diagonal left
            if (tileList.get(index+4).getHasCrop() || tileList.get(index+4).getHasRock())
                result = true;
            //check lower diagonal right
            if (tileList.get(index+6).getHasCrop() || tileList.get(index+6).getHasRock())
                result = true;
            //check above
            if (tileList.get(index-5).getHasCrop() || tileList.get(index-5).getHasRock())
                result = true;
            //check upper diagonal left
            if (tileList.get(index-6).getHasCrop() || tileList.get(index-6).getHasRock())
                result = true;
            //check upper diagonal right
            if (tileList.get(index-4).getHasCrop() || tileList.get(index-4).getHasRock())
                result = true;
        }

        return result;
    }

    /**
    * This gets the day count inside the farming game
    *
    * @return - the number of days spent in the game
    */
    public int getDayCount() {
        return this.dayCount;
    }
}