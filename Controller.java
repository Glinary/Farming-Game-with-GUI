import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Player player = new Player();
        Farm myFarm = new Farm();
        boolean isEnd = false;
        Scanner sc = new Scanner(System.in);
        String answer;
        boolean condition1 = false,
                condition2 = false,
                condition3 = false;
        String option;
        int choice;
        

        while(isEnd == false) {  
            
            do {
                //display player stats
                System.out.println("Farm lot: 1x1");
                System.out.println("Objectcoins: " + player.getMoney());
                System.out.println("Level: " + player.getLevel());
                System.out.println("Experience: " + player.getExperience());
                System.out.println("Day: " + myFarm.getDay());

                //display instructions
                System.out.println("Enter [1] to plow the tile");
                System.out.println("Enter [2] to buy & plant a Turnip seed");
                System.out.println("Enter [3] to water the tile");
                System.out.println("Enter [4] to advance to next day");
                System.out.println("Enter [5] to harvest crop");

                option = sc.nextLine();
                choice = Integer.parseInt(option);

                switch (choice) {
                    case 1: //check if unplowed, then plow the tile
                        if (myFarm.getTile().getPlowStatus() != true) {
                            player.getInventory().getPlow().plowTile(myFarm.getTile());
                            player.addExperience(0.5);
                            if(player.getExperience() / 100 != player.getLevel()) {
                                player.levelUp();
                            }
                            System.out.println("You have plowed the tile.\n");
                        } else {
                            System.out.println("You already plowed this tile.\n");
                        }
                        break;
                    case 2: //buy and plant a turnip seed
                        //check if plowed, so it is able to be planted upon
                        //check also if tile is empty, so it is able to be planted upon
                        if (myFarm.getTile().getPlowStatus() && myFarm.getTile().getPlantStatus() != true) {
                            if(player.getMoney() >= 5) {
                                player.spendMoney(5);
                                myFarm.getTile().plantSeed("Turnip", player);
                                myFarm.getTile().setPlantStatus(true);   
                                System.out.println("You planted a turnip"); 
                            }
                            
                            //if planted already, dont plant
                        } else if (myFarm.getTile().getPlantStatus()){
                            System.out.println("There is already a plant.\n");
                        }
                        else {
                            System.out.println("Tile is not yet plowed, so seed cannot be planted.\n");
                        }
                        break;
                    case 3: //water the tile
                        //check if plowed, so it is able to water
                        if (myFarm.getTile().getPlowStatus()) {
                            player.getInventory().getWateringCan().waterTile(myFarm.getTile());
                            System.out.println("You used the watering can\n");
                            player.addExperience(0.5);
                            if(player.getExperience() / 100 != player.getLevel()) {
                                player.levelUp();
                            }
                            System.out.println("You watered the tile.\n");
                        } else {
                            System.out.println("Tile is not yet plowed\n");
                        }
                        break;
                    case 4: //advance to next day

                        //if planted, wither crop on harvest day if requirements are not met NOTE:recheck
                        if (myFarm.getTile().getPlantStatus()) {
                            //add water count for each day
                            if (myFarm.getTile().getIsWatered()) {
                                myFarm.getTile().getTurnip().addWaterCount();
                            }
                                
                        }
                            
                        myFarm.nextDay();
                        //check if there's a crop to be harvested
                        if (myFarm.getTile().getPlantStatus()) {
                            //check if its harvest day, and wither crop if not harvested
                            myFarm.getTile().getTurnip().checkIfWithered();

                            if (myFarm.getTile().getTurnip().getIsWithered()) {
                                System.out.println("Crop withered");
                                condition3 = true;
                            }
                            
                        }
                        break;
                    case 5: //harvest crop
                        //only run if it is planted
                        if (myFarm.getTile().getPlantStatus()) {
                            if (myFarm.getTile().getTurnip().checkIfHarvestable() && myFarm.getTile().getTurnip().getIsWithered() == false) {  
                                myFarm.getTile().harvestPlant();
        
                                player.addMoney(myFarm.getTile().getTurnip().getHarvestYield() * myFarm.getTile().getTurnip().getSellingPrice());

                                System.out.println("Your harvest produced " + myFarm.getTile().getTurnip().getHarvestYield() + " turnip(s)");
                                System.out.println("You earned " + 
                                                myFarm.getTile().getTurnip().getHarvestYield() * myFarm.getTile().getTurnip().getSellingPrice() +
                                            " objectcoins from the harvest");

                                player.addExperience(5.0);

                            } else if(myFarm.getTile().getTurnip().getIsWithered() == true){
                                System.out.println("Crop is withered\n");
                            } else {
                                System.out.println("Crop is not harvestable\n");
                            }
                        } else {
                            System.out.println("You did not plant in this tile yet.\n");
                        }
                            
                        break;
                    default:
                        System.out.println("invalid option");
                        break;
                }
            
            } while (choice != 1 && choice != 2 && choice != 3 &&
                     choice != 4 && choice != 5);

            //harvestable but water requirements not met
                //consider separating occupied and planted checker
                if (myFarm.getTile().getPlantStatus()) {
                    if (myFarm.getTile().getTurnip().checkIfHarvestable()) {
                        if (myFarm.getTile().getTurnip().getWaterCount() != myFarm.getTile().getTurnip().getMinWaterRequirement()) {
                            myFarm.getTile().getTurnip().setIsWithered(true);
                            System.out.println("Crop withered\n");
                            condition3 = true;
    
                        }
                    }
                }

            
            //check if there are no active crops (condition1)
            if (myFarm.getTile().getPlantStatus() == false)
                condition1 = true;

            //check if enough money (condition2)
            if (player.getMoney() <= 0)
                condition2 = true;

            if ((condition1 && condition2) || condition3) {
                //if game over, ask player if they want to play again
                System.out.println("Game over. Do you want to play again?");
                System.out.println("Enter [Y/y]es or [N/n]o");

                answer = sc.nextLine();

                if (answer.equals("N") || answer.equals("n"))
                    isEnd = true;
                else {
                    myFarm.resetGame();
                    player.resetPlayer();
                    condition1 = false;
                    condition2 = false;
                    condition3 = false;
                }
            } 
            
        }
        sc.close();
    }
}

/*  
    This is to certify that this project is our own work, based on our personal efforts in studying and applying the concepts learned. 
    We have constructed the functions and their respective algorithms and corresponding code by ourselves. The program was run, tested, 
    and debugged by our own efforts. We further certify that we have not copied in part or whole or otherwise plagiarized the work of other 
    students and/or persons.
*/