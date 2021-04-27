package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.Save.Path;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import static be.ac.umons.Sokoban.JavaFX.Scenes.SceneTool.*;

/**
 * Class to create a popup window in order to ask some choice
 * to the player or to prevent him from making some action
 */


public class PopupWindow {
    public enum PopupType {
        DELETE_PROFILE("DELETE PROFILE","Are you sure you want to delete this profile"),
        NEW_PROFILE("CREATE PROFILE", "Please enter a profile name"),
        END_GAME("END OF THE LEVEL", "Congrats you won ! Please make a choice\n You unlocked the next level"),
        HISTORY("History", "Here is your history");

        private final String title;
        private final String message;

        /**
         * Construc of a popup type in the enum
         * @param title specific title given to each type of popup
         * @param message specific message given to each type of popup
         */

        PopupType(String title, String message){
            this.title = title;
            this.message = message;
        }


        public String getTitle(){
            return title;
        }
        public String getMessage(){
            return message;
        }

    }

    /**
     * Constructor used to create a popup window th throw some advertising or question in the game
     * the constructor calls the display method in order to display the popup
     *
     * @param popupType Type of popup we will display on the screen
     */

    public PopupWindow(PopupType popupType){
        display(popupType);
    }

    /**
     * This methode takes all the parameters necessary to display a pop up
     *
     * @param popupType the popup type to display
     */

    public void display(PopupType popupType){
        Stage popupWindow = new Stage();

        //Creating the new window to display the popup
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(popupType.getTitle());
        popupWindow.setMinWidth(500);
        popupWindow.setMinHeight(400);

        Label label = new Label();
        label.setText(popupType.getMessage());
        label.setFont(Font.font("impact", 30));
        label.setStyle("-fx-color-label-visible: red;");

        Button closeButton = new Button();
        closeButton.setBackground(new Background(bgFillGray));
        closeButton.setGraphic(SpriteIcon.getIconImg(IconImg.EXIT));
        closeButton.setScaleX(1);
        closeButton.setScaleY(1);

        VBox layout = new VBox(10);
        HBox choice = new HBox(50);

        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Setting the current scene calling the next method to create specific layout
        Scene scene = new Scene(createLayout(popupType, label, popupWindow));
        popupWindow.setScene(scene);
        popupWindow.showAndWait();

    }

    /**
     * This methode initiate and return a layout that will be display on the scene
     * @param popupType the type of popup
     * @param label The message we display wich comes directly from the Popup type enum
     * @param popupWindow The stage that is create in the previous method
     * @return returning the layout to the call in display() method
     */

    public VBox createLayout(PopupType popupType, Label label, Stage popupWindow){
        VBox layout = new VBox(10);
        HBox choice = new HBox(50);
        TextField input = new TextField();
        input.setFont(new Font("arial", 20));
        Button closeButton = new Button();
        closeButton.setBackground(new Background(bgFillGray));
        closeButton.setGraphic(SpriteIcon.getIconImg(IconImg.EXIT));
        closeButton.setScaleX(0.5);
        closeButton.setScaleY(0.5);

        //Switching on the popup type to set a specific layout
        switch (popupType){
            case NEW_PROFILE:
                HBox bottomBox = new HBox(50);
                Button validate = new Button();
                validate.setGraphic(SpriteIcon.getIconImg(IconImg.VALIDATE));
                validate.setBackground(new Background(bgFillGreen));
                validate.setScaleX(1);
                validate.setScaleY(1);
                validate.setStyle("-fx-cursor: hand;");

                bottomBox.getChildren().addAll(input,validate);
                bottomBox.setAlignment(Pos.CENTER);
                input.setScaleY(1.5);
                input.setScaleX(1);

                //Closing the window
                closeButton.setTranslateX(-210);
                closeButton.setTranslateY(-95);
                closeButton.setAlignment(Pos.TOP_LEFT);
                closeButton.setOnAction(event -> {
                    popupWindow.close();
                });

                //Validate the creation of a profile
                validate.setOnAction(event -> {
                    //method to set the username entered in the textfield
                    GameScene.getCurrProfile().setUsername(input.getCharacters().toString());
                    //clearing textfield
                    input.clear();
                    //end popup
                    popupWindow.close();
                });

                layout.getChildren().addAll(closeButton,label, bottomBox);
                layout.setBackground(new Background(bgFillYellow));
                layout.setSpacing(10);
                layout.setAlignment(Pos.CENTER);

                return layout;
            case DELETE_PROFILE:
                HBox bottomBox2 = new HBox(20);
                Button validate2 = new Button();
                Button cancel = new Button();

                //close popup
                closeButton.setTranslateY(-95);
                closeButton.setTranslateX(-260);
                closeButton.setAlignment(Pos.TOP_LEFT);
                closeButton.setOnAction(event -> {
                    popupWindow.close();
                });

                validate2.setBackground(new Background(bgFillGreen));
                cancel.setBackground(new Background(bgFillRed));

                validate2.setGraphic(SpriteIcon.getIconImg(IconImg.VALIDATE));
                cancel.setGraphic(SpriteIcon.getIconImg(IconImg.CROSS));

                validate2.setStyle("-fx-cursor: hand;");
                cancel.setStyle("-fx-cursor: hand;");

                validate2.setScaleX(1);
                validate2.setScaleY(1);
                cancel.setScaleX(1);
                cancel.setScaleY(1);

                //Delete the selected profile
                validate2.setOnAction(event -> {
                    deleteAProfile();
                    popupWindow.close();
                });

                //Does nothing and comeback to profile selection
                cancel.setOnAction(event -> {
                    popupWindow.close();
                });

                bottomBox2.getChildren().addAll(validate2, cancel);
                bottomBox2.setAlignment(Pos.CENTER);

                layout.getChildren().addAll(closeButton, label, bottomBox2);
                layout.setSpacing(10);
                layout.setAlignment(Pos.CENTER);
                layout.setBackground(new Background(bgFillYellow));

                return layout;
            case END_GAME:
                Button returnToMenu = new Button();
                Button restartGame = new Button();
                restartGame.setScaleX(1.5);
                restartGame.setScaleY(1.5);
                returnToMenu.setScaleX(1.5);
                returnToMenu.setScaleY(1.5);
                returnToMenu.setBackground(new Background(bgFillRed));
                restartGame.setBackground(new Background(bgFillGreen));

                restartGame.setGraphic(SpriteIcon.getIconImg(IconImg.RESTART));
                returnToMenu.setGraphic(SpriteIcon.getIconImg(IconImg.EXIT));

                returnToMenu.setStyle("-fx-cursor: hand;");
                restartGame.setStyle("-fx-cursor: hand;");

                layout.setBackground(new Background(bgFillYellow));

                returnToMenu.setOnAction(event -> {
                    popupWindow.close();
                    //Switch made to know on wich scene we have to go back
                    switch(GameScene.currPath){
                        case LVL:
                            LevelSelectionScene.makeScene();
                            SceneList.LVL_SELECTION.setOnActive();
                            break;
                        case SAVE:
                            SceneList.PLAY_MENU.setOnActive();
                            break;
                        default: throw new IllegalStateException("The file doesn't come from an correct path");
                    }
                });
                //Restarting the game
                restartGame.setOnAction(event -> {
                    popupWindow.close();
                    GameScene.loadLevel(GameScene.currPath, GameScene.currFileName);

                });
                label.setAlignment(Pos.CENTER);
                label.setTextFill(Color.web("#1EA7E1"));

                choice.getChildren().addAll(restartGame, returnToMenu);
                choice.setAlignment(Pos.CENTER);
                choice.setSpacing(150);
                choice.setTranslateX(15);
                layout.getChildren().addAll(label, choice);
                layout.setAlignment(Pos.CENTER);
                layout.setSpacing(200);
                return layout;

            case HISTORY:
                HBox saveCopy = new HBox(30);

                Label history = new Label(GameScene.movements.toString());
                history.setFont(Font.font("lobster", 15));
                history.setWrapText(true);

                Button save = new Button();
                save.setBackground(new Background(bgFillDarkOrange));
                save.setGraphic(SpriteIcon.getIconImg(IconImg.SAVE));
                save.setScaleX(1);
                save.setScaleY(0.8);
                save.setStyle("-fx-cursor: hand; -fx-padding: 10,10,10,10");

                input.setScaleX(0.8);
                input.setScaleY(1.2);
                input.setTranslateX(30);

                Button copy = new Button("Copy !");
                copy.setBackground(new Background(bgFillGreen));
                copy.setStyle("-fx-cursor: hand; -fx-padding: 10,10,10,10");
                copy.setScaleX(2);
                copy.setScaleY(1.5);

                closeButton.setOnAction(event -> {
                    popupWindow.close();
                });

                copy.setOnAction(event -> {
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent historyToCLip = new ClipboardContent();
                    String movContent = "";
                    for (int i = 0; i < history.getText().length(); i++) {
                        if (Pattern.matches("[zqsd]", history.getText().substring(i,i+1))){
                            movContent += history.getText().substring(i, i+1);
                        }
                    }
                    historyToCLip.putString(movContent);
                    clipboard.setContent(historyToCLip);

                });

                save.setOnAction(event -> {
                    if (Pattern.matches("^(\\w|_)+$", input.getCharacters().toString())){
                        try{
                            String movContent = "";
                            for (int i = 0; i < history.getText().length(); i++) {
                                if (Pattern.matches("[zqsd]", history.getText().substring(i,i+1))){
                                    movContent += history.getText().substring(i, i+1);
                                }
                            }
                            File file = new File(Path.UNIT_TEST_IN.getPath() + input.getCharacters().toString() + ".mov");
                            boolean success = file.createNewFile();
                            if(success){
                                FileWriter writer = new FileWriter(file);
                                writer.write(movContent);
                                writer.flush();
                                writer.close();
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    input.clear();
                });

                saveCopy.getChildren().addAll(copy, input, save);
                saveCopy.setAlignment(Pos.CENTER);
                saveCopy.setTranslateY(150);
                saveCopy.setTranslateX(10);

                label.setTextFill(Color.web("black"));

                layout.setBackground(new Background(bgFillYellow));
                layout.getChildren().addAll(closeButton,label, history, saveCopy);

                popupWindow.setResizable(false);
                popupWindow.setMaxHeight(400);
                popupWindow.setMaxWidth(500);

                return layout;

            default: throw new IllegalStateException("Not an existing popup");
        }
    }

    //Method to delete a profile
    public void deleteAProfile(){
        throw new IllegalStateException("Function has not been defined");
    }
    
}
