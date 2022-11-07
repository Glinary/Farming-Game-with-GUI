public class Tile {
    private boolean isPlowed = false;
    private boolean isPlanted = false;
    private boolean isWatered = false;
    private Turnip turnip;

    public Tile() {
        
    }

    public void plantSeed(String name, Player player) {

        //check if money is enough
        switch (name) {
            case "Turnip":
                if (player.getMoney() >= 5) {
                    if (this.isPlanted == false) {
                        this.isPlanted = true;
                        this.turnip = new Turnip();

                        //spend money
                        
                    } else {
                        System.out.println("A seed is already planted in this tile.");
                    }
                }
                break;
            default: 
                System.out.println("Invalid");
                break;
        }
    }

    public void harvestPlant() {
        this.isPlowed = false;
        this.isPlanted = false;
    }

    public Turnip getTurnip() {
        return this.turnip;
    }

    public boolean getPlantStatus() {
        return this.isPlanted;
    }

    public void setPlantStatus(boolean status) {
        this.isPlanted = status;
    }

    public boolean getPlowStatus() {
        return this.isPlowed;
    }

    public boolean getIsWatered() {
        return this.isWatered;
    }

    public void setIsWatered(boolean status) {
        this.isWatered = status;
    }

    public void setIsPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }
    
}
