package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteUI;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.UIImg;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class GeneratorScene extends SceneTool{

    public static GridPane root = new GridPane();

    public static void makeScene(){
        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

        root.setGridLinesVisible(true);

        topLeftGenesis(root);
        topGenesis(root);
        centerGenesis(root);

        Scene scene = new Scene(root);
        SceneList.GENERATOR.setScene(scene);
    }

    public static void topLeftGenesis(GridPane root){
        HBox exitBox = new HBox();
        ExitButton exitButton = new ExitButton(SceneList.MENU);

        exitButton.setTranslateX(15);
        exitButton.setTranslateY(15);

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(590, 150);
        root.add(exitBox, 0, 0);
    }

    public static void topGenesis(GridPane root){
        HBox titleBox = new HBox();
        HBox emptyBox = new HBox();

        Label title = new Label("Generate a Level");
        title.setFont(Font.font("impact", 70));
        title.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(740, 150);

        root.add(titleBox, 1, 0);

        emptyBox.setPrefSize(590, 150);
        root.add(emptyBox, 2, 0);
    }
    public static void centerGenesis(GridPane root){
        VBox elementsBox = new VBox(200);


        HBox choicesBox = new HBox(200);



        HBox generateBox = new HBox();
        StackPane generateButton = new StackPane();
        ImageView generateButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);
        generateButtonImg.setScaleY(2);
        generateButtonImg.setScaleX(2);


        Label generateButtonText = new Label("Generate !");
        generateButtonText.setFont(Font.font("impact", 48));


        generateButton.getChildren().addAll(generateButtonImg, generateButtonText);
        generateButton.setStyle("-fx-cursor: hand;");

        generateBox.getChildren().add(generateButton);
        generateBox.setAlignment(Pos.CENTER);


        //Box to choice the Difficulty
        VBox difficultyBox = new VBox();

        Label difficultyText = new Label("Select a difficulty level");
        difficultyText.setFont(Font.font("impact", 35));

        ChoiceBox difficultyChoice = new ChoiceBox();
        choicesBox.setScaleY(1.5);
        choicesBox.setTranslateY(50);

        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");

        difficultyChoice.getItems().addAll(easy, medium, hard);

        difficultyBox.getChildren().addAll(difficultyText, difficultyChoice);
        difficultyBox.setAlignment(Pos.CENTER);


        VBox numberOfBox = new VBox();
        TextField boxNumber = new TextField();
        boxNumber.setFont(new Font("arial", 30));
        boxNumber.setScaleX(1);

        Label nbBoxText = new Label("Please enter the number\n of boxes you want");
        nbBoxText.setFont(Font.font("impact", 35));
        nbBoxText.setAlignment(Pos.CENTER);


        numberOfBox.getChildren().addAll(nbBoxText, boxNumber);

        choicesBox.getChildren().addAll(difficultyBox, numberOfBox);
        choicesBox.setAlignment(Pos.CENTER);

        elementsBox.getChildren().addAll(choicesBox, generateBox);
        elementsBox.setAlignment(Pos.CENTER);

        elementsBox.setPrefSize(1980, 1000);
        root.add(elementsBox, 1, 1);



    }
}
