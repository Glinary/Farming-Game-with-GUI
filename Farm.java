import java.util.ArrayList;
import java.util.Random;
public class Farm {

    private Player player;
    private ArrayList<Tile> tileList = new ArrayList<Tile>();
    private int farmLotSize = 50;
    private boolean endGameConditionOne = false;
    private boolean endGameConditionTwo = false;
    private boolean endGameConditionThree = false;
    private int dayCount = 1;

    public Farm (int rockCount) { //TODO: algorithm for rock implementation
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

    public void goToNextDay () {
        this.endGameConditionOne = false;
        this.endGameConditionTwo = false;
        this.endGameConditionThree = false;

        //TODO: insert other things that will reset
        this.dayCount++;
    }

    public boolean checkIfGameShouldEnd () {
        boolean res = false;

        int i;
        int counter = 0,
            witheredCounter = 0;

        //check endGameConditionOne
        for (i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getHasCrop())
                counter++;
        }
        if (counter == 0)
            this.endGameConditionOne = true;
        // check endGameConditionThree
        if (counter == 50)  {
            for (i = 0; i < tileList.size(); i++) {
                if (tileList.get(i).getCrop().getIsWithered())
                    witheredCounter++;
            }
        }
        if (witheredCounter == 50)
            this.endGameConditionThree = true;

        //check endGameConditionTwo
        if (player.getFarmerType().equals("Farmer")) {
            if (player.getObjectcoins() < 5)
                this.endGameConditionTwo = true;
        } else if (player.getFarmerType().equals("Registered Farmer")) {
            if (player.getObjectcoins() < 4)
                this.endGameConditionTwo = true;
        } else if (player.getFarmerType().equals("Distinguished Farmer")) {
            if (player.getObjectcoins() < 3)
                this.endGameConditionTwo = true;
        } else if (player.getFarmerType().equals("Legendary Farmer")) {
            if (player.getObjectcoins() < 2)
                this.endGameConditionTwo = true;
        }

        if (this.endGameConditionOne &&
            this.endGameConditionTwo &&
            this.endGameConditionThree) {
                res = true;
            }

        return res;
    }

    public void resetGame () {
        int i;

        this.endGameConditionOne = false;
        this.endGameConditionTwo = false;
        this.endGameConditionThree = false;

        //TODO: insert other things that will reset
        player.resetPlayerStats();
        
        for (i = 0; i < this.farmLotSize; i++) {
            tileList.get(i).resetStats();
        }
        this.dayCount = 1;
    }

    public void displayFarmInformation () {
        System.out.println("Day " + this.dayCount);
    }

    public Player getPlayer () {
        return this.player;
    }

    public Tile getTile (int index) {
        return this.tileList.get(index);
    }

}