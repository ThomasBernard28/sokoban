package be.ac.umons.Sokoban.JavaFX.Scenes;


import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.stage.Popup;

import java.awt.event.MouseEvent;


public class ProfileScene extends SceneTool {
    private static final BorderPane root = new BorderPane();

    public static void makeScene(){
        root.setTop(topGenesis());
        root.setBottom(bottomGenesis());
        root.setLeft(leftGenesis());
        root.setRight(rightGenesis());
        root.setCenter(centerGenesis());

        Scene scene = new Scene(root);
        root.setBackground(new Background(bgFillLightBlue));
        SceneList.PROFILE.setScene(scene);
    }

    public static HBox topGenesis(){
        HBox topSide = new HBox();
        HBox titleBox = new HBox();
        HBox statsBox = new HBox();

        Label title = new Label("Select Profile");
        title.setFont(Font.font("impact", 70));

        Button exitButton = makeExitButton();
        exitButton.setScaleY(1.0);
        exitButton.setScaleX(1.0);
        exitButton.setOnAction(event ->
                SceneList.MENU.setOnActive());

        Button statsButton = new Button();
        statsButton.setGraphic(SpriteIcon.getIconImg(IconImg.STAT));
        statsButton.setBackground(new Background(bgFillDarkOrange));
        statsButton.setStyle("-fx-padding: 10, 10, 10, 10 ; -fx-cursor: hand;");

        //TODO SET ACTION FOR STAT BUTTON

        titleBox.getChildren().addAll(exitButton, title);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setSpacing(50);

        statsBox.getChildren().add(statsButton);
        statsBox.setAlignment(Pos.BASELINE_RIGHT);

        topSide.getChildren().addAll(titleBox, statsBox);
        topSide.setStyle("-fx-padding: 70, 50, 20, 50");
        topSide.setSpacing(800);

        return topSide;
    }
    public static VBox centerGenesis(){
        VBox centerSide = new VBox();
        VBox profilesBox = new VBox();
        VBox deleteBox = new VBox();

        StackPane newProfile1Button = new StackPane();
        StackPane profile1Button = new StackPane();
        StackPane newProfile2Button = new StackPane();
        StackPane profile2Button = new StackPane();
        StackPane newProfile3Button = new StackPane();
        StackPane profile3Button = new StackPane();
        Button deleteProfile = new Button();

        deleteProfile.setBackground(new Background(bgFillDarkOrange));
        deleteProfile.setGraphic(SpriteIcon.getIconImg(IconImg.RESET));

        newProfile1Button.setScaleX(2);
        newProfile1Button.setScaleY(2);
        newProfile2Button.setScaleX(2);
        newProfile2Button.setScaleY(2);
        newProfile3Button.setScaleX(2);
        newProfile3Button.setScaleY(2);
        profile1Button.setScaleX(2);
        profile1Button.setScaleY(2);
        profile2Button.setScaleX(2);
        profile2Button.setScaleY(2);
        profile3Button.setScaleY(2);
        profile3Button.setScaleY(2);
        deleteProfile.setScaleX(1);
        deleteProfile.setScaleY(1);

        ImageView profile1ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView profile2ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView profile3ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);

        Label newProfile1Text = new Label("New profile");
        Label newProfile2Text = new Label("New profile");
        Label newProfile3Text = new Label("New profile");
        newProfile1Text.setFont(Font.font("impact", 30));
        newProfile2Text.setFont(Font.font("impact", 30));
        newProfile3Text.setFont(Font.font("impact", 30));


        newProfile1Button.getChildren().addAll(profile1ButtonImg, newProfile1Text);
        newProfile2Button.getChildren().addAll(profile2ButtonImg, newProfile2Text);
        newProfile3Button.getChildren().addAll(profile3ButtonImg, newProfile3Text);

        newProfile1Button.setStyle("-fx-cursor: hand;");
        newProfile2Button.setStyle("-fx-cursor: hand;");
        newProfile3Button.setStyle("-fx-cursor: hand;");

        profile1Button.setStyle("-fx-cursor: hand;");
        profile2Button.setStyle("-fx-cursor: hand;");
        profile3Button.setStyle("-fx-cursor: hand;");

        Background unpressed = new Background(bgFillDarkOrange);
        Background pressed = new Background(bgFillGray);

        deleteProfile.setOnMouseClicked(event -> {
            deleteProfile.setBackground(pressed);
            newProfile1Button.setOnMouseClicked(event1 -> {
                deleteProfile.setBackground(unpressed);
                new PopupWindow(PopupWindow.PopupType.DELETE_PROFILE);
            });

        });
        newProfile1Button.setOnMouseClicked(event -> {
            new PopupWindow(PopupWindow.PopupType.NEW_PROFILE);
            profile1Button.getChildren().addAll(createProfile("Profile 1"), profile1ButtonImg);
            profilesBox.getChildren().set(0, profile1Button);
        });

        newProfile2Button.setOnMouseClicked(event -> {
            //TODO
        });
        newProfile2Button.setOnMouseClicked(event -> {
            //TODO
        });

        deleteBox.getChildren().add(deleteProfile);
        deleteBox.setAlignment(Pos.CENTER);
        deleteProfile.setTranslateX(150);

        profilesBox.getChildren().addAll(newProfile1Button, newProfile2Button, newProfile3Button);
        profilesBox.setSpacing(100);
        profilesBox.setAlignment(Pos.CENTER);

        centerSide.getChildren().addAll(deleteBox, profilesBox);
        centerSide.setSpacing(50);
        centerSide.setAlignment(Pos.CENTER);

        return centerSide;
    }
    public static HBox bottomGenesis(){
        HBox bottomSide = new HBox();

        bottomSide.setMinHeight(50);

        return bottomSide;
    }
    public static VBox leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(50);

        return leftSide;
    }
    public static  VBox rightGenesis(){
        VBox rightSide = new VBox();

        rightSide.setMinWidth(50);

        return rightSide;
    }
    public static Label createProfile(String name){
        Label profileName = new Label("name");
        return profileName;
    }

}