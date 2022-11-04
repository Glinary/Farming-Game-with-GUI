public class Product {
    private String type;
    private int quantity = 0;
    private int sellingPrice;

    public Product(String type, int sellingPrice) {
        this.type = type;
        this.sellingPrice = sellingPrice;
    }

    public Product(String type, int quantity, int sellingPrice) {
        this.type = type;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }

    public String getType() {
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getsellingPrice() {
        return this.sellingPrice;
    }

    public void changeQuantity(int change) {
        this.quantity += change;
    }
}
