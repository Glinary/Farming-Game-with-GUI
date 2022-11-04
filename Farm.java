public class Farm {
    private Tile tile;
    private int day = 1;

    public Farm() {
        Tile tile = new Tile();
    }

    public void nextDay() {
        this.day++;
        if(tile.getPlantStatus() == true) {
            tile.getTurnip().subtractDaysLeft();
            tile.getTurnip().setIsWatered(false);
        }

    }
    public Tile getTile() {
        return this.tile;
    }

}
