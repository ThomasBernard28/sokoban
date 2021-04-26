package be.ac.umons.Sokoban.JavaFX.Scenes;


import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import be.ac.umons.Sokoban.Save.ProfileList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.layout.*;


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

        exitButton.setOnAction(event -> SceneList.MENU.setOnActive());

        Button statsButton = new Button();
        statsButton.setGraphic(SpriteIcon.getIconImg(IconImg.STAT));
        statsButton.setBackground(new Background(bgFillDarkOrange));
        statsButton.setStyle("-fx-padding: 10, 10, 10, 10 ; -fx-cursor: hand;");

        statsButton.setOnAction(event -> SceneList.STATS.setOnActive());

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

    public static GridPane centerGenesis(){
        GridPane profilesContainer = new GridPane();

        ProfileButton[] profileButtons = {
                new ProfileButton(ProfileList.PROFILE1),
                new ProfileButton(ProfileList.PROFILE2),
                new ProfileButton(ProfileList.PROFILE3)
        };
        DelProfileButton[] delProfileButtons = {
                new DelProfileButton(profileButtons[0], bgFillDarkOrange),
                new DelProfileButton(profileButtons[1], bgFillDarkOrange),
                new DelProfileButton(profileButtons[2], bgFillDarkOrange)
        };

        for (int i = 0; i < profileButtons.length; i++) {
            profilesContainer.add(profileButtons[i], 0, i);
            profilesContainer.add(delProfileButtons[i], 1, i);
        }

        profilesContainer.setAlignment(Pos.CENTER);
        profilesContainer.setVgap(70);
        profilesContainer.setHgap(115);
        profilesContainer.setGridLinesVisible(false);

        return profilesContainer;
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
        return new Label("name");
    }
}

class ProfileButton extends StackPane{

    private final ProfileList linkedProfile;
    Label btnTxt;

    ProfileButton(ProfileList linkedProfile){
        super();
        this.setScaleX(2);
        this.setScaleY(2);
        this.linkedProfile = linkedProfile;
        btnTxt = new Label(linkedProfile.getProfile().getUsername());
        btnTxt.setFont(Font.font("impact", 30));

        this.getChildren().addAll(SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02), btnTxt);

        this.setStyle("-fx-cursor: hand;");

        this.setOnMouseClicked(event -> {
            if(linkedProfile.isNew()){
                GameScene.setCurrProfile(linkedProfile);
                new PopupWindow(PopupWindow.PopupType.NEW_PROFILE);
                btnTxt.setText(linkedProfile.getProfile().getUsername());
            }else{
               GameScene.setCurrProfile(linkedProfile);
               SceneTool.SceneList.PLAY_MENU.setOnActive();
            }
        });

    }

    public void delLinkedProfile() {
        ProfileList.deleteProfile(linkedProfile);
        btnTxt.setText(linkedProfile.getProfile().getUsername());
    }
}

class DelProfileButton extends Button {

    DelProfileButton(ProfileButton linkedButton, BackgroundFill bg){
        super();
        this.setBackground(new Background(bg));
        this.setGraphic(SpriteIcon.getIconImg(IconImg.RESET));
        this.setScaleX(1);
        this.setScaleY(1);
        this.setStyle("-fx-cursor: hand;");
        this.setOnAction(event -> {
            new PopupWindow(PopupWindow.PopupType.DELETE_PROFILE){
                @Override
                public void deleteAProfile(){
                    linkedButton.delLinkedProfile();
                }
            };
        });
    }
}
