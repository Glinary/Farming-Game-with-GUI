public class Inventory {
    private Plow plow;
    private WateringCan wateringCan;

    public Inventory() {
        this.plow = new Plow();
        this.wateringCan = new WateringCan();
    }

    public Plow getPlow() {
        return this.plow;
    }

    public WateringCan getWateringCan() {
        return this.wateringCan;
    }

}
