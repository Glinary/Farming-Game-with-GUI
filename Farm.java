public class Farm {

    private Player player;
    private Tile[][] tileList;
    private int farmLotRow = 10;
    private int farmLotColumn = 5;
    private boolean endGameConditionOne = false;
    private boolean endGameConditionTwo = false;
    private boolean endGameConditionThree = false;
    private int dayCount = 1;

    public Farm (int rockCount, String scatterType) { //TODO: algorithm for rock implementation
        int i, j;

        Tile tile = new Tile();
        player = new Player();
        tileList = new Tile[10][5];

        for (i = 0; i < this.farmLotRow; i++) {
            for (j = 0; j < this.farmLotColumn; j++) {
                tileList[i][j] = tile;
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

        if (this.endGameConditionOne &&
            this.endGameConditionTwo &&
            this.endGameConditionThree) {
                res = true;
            }

        return res;
    }

    public void resetGame () {
        int i, j;

        this.endGameConditionOne = false;
        this.endGameConditionTwo = false;
        this.endGameConditionThree = false;

        //TODO: insert other things that will reset
        player.resetPlayerStats();
        
        for (i = 0; i < this.farmLotRow; i++) {
            for (j = 0; j < this.farmLotColumn; j++) {
                tileList[i][j].resetStats();
            }
        }
        this.dayCount = 1;
    }

    public void setEndGameConditionOne (boolean status) {
        this.endGameConditionOne = status;
    }

    public void setEndGameConditionTwo (boolean status) {
        this.endGameConditionTwo = status;
    }

    public void setEndGameConditionThree (boolean status) {
        this.endGameConditionThree = status;
    }

    public void displayFarmInformation () {
        System.out.println("Day " + this.dayCount);
    }

}