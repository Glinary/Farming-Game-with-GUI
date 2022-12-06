public class Tile {
    private boolean isPlowed;
    private boolean hasRock;
    private boolean  hasCrop;
    private Crop crop;

    public Tile () {

    }

    public boolean getIsPlowed () {
        return this.isPlowed;
    }

    public boolean getHasRock () {
        return this.hasRock;
    }

    public boolean getHasCrop () {
        return this.hasCrop;
    }

    public void setIsPlowed (boolean status) {
        this.isPlowed = status;
    }

    public void setHasRock (boolean status) {
        this.hasRock = status;
    }

    public void setHasCrop (boolean status) {
        this.hasCrop = status;
    }

    public void resetStats () {
        this.isPlowed = false;
        this.hasRock = false;
        this.hasCrop = false;
        this.crop = null;
    }

    public Crop getCrop() {
        return this.crop;
    }

    public void createCrop(int num) {
        switch (num) {
            case 1:
                this.crop = new Turnip();
                break;
            case 2:
                this.crop = new Carrot();
                break;
            case 3:
                this.crop = new Potato();
                break;
            case 4:
                this.crop = new Rose();
                break;
            case 5:
                this.crop = new Tulips();
                break;
            case 6:
                this.crop = new Sunflower();
                break;
            case 7:
                this.crop = new Mango();
                break;
            case 8:
                this.crop = new Apple();
        }
    }
}