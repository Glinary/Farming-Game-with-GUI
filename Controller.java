import java.util.Scanner;
import java.io.*;

public class Controller {
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String readFromText = "";
        Farm myFarm = null;
        Tile currTile = null;
        boolean endGame = false,
                exitGame = false;
        int gameMode = -1,
            firstActionOption = -1,
            tileOption = -1,
            actionOption = -1,
            endGameOption = -1;
        System.out.println("Choose your game mode"); //differing rock scatter types
        System.out.println("[1] easy, [2] normal, [3] hard");

        while (gameMode != 1 && gameMode != 2 && gameMode != 3)
            gameMode = sc.nextInt();

        switch(gameMode) {
            case 1:
                System.out.println("You picked easy mode");
                try {
                    Scanner textScanner = new Scanner(new File("easy.txt"));
                    readFromText = textScanner.next();
                    myFarm = new Farm(Integer.parseInt(readFromText));
                } catch (Exception e) {
                    System.out.println("File not found");
                    myFarm = new Farm(0);
                }
                break;
            case 2:
                System.out.println("You picked normal mode");
                try {
                    Scanner textScanner = new Scanner(new File("normal.txt"));
                    readFromText = textScanner.next();
                    myFarm = new Farm(Integer.parseInt(readFromText));
                } catch (Exception e) {
                    System.out.println("File not found");
                    myFarm = new Farm(0);
                }
                break;
            case 3:
                System.out.println("You picked hard mode");
                try {
                    Scanner textScanner = new Scanner(new File("hard.txt"));
                    readFromText = textScanner.next();
                    myFarm = new Farm(Integer.parseInt(readFromText));
                } catch (Exception e) {
                    System.out.println("File not found");
                    myFarm = new Farm(0);
                }
                break;
        }

        //sc.close();
        //display stats
        myFarm.displayFarmInformation();
        myFarm.getPlayer().displayPlayerInformation();

        while (!exitGame) {

            while (!endGame) {
                
                System.out.println("Enter [1] to pick a tile");
                System.out.println("Enter [2] to sleep");
                
                while (firstActionOption != 1 && firstActionOption != 2)
                    firstActionOption = sc.nextInt();

                //sc.close();
                if (firstActionOption == 2) {
                    myFarm.getPlayer().checkStatusBeforeNextDay();
                    myFarm.goToNextDay();
                    //display stats
                    myFarm.displayFarmInformation();
                    myFarm.getPlayer().displayPlayerInformation();
                    
                } else {
                    System.out.println("Enter tile #");

                    
                    while (tileOption < 0 || tileOption > 49)
                        tileOption = sc.nextInt();
    
                    System.out.println("You picked tile " + tileOption);
                    currTile = myFarm.getTile(tileOption);
                    
                    
                    System.out.println("Choose an action"); //plow, plant, water, fertilize, pickaxe, shovel, harvest
                    System.out.println("Enter [0] to plow tile");
                    System.out.println("Enter [1] to plant seed");
                    System.out.println("Enter [2] to use watering can");
                    System.out.println("Enter [3] to use fertilizer");
                    System.out.println("Enter [4] to use pickaxe");
                    System.out.println("Enter [5] to use shovel");
                    System.out.println("Enter [6] to harvest crop");
        
                    while (actionOption < 0 || actionOption > 6)
                        actionOption = sc.nextInt();
        
                    switch(actionOption) {
                        case 0:
                            myFarm.getPlayer().plow(currTile);
                            break;
                        case 1:
                            myFarm.getPlayer().plantSeed(currTile, myFarm); //TODO: UPDATE UML
                            break;
                        case 2:
                            myFarm.getPlayer().water(currTile);
                            break;
                        case 3:
                            myFarm.getPlayer().fertilize(currTile);
                            break;
                        case 4:
                            myFarm.getPlayer().pickaxe(currTile);
                            break;
                        case 5:
                            myFarm.getPlayer().shovel(currTile);
                            break;
                        case 6:
                            myFarm.getPlayer().harvest(currTile);
                            break;
                    }

                    //sc.close();
                }

                gameMode = -1;
                firstActionOption = -1;
                tileOption = -1;
                actionOption = -1;
                endGameOption = -1;
                    
                //check game conditions
                endGame = myFarm.checkIfGameShouldEnd(); 
            }

            if (endGame == true) {
                System.out.println("You lost the game. Would you like to play again?");
                System.out.println("Enter [1] to play again");
                System.out.println("Enter [2] to quit the game");

                while (endGameOption != 1 && endGameOption != 2) 
                endGameOption = sc.nextInt();

                //sc.close();

                if (endGameOption == 1) {
                    System.out.println("You chose to play again");

                    //reset everything
                    myFarm.resetGame();
                    myFarm = null;
                    currTile = null;
                    endGame = false;
                    exitGame = false;
                    gameMode = -1;
                    tileOption = -1;
                    actionOption = -1;
                    endGameOption = -1;

                    System.out.println("Pick a game mode");
                    System.out.println("[1] easy, [2] normal, [3] hard");

                    while (gameMode != 1 && gameMode != 2 && gameMode != 3)
                        gameMode = sc.nextInt();

                    //sc.close();
                    switch(gameMode) {
                        case 1:
                            System.out.println("You picked easy mode");
                            myFarm = new Farm(10);
                            break;
                        case 2:
                            System.out.println("You picked normal mode");
                            myFarm = new Farm(15);
                            break;
                        case 3:
                            System.out.println("You picked hard mode");
                            myFarm = new Farm(20);
                            break;
                    }

                    //sc.close();

                    endGame = false;

                    //display stats
                    myFarm.displayFarmInformation();
                    myFarm.getPlayer().displayPlayerInformation();

                } else {
                    System.out.println("Thank you for playing");
                    exitGame = true;
                }
                    
            }
        }

       //sc.close(); 

    }
}
