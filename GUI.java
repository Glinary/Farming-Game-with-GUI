import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
    Stage window;
    Scene menuScene, easyScene, normalScene, hardScene;
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;

        //for the scattering of rocks
        Label welcomeMessage = new Label("Choose your game mode");
        Button easyButton = new Button("Easy");
        Button normalButton = new Button("Normal");
        Button hardButton = new Button("Hard");

        easyButton.setOnAction(e -> window.setScene(easyScene));
        normalButton.setOnAction(e -> window.setScene(normalScene));
        hardButton.setOnAction(e -> window.setScene(hardScene));

        //main menu
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(welcomeMessage, easyButton, normalButton, hardButton);
        menuScene = new Scene(layout1, 200, 200);

        Button actionMenuButton = new Button("tile 1");
        actionMenuButton.setOnAction(e -> ActionMenu1.display());
        actionMenuButton.setOnAction(e -> {
            int actionMenu1Answer = ActionMenu1.display();
            switch (actionMenu1Answer) {
                case 0:
                    System.out.println("You chose to plow tile");
                    break;
                case 1:
                    System.out.println("You chose to plant seed");
                    break;
                case 2:
                    System.out.println("You chose to water tile");
                    break;
                case 3:
                    System.out.println("You chose to fertilize crop");
                    break;
                case 4:
                    System.out.println("You chose to use pickaxe");
                    break;
                case 5:
                    System.out.println("You chose to use shovel");
                    break;
                case 6:
                    System.out.println("You chose to harvest crop");
                    break;
                default:
                    System.out.println("You cancelled your action");
                    break;
            }
        });

        //easyScene from easyButton
        VBox layout2 = new VBox();
        Label playerStatsLabel = new Label("Day 1");
        layout2.getChildren().addAll(playerStatsLabel, actionMenuButton);
        easyScene = new Scene(layout2, 600, 300);


        //let's program know which scene to show first
        window.setScene(menuScene);
        window.setTitle("Farming Game Main Menu");
        window.show();
    }
}