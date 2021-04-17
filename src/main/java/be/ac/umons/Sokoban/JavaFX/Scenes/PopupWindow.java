package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import static be.ac.umons.Sokoban.JavaFX.Scenes.SceneTool.*;



public class PopupWindow {
    public enum PopupType {
        DELETE_PROFILE("DELETE PROFILE","Are you sure you want to delete this profile"),
        NEW_PROFILE("CREATE PROFILE", "Please enter a profile name"),
        END_GAME("END OF THE LEVEL", "Congrats you won ! Please make a choice");

        private  final String title;
        private  final String message;

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

    public PopupWindow(PopupType popupType){
        display(popupType);
    }

    public void display(PopupType popupType){
        Stage popupWindow = new Stage();

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

        closeButton.setOnAction(event -> popupWindow.close());

        Button yes = new Button("YES !");
        Button no = new Button("NO");

        VBox layout = new VBox(10);
        HBox choice = new HBox(50);

        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(createLayout(popupType, label));
        popupWindow.setScene(scene);
        popupWindow.showAndWait();

    }

    public VBox createLayout(PopupType popupType, Label label){
        VBox layout = new VBox(10);
        HBox choice = new HBox(50);
        TextField input = new TextField();
        input.setFont(new Font("arial", 20));
        Button closeButton = new Button();
        closeButton.setBackground(new Background(bgFillGray));
        closeButton.setGraphic(SpriteIcon.getIconImg(IconImg.EXIT));
        closeButton.setScaleX(1);
        closeButton.setScaleY(1);

        switch (popupType){
            case NEW_PROFILE:
                layout.getChildren().addAll(closeButton, input);
                return layout;
            case DELETE_PROFILE:
                break;
            case END_GAME:
                Button returnToMenu = new Button("Return to the menu");
                Button restartGame = new Button("Restart");
                restartGame.setScaleX(4);
                restartGame.setScaleY(2);
                returnToMenu.setScaleX(2);
                returnToMenu.setScaleY(2);
                returnToMenu.setBackground(new Background(bgFillGreen));
                restartGame.setBackground(new Background(bgFillDarkOrange));
                layout.setBackground(new Background(bgFillLightBlue));
                closeButton.setAlignment(Pos.TOP_RIGHT);

                returnToMenu.setOnAction(event -> {
                    SceneList.LVL_SELECTION.setOnActive();
                });
                restartGame.setOnAction(event -> {
                    SceneList.GAME.setOnActive();
                });
                choice.getChildren().addAll(restartGame, returnToMenu);
                choice.setAlignment(Pos.CENTER);
                choice.setSpacing(150);
                choice.setTranslateX(15);
                layout.getChildren().addAll(label, choice);
                layout.setAlignment(Pos.CENTER);
                layout.setSpacing(200);
                return layout;
            default: throw new IllegalStateException("Not an existing popup");
        }
        return null;
    }
}
