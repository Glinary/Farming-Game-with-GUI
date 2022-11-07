public class Farm {
    private Tile tile;
    private int day = 1;

    public Farm() {
        this.tile = new Tile();
    }

    public void nextDay() {
        this.day += 1;
        if(this.tile.getPlantStatus() == true) {
            this.tile.getTurnip().subtractDaysLeft();
            this.tile.setIsWatered(false);
        }

    }
    public Tile getTile() {
        return this.tile;
    }

    public void resetGame() {
        //reset tile condition
        this.tile = new Tile();

        //reset number of days
        this.day = 1;
    }

    public int getDay() {
        return this.day;
    }

}
