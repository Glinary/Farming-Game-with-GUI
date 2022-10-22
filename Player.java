public class Player {
    private Inventory inventory;
    private int money;
    private int level = 0;
    private double experience = 0.0;
    private int productCount;

    public Player () {
        this.inventory = new Inventory();
    }

    public void harvestCrop (Tile tile) {
        tile.setIsPlowed(false);

        this.experience += 5;
    }

    public int getMoney() {
        return this.money;
    }

    public int getLevel() {
        return this.level;
    }

    public void spendMoney(int price) {
        this.money -= price;
    }

    public void addInventoryProduct(Turnip turnip) {
        inventory.storeProduct(turnip);
    }

    public double getExperience() {
        return this.experience;
    }

    public void levelUp() {
        this.level++;
        this.experience -= 100;
    }

}
