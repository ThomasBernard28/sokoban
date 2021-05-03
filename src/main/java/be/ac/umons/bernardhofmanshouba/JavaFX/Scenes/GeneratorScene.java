package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Event.LevelGenEvent;
import be.ac.umons.bernardhofmanshouba.JavaFX.Size;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteUI;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.TileImg;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.UIImg;
import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

public class GeneratorScene extends SceneTool{

    private final static EventHandler<javafx.scene.input.MouseEvent> filter = event -> event.consume();
    private static GamePane visualGrid = null;


    public static GridPane root = new GridPane();

    public static void makeScene(){
        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

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
        VBox difficultyBox = new VBox(20);

        Label difficultyText = new Label("Select a difficulty level");
        difficultyText.setFont(Font.font("impact", 35));
        difficultyText.setTextFill(Color.web("#77D04F"));

        ChoiceBox<String> difficultyChoice = new ChoiceBox<>();
        choicesBox.setScaleY(1.5);
        choicesBox.setTranslateY(50);

        //Differents difficulty levels
        difficultyChoice.getItems().addAll("Easy", "Medium", "Hard");
        //default difficulty level
        difficultyChoice.setValue("Medium");
        difficultyChoice.setScaleX(3);
        difficultyChoice.setScaleY(1.5);

        difficultyBox.getChildren().addAll(difficultyText, difficultyChoice);
        difficultyBox.setAlignment(Pos.CENTER);
        difficultyBox.setTranslateY(10);




        VBox numberOfBox = new VBox();
        TextField boxNumber = new TextField();
        boxNumber.setFont(new Font("arial", 30));
        boxNumber.setScaleX(1);
        boxNumber.setScaleY(0.8);

        Label nbBoxText = new Label("Please enter the number\n        of boxes you want");
        nbBoxText.setTextFill(Color.web("gold"));
        nbBoxText.setFont(Font.font("impact", 35));
        nbBoxText.setAlignment(Pos.CENTER);

        generateBox.setOnMouseClicked(event ->{
           GamePane toGenenerate = new GamePane(new Grid(getDifficulty(difficultyChoice)));
           visualGrid = toGenenerate;
           visualGrid.getGrid().constructMovables(getNbBoxes(boxNumber), getNbMix(getDifficulty(difficultyChoice), getNbBoxes(boxNumber)));
           visualGrid.initiateLvlGen();
           GameScene.makeScene(visualGrid.getGrid());
           SceneList.GAME.setOnActive();
        });


        numberOfBox.getChildren().addAll(nbBoxText, boxNumber);

        choicesBox.getChildren().addAll(difficultyBox, numberOfBox);
        choicesBox.setAlignment(Pos.CENTER);

        elementsBox.getChildren().addAll(choicesBox, generateBox);
        elementsBox.setAlignment(Pos.CENTER);

        elementsBox.setPrefSize(1980, 1000);
        root.add(elementsBox, 1, 1);



    }
    //Get choice of the difficultyChoice
    private static Size getDifficulty(ChoiceBox<String> difficultyChoice){
        String difficulty = difficultyChoice.getValue();

        switch (difficulty) {
            case ("Easy"):
                return Size.SMALL;
            case ("Medium"):
                return Size.MEDIUM;
            case ("Hard"):
                return Size.LARGE;
            default: throw new IllegalStateException("This is not a valid difficulty level");
        }
    }

    private static int getNbBoxes(TextField nbBox){

        if (Pattern.matches("\\d", nbBox.getCharacters())){
            return Integer.parseInt(nbBox.getCharacters().toString());

        }throw new IllegalStateException("The sequence must be integers only");

    }
    private static int getNbMix(Size currSize, int nbBox){
        //if small 50 mix per box
        //if medium 100 mix per box
        //if large 125mix per box

        switch (currSize){
            case SMALL:
                return nbBox * 50;
            case MEDIUM:
                return nbBox * 100;
            case LARGE:
                return nbBox * 125;
            default: throw new IllegalStateException("The currSize is not a valid size ");
        }
    }
}
