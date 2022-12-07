import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ActionMenu1 {

    static int input;
    public static int display() {
        Stage actionWindow = new Stage();

        //so it does not let the other window usable unless this menu is closed
        actionWindow.initModality(Modality.APPLICATION_MODAL);
        actionWindow.setTitle("Action Menu");
        actionWindow.setMinWidth(250);

        //buttons to choose from
        Label instructionLabel = new Label();
        instructionLabel.setText("Choose your action");
        Button plowTileButton = new Button("Plow tile");
        Button plantSeedButton = new Button("Plant seed");
        Button waterTileButton = new Button ("Water tile");
        Button fertilizeCropButton = new Button ("Fertilize crop");
        Button pickAxeButton = new Button ("Use pickaxe");
        Button shovelButton = new Button ("Use shovel");
        Button harvestCropButton = new Button ("Harvest Crop");
        Button closeButton = new Button("Cancel action");
        closeButton.setOnAction(e -> actionWindow.close());

        //when user clicks plow tile
        plowTileButton.setOnAction(e -> {
            input = 0;
            actionWindow.close();
        });
        //when user clicks plant seed
        plantSeedButton.setOnAction(e -> {
            input = 1;
            actionWindow.close();
        });
        //when user clicks water tile
        waterTileButton.setOnAction(e -> {
            input = 2;
            actionWindow.close();
        });
        //when user clicks fertilize crop
        fertilizeCropButton.setOnAction(e -> {
            input = 3;
            actionWindow.close();
        });
        //when user clicks use pickaxe
        pickAxeButton.setOnAction(e -> {
            input = 4;
            actionWindow.close();
        });
        //when user clicks use shovel
        shovelButton.setOnAction(e -> {
            input = 5;
            actionWindow.close();
        });
        //when user clicks harvest crop
        harvestCropButton.setOnAction(e -> {
            input = 6;
            actionWindow.close();
        });
        closeButton.setOnAction(e -> {
            input = -1;
            actionWindow.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(instructionLabel,
                                    plowTileButton,
                                    plantSeedButton,
                                    waterTileButton,
                                    fertilizeCropButton,
                                    pickAxeButton,
                                    shovelButton,
                                    harvestCropButton,
                                    closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        actionWindow.setScene(scene);
        actionWindow.showAndWait();

        return input;
    }
}
