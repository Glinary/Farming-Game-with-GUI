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

        while(isEnd == false) {
            if(player.getExperience() % 100 != player.getLevel()) {
                player.levelUp();
            }
            
            //ask player if they want to continue
            System.out.println("Proceed to next day?");
            System.out.println("Enter [Y/y]es or [N/n]o");

            answer = sc.nextLine();
            if (answer == "N" || answer == "n") {
                isEnd = true;

                //check if there are still seeds (condition1)

                //check active crops (condition2)
                if (myFarm.getTile().getPlantStatus())
                    condition2 = true;

                //check if enough money (condition3)
                if (player.getMoney() <= 0)
                    condition3 = true;

                if (condition1 && condition2 && condition3) 
                    isEnd = true;
            } else {
                myFarm.nextDay();       
            }
            
        }
        sc.close();
    }
}