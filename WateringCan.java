public class WateringCan {
    
    public WateringCan() {

    }

    public boolean waterPlant(Tile tile, Turnip turnip) {
        if(tile.getPlowStatus() == true) {
            turnip.setIsWatered(true);
            return true;
        } else {
            System.out.println("The tile is not plowed.");
            return false;
        }
            
    }
}
