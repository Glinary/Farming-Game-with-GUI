/**
 * This class represents a tile object that contains properties related to its plow, rock, and crop status;
 */

public class Tile {
    private boolean isPlowed;
    private boolean hasRock;
    private boolean hasCrop;
    private Crop crop;

    /**
     * This constructor currently does not set any attributes of the tile object to a default value
     */
    public Tile () {

    }

    /**
     * This method returns true if the tile has been plowed
     * 
     * @return - a boolean value related to the plow status of the tile
     */
    public boolean getIsPlowed () {
        return this.isPlowed;
    }

    /**
     * This method returns true if the tile has a rock
     * 
     * @return - a boolean value related to the rock status of the tile
     */
    public boolean getHasRock () {
        return this.hasRock;
    }

    /**
     * This method returns true if the tile contains a crop
     * 
     * @return - a boolean value related to the crop status of the tile
     */
    public boolean getHasCrop () {
        return this.hasCrop;
    }

    /**
     * This method sets the plow status of the tile based on the given value
     * 
     * @param status - the boolean value that will be set for the plow status of the object
     */
    public void setIsPlowed (boolean status) {
        this.isPlowed = status;
    }

    /**
     * This method sets the rock status of the tile based on the given value
     * 
     * @param status - the boolean value that will be set for the rock status of the object
     */
    public void setHasRock (boolean status) {
        this.hasRock = status;
    }

    /**
     * This method sets the crop status of the tile based on the given value
     * 
     * @param status - the boolean value that will be set for the crop status of the object
     */
    public void setHasCrop (boolean status) {
        this.hasCrop = status;
    }

    /**
     * This method resets the stats of the tile object back to its default values
     */
    public void resetStats () {
        this.isPlowed = false;
        this.hasRock = false;
        this.hasCrop = false;
        this.crop = null;
    }

    /**
     * This method returns the crop contained inside the tile object
     * 
     * @return - a crop object
     */
    public Crop getCrop() {
        return this.crop;
    }

    /**
     * This method creates a crop object inside the tile depending on the number value accepted by its parameter
     * 
     * @param num - the value that corresponds to the chosen crop type of the player
     */
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