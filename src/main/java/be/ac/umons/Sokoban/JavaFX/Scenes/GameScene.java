package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.MapGeneration.Grid;
import be.ac.umons.Sokoban.JavaFX.Event.PlayerEvent;
import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import be.ac.umons.Sokoban.Save.Save;
import be.ac.umons.Sokoban.Stats.Profile;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class GameScene extends SceneTool {
    private static Grid currentGrid = null;
    private static PlayerEvent playerEvent = null;
    protected static String currFileName = null;
    protected static Path currPath = null;

    private static Profile currProfile = null;

    public static ArrayList<String> movements = new ArrayList();
    private static TextField nbrMov;

    public static void makeScene(){
        VBox V_ROOT = new VBox();
        HBox root = new HBox(V_ROOT);

        Scene scene = new Scene(root);
        SceneList.GAME.setScene(scene);

        V_ROOT.getChildren().addAll(topRowGenesis(), bottomRowGenesis(currentGrid));

        V_ROOT.setStyle("-fx-padding: 0 100 100 100");
        V_ROOT.setSpacing(100);

        root.setBackground(new Background(bgFillLightBlue));
        root.setAlignment(Pos.CENTER);


        //root.setStyle("-fx-border-width: 5;-fx-border-color: red");



    }
    public static void makeScene(Grid grid){
        VBox V_ROOT = new VBox();
        HBox root = new HBox(V_ROOT);

        Scene scene = new Scene(root);
        SceneList.GAME.setScene(scene);

        V_ROOT.getChildren().addAll(topRowGenesis(), bottomRowGenesis(grid));

        V_ROOT.setStyle("-fx-padding: 0 100 100 100");
        V_ROOT.setSpacing(100);

        root.setBackground(new Background(bgFillLightBlue));
        root.setAlignment(Pos.CENTER);


        //root.setStyle("-fx-border-width: 5;-fx-border-color: red");



    }
    private static GamePane bottomRowGenesis(Grid grid){
        // TODO change the resize factor of initiate method
        GamePane gamePane = new GamePane(grid);
        currentGrid = grid;
        gamePane.initiate();

        gamePane.setOnMouseClicked(event -> {
            gamePane.requestFocus();
        });

        playerEvent = new PlayerEvent(gamePane);
        SceneList.GAME.getScene().addEventHandler(KeyEvent.KEY_PRESSED, playerEvent);
        return gamePane;
    }
/*
    protected static GamePane bottomRowGenesis(Size size){
        // TODO change the resize factor of initiate method
        Grid logicGrid = new Grid(size);
        logicGrid.set_default_walls();
        logicGrid.set_player(1,1);
        logicGrid.set_boxes(logicGrid.getSize().getCol()/2, logicGrid.getSize().getRow()/2);
        logicGrid.set_flag((logicGrid.getSize().getCol()/2) + 1, (logicGrid.getSize().getRow()/2) + 1);

        Grid test = null;
        try{
            test = Load.loadFile(Path.LVL,"level1_0");
        }catch (IOException e){
            e.printStackTrace();
        }

        currentGrid = test;
        GamePane gamePane = new GamePane(test);
        gamePane.initiate();
        playerEvent = new PlayerEvent(gamePane);
        SceneList.GAME.getScene().addEventHandler(KeyEvent.KEY_PRESSED, playerEvent);
        return gamePane;
    }
*/
    private static HBox topRowGenesis(){
        HBox topSide = new HBox();
        HBox titleBox = new HBox();
        HBox saveBox = new HBox();
        HBox others = new HBox();
        VBox topRightBox = new VBox(5);


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

        history.setOnAction(event -> {
            new PopupWindow(PopupWindow.PopupType.HISTORY);
        });


        //TODO this label will display the current lvl playing
        Label title = new Label("Sokoban");

        title.setFont(Font.font("impact", 50));
        title.setStyle("-fx-padding: 20 20 20 20;");

        TextField fileOutput = new TextField();
        fileOutput.setFont(new Font("arial", 28));
        fileOutput.setScaleY(1.2);

        Button save = new Button();
        save.setGraphic(SpriteIcon.getIconImg(IconImg.SAVE));
        save.setBackground(new Background(bgFillGray));
        save.setStyle("-fx-padding: 10, 10, 10, 10; -fx-cursor: hand; ");

        restartGame.setOnAction(event -> {
            GameScene.makeTheGame(GameScene.currPath, GameScene.currFileName);
        });

        Button exitButton = makeExitButton();
        exitButton.setOnAction(event -> {
            switch (GameScene.currPath){
                case LVL:
                    SceneList.LVL_SELECTION.setOnActive();
                    break;
                case SAVE:
                    SceneList.PLAY_MENU.setOnActive();
                    break;
                default: throw new IllegalStateException("The file doesn't come from an correct path");
            }
        });
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);

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

        topSide.setStyle("-fx-padding: 70 50 20 50");

        others.getChildren().addAll(history, movBox, restartGame);
        others.setAlignment(Pos.CENTER);
        others.setSpacing(60);
        others.setTranslateX(-25);

        topRightBox.getChildren().addAll(saveBox, others);

        titleBox.getChildren().addAll(exitButton, title);
        titleBox.setSpacing(20);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        saveBox.getChildren().addAll(fileOutput, save);
        saveBox.setAlignment(Pos.CENTER);
        topSide.getChildren().addAll(titleBox,topRightBox);
        topSide.setSpacing(400);

        return topSide;

    }
    public static GamePane createGamePane(Grid gameFile){
        GamePane gamePane = new GamePane(gameFile);
        gamePane.initiate();

        return gamePane;
    }
    public static void victory(){
        if(WINDOW.getScene() == SceneList.GAME.getScene()){
            if (currPath == Path.LVL){
                if(currFileName.startsWith("10", 5)){
                    currProfile.setBestMov(movements.size(), 10);
                }
                else{
                    currProfile.setBestMov(movements.size(), Integer.parseInt(currFileName.substring(5,6)));
                }

            }
            new PopupWindow(PopupWindow.PopupType.END_GAME);
        }
    }
    public static void makeTheGame(Path path, String fileName){
        movements.clear();
        currFileName = fileName;
        currPath = path;

        try{
            Grid gameFile = Load.loadFile(path, fileName);
            GameScene.makeScene(gameFile);
            SceneList.GAME.setOnActive();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void history(String letters){
        movements.add(letters);
        nbrMov.setText("Movements : "+ movements.size());
    }

    public static Profile getCurrProfile() {
        return currProfile;
    }

    public static void setCurrProfile(Profile currProfile) {
        GameScene.currProfile = currProfile;
    }

}
