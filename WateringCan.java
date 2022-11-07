
public class WateringCan {
    

    public WateringCan() {

    }

    public boolean waterTile(Tile tile) {
        if(tile.getPlowStatus() == true) {
            tile.setIsWatered(true);
            return true;
        } else {
            System.out.println("The tile is not plowed.");
            return false;
        }
            
    }
}
