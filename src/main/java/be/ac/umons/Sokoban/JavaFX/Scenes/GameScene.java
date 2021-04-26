package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Event.PlayerEvent;
import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.MapGeneration.Grid;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import be.ac.umons.Sokoban.Save.ProfileList;
import be.ac.umons.Sokoban.Save.Save;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class GameScene extends SceneTool{

    public static GridPane root = null;

    private static Grid currentGrid = null;
    private static PlayerEvent playerEvent = null;
    protected static String currFileName = null;
    protected static Path currPath = null;

    private static ProfileList currProfile = null;

    public final static ArrayList<String> movements = new ArrayList<>();
    private static TextField nbrMov;

    public static void makeScene(Grid grid){
        root = new GridPane();
        Scene scene = new Scene(root);
        SceneList.GAME2.setScene(scene);

        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

        root.add(topLeftGenesis(), 0, 0);
        root.add(topCenterGenesis(), 1, 0);
        root.add(topRightGenesis(), 2,0);
        root.add(centerGenesis(grid), 1, 1, 2, 1);
    }

    public static HBox topLeftGenesis(){
        HBox exitBox = new HBox();

        Button exitButton = makeExitButton();
        exitButton.setScaleY(1);
        exitButton.setScaleX(1);
        exitButton.setTranslateY(15);
        exitButton.setTranslateX(15);
        exitButton.setOnAction(event ->{
            switch (GameScene.currPath) {
                case LVL:
                    SceneList.LVL_SELECTION.setOnActive();
                    break;
                case SAVE:
                    SceneList.PLAY_MENU.setOnActive();
                    break;
                default:
                    throw new IllegalStateException("The file doesn't come from an correct path");
            }
        });

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(690, 200);
        return exitBox;
    }

    public static HBox topCenterGenesis(){
        HBox titleBox = new HBox();

        Label title = new Label("Sokoban");
        title.setFont(Font.font("impact", 50));
        title.setTextFill(Color.web("gold"));
        title.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(540, 200);
        return titleBox;

    }

    public static VBox topRightGenesis(){
        //contains both Hboxes.
        VBox elements = new VBox(5);

        // contains the textfield for saving and save button
        HBox savingBox = new HBox();

        TextField fileOutput = new TextField();
        fileOutput.setFont(new Font("arial", 28));
        fileOutput.setScaleY(1.2);

        Button save = new Button();
        save.setGraphic(SpriteIcon.getIconImg(IconImg.SAVE));
        save.setBackground(new Background(bgFillGray));
        save.setStyle("-fx-padding: 10, 10, 10, 10; -fx-cursor: hand; ");

        save.setOnAction(event -> {
            CharSequence output = fileOutput.getCharacters();
            System.out.println(fileOutput.isFocused());
            // https://regex101.com/
            if (Pattern.matches("^(\\w|_)+$", output)){
                try{
                    Save.saving(currentGrid, Path.SAVE, output.toString());
                    fileOutput.clear();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        });

        savingBox.getChildren().addAll(fileOutput, save);
        savingBox.setAlignment(Pos.CENTER);


        // otherBox contains history, mov counter and restart button
        HBox otherBox = new HBox(60);

        StackPane movBox = new StackPane();
        nbrMov = new TextField("Movements : 0");
        nbrMov.setFont(Font.font("impact", 18));
        nbrMov.setEditable(false);
        nbrMov.setFocusTraversable(false);
        nbrMov.setBackground(new Background(bgFillYellow));
        nbrMov.setPrefHeight(42);
        nbrMov.setPrefWidth(220);
        movBox.setScaleX(1.5);
        movBox.setScaleY(1.6);
        movBox.getChildren().add(nbrMov);
        movBox.setAlignment(Pos.CENTER);

        Button restartGame = new Button();
        restartGame.setScaleX(1);
        restartGame.setScaleY(1);
        restartGame.setGraphic(SpriteIcon.getIconImg(IconImg.RESTART));
        restartGame.setBackground(new Background(bgFillDarkOrange));
        restartGame.setStyle("-fx-cursor: hand; -fx-padding: 10, 10, 10, 10;");

        Button history = new Button("History");
        history.setScaleX(2);
        history.setScaleY(1.5);
        history.setBackground(new Background(bgFillRed));
        history.setMaxHeight(45);
        history.setMaxWidth(100);
        history.setStyle("-fx-cursor: hand; -fx-padding: 10, 10, 10, 10;");
        history.setTranslateX(-35);



        restartGame.setOnAction(event -> {
            GameScene.loadLevel(GameScene.currPath, GameScene.currFileName);
        });

        history.setOnAction(event -> {
            new PopupWindow(PopupWindow.PopupType.HISTORY);
        });

        otherBox.getChildren().addAll(history, movBox, restartGame);
        otherBox.setAlignment(Pos.CENTER);
        otherBox.setTranslateX(-25);

        elements.getChildren().addAll(savingBox, otherBox);
        elements.setPrefSize(690, 200);
        elements.setAlignment(Pos.CENTER_RIGHT);
        elements.setTranslateX(30);


        return elements;

    }

    public static VBox centerGenesis(Grid grid){
        VBox V_ROOT = new VBox();

        GamePane gamePane = new GamePane(grid);
        currentGrid = grid;
        gamePane.initiate();
        gamePane.setOnMouseClicked( event -> gamePane.requestFocus() );

        V_ROOT.getChildren().add(gamePane);
        V_ROOT.setPrefSize(1280, 720);
        V_ROOT.setAlignment(Pos.CENTER);
        V_ROOT.setTranslateX(-350);

        playerEvent = new PlayerEvent(gamePane);
        SceneList.GAME2.getScene().addEventHandler(KeyEvent.KEY_PRESSED, playerEvent);

        return V_ROOT;
    }

    public static void victory(){
        if(WINDOW.getScene() == SceneList.GAME2.getScene()){
            if (currPath == Path.LVL){
                if(currFileName.startsWith("10", 5)){
                    currProfile.setBestMov(movements.size(), 10);
                }
                else{
                    currProfile.setBestMov(movements.size(), Integer.parseInt(currFileName.substring(5,6)));
                }

            }
            currProfile.incrementLvlCompleted();
            new PopupWindow(PopupWindow.PopupType.END_GAME);
        }
    }

    public static void loadLevel(Path path, String fileName){
        movements.clear();
        currFileName = fileName;
        currPath = path;
        try{
            Grid gameFile = Load.loadFile(path, fileName);
            GameScene.makeScene(gameFile);
            SceneList.GAME2.setOnActive();
            System.out.println(gameFile.getPlayerX() + "," + gameFile.getPlayerY());

        }catch (IOException e){
            throw new IllegalStateException("File is missing");
        }
    }

    protected static void resetScene(){
        currentGrid = null;
        playerEvent = null;
        currFileName = null;
        currPath = null;
        movements.clear();
        nbrMov.clear();
    }

    public static void history(String letters){
        movements.add(letters);
        nbrMov.setText("Movements : "+ movements.size());
    }

    public static ProfileList getCurrProfile() {
        return currProfile;
    }

    public static void setCurrProfile(ProfileList currProfile) {
        GameScene.currProfile = currProfile;
    }

}

