public class Tile {
    private boolean isPlowed = false;
    private boolean isPlanted = false;
    private Turnip turnip;

    public Tile() {
        
    }

    public void setIsPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }

    public void plantSeed(String name, Player player) {

        //check if money is enough
        switch (name) {
            case "turnip":
                if (player.getMoney() >= 5) {
                    if (this.isPlanted == false) {
                        this.isPlanted = true;
                        this.turnip = new Turnip();
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
    public Turnip getTurnip() {
        return this.turnip;
    }

    public boolean getPlantStatus() {
        return this.isPlanted;
    }

    public boolean getPlowStatus() {
        return this.isPlowed;
    }
    
}
