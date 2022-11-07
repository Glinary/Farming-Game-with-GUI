public class Player {
    private Inventory inventory;
    private int money = 100;
    private int level = 0;
    private double experience = 0.0;

    public Player () {
        this.inventory = new Inventory();
    }

    public void harvestCrop (Tile tile) {
        tile.setIsPlowed(false);

        this.experience += 5;
    }

    public void addMoney(int income) {
        this.money += income;
    }

    public void spendMoney(int price) {
        this.money -= price;
    }

    public void addExperience(double gainedExperience) {
        this.experience += gainedExperience;
    }

    public void levelUp() {
        this.level++;
    }

    public int getMoney() {
        return this.money;
    }

    public int getLevel() {
        return this.level;
    }

    public double getExperience() {
        return this.experience;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void resetPlayer() {
        this.money = 100;
        this.experience = 0.0;
        this.level = 0;
    }
}
