package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Event.PlayerEvent;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.IconImg;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.Save.Load;
import be.ac.umons.bernardhofmanshouba.Save.Path;
import be.ac.umons.bernardhofmanshouba.Save.ProfileList;
import be.ac.umons.bernardhofmanshouba.Save.Save;
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

/**
 * This class create a scene containing a GamePane and display the current game.
 */

public class GameScene extends SceneTool{

    public static GridPane root = null;

    private static Grid currentGrid = null;
    private static PlayerEvent playerEvent = null;
    protected static String currFileName = null;
    protected static Path currPath = null;

    private static ProfileList currProfile = null;

    public final static ArrayList<String> movements = new ArrayList<>();
    private static TextField nbrMov;

    /**
     * Method to create a playable scene add different parts to and then display it
     * @param grid The Grid (game instance) we want to display
     */

    public static void makeScene(Grid grid){
        root = new GridPane();
        Scene scene = new Scene(root);
        SceneList.GAME.setScene(scene);

        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

        root.add(topLeftGenesis(), 0, 0);
        root.add(topCenterGenesis(), 1, 0);
        root.add(topRightGenesis(), 2,0);
        root.add(centerGenesis(grid), 1, 1, 2, 1);
    }

    /**
     * Method to create the top left side of the window containing
     * the Exit Button
     * @return Hbox that will be added to the Scene at top left place
     */
    public static HBox topLeftGenesis(){
        HBox exitBox = new HBox();

        ExitButton exitButton = new ExitButton();
        exitButton.setTranslateY(15);
        exitButton.setTranslateX(15);
        exitButton.setOnAction(event ->{
            switch (GameScene.currPath) {
                case LVL:
                    SceneList.LVL_SELECTION.setOnActive();
                    break;
                case SAVE:
                    SceneList.GAME_MODE.setOnActive();
                    break;
                default:
                    throw new IllegalStateException("The file doesn't come from an correct path");
            }
        });

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(690, 200);
        return exitBox;
    }

    /**
     * Method to create the top center side of the Scene containing
     * the title
     * @return Hbox that will be added to the Scene at top center place
     */
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

    /**
     * Method to create the top right side of the Scene containing
     * the Textfield used for saving the game, the restart button, the movements count and
     * the button to check the history of the game
     * @return Vbox that will be added to the Scene at top right place
     */

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
            // https://regex101.com/
            if (Pattern.matches("^(\\w|_)+$", output)){
                try{
                    Save.saving(currentGrid, Path.SAVE, output.toString() + ".xsb");
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

    /**
     * Method to create the center side of the Scene which will contain the visual
     * game instance
     * @param grid The grid (Game instance) that will be displayed
     * @return Vbox that will be added to the Scene at center place
     */

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
        SceneList.GAME.getScene().addEventHandler(KeyEvent.KEY_PRESSED, playerEvent);

        return V_ROOT;
    }

    /**
     * Method to open a popup as the game is won.
     * The method first check if it's a campaign level
     * The method also determines if there is a level to unlock and update the profiles
     * The method add the number of movements to the active profile
     */
    public static void victory(){
        //Victory is not used in SandBoxScene
        if(WINDOW.getScene() == SceneList.GAME.getScene()){
            //We want to update profile only if it's a campaign level
            if (currPath == Path.LVL){
                //Determine wich level was played
                if(currFileName.startsWith("10", 5)){
                    currProfile.setBestMov(movements.size(), 10);
                }
                else{
                    currProfile.setBestMov(movements.size(), Integer.parseInt(currFileName.substring(5,6)));
                    currProfile.incrementLvlCompleted(Integer.parseInt(currFileName.substring(5,6)));
                }
            }
            //Open the popup
            new PopupWindow(PopupWindow.PopupType.END_GAME);
        }
    }

    /**
     * Method used to load a custom level added by the user
     * @param path Generic path to the file
     * @param fileName Name of the file that has to be loaded
     */
    public static void loadLevel(Path path, String fileName){
        movements.clear();
        currFileName = fileName;
        currPath = path;
        try{
            Grid gameFile = Load.loadFile(path, fileName);
            GameScene.makeScene(gameFile);
            SceneList.GAME.setOnActive();

        }catch (IOException e){
            throw new IllegalStateException("File is missing");
        }
    }

    /**
     * Method that refresh the moves counter
     * @param letters actual number of moves displayed
     */
    public static void history(String letters){
        if(currProfile != null) {
            movements.add(letters);
            nbrMov.setText("Movements : " + movements.size());
        }
    }

    /**
     * Method to get the actual profile which is currently playing
     * @return The current profile
     */
    public static ProfileList getCurrProfile() {
        return currProfile;
    }

    /**
     * Method to set the current profile
     * @param currProfile the Profile
     */
    public static void setCurrProfile(ProfileList currProfile) {
        GameScene.currProfile = currProfile;
    }

}

