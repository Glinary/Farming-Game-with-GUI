import java.util.ArrayList;

public class Inventory {
    private Plow plow;
    private WateringCan wateringCan;
    private ArrayList<Product> productList; 

    public Inventory() {
        this.plow = new Plow();
        this.wateringCan = new WateringCan();
        this.productList = new ArrayList<Product>();
    }

    public void storeProduct(Turnip turnip) {
        int i;
        boolean alreadyExists = false;
        for(i = 0; i < productList.size(); i++) {
            if(productList.get(i).getType().compareTo("Turnip") == 0) {
                alreadyExists = true;
                productList.get(i).changeQuantity(turnip.getHarvestYield());
            }
                
        }

        if(alreadyExists == false) {
            productList.add(new Product("Turnip", turnip.getHarvestYield(), 6));
        }
            
    }

}
