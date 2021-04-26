package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.IconImg;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteUI;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.UIImg;
import be.ac.umons.bernardhofmanshouba.Save.ProfileList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ProfileScene extends SceneTool{

    public static GridPane root = new GridPane();

    public static void makeScene(){
        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

        topCenterGenesis(root);
        topLeftGenesis(root);
        topRightGenesis(root);
        centerGenesis(root);

        root.setGridLinesVisible(false);

        Scene scene = new Scene(root);
        SceneList.PROFILE.setScene(scene);
    }

    public static void topLeftGenesis(GridPane root){
        HBox exitBox = new HBox();

        Button exitButton = makeExitButton();
        exitButton.setScaleY(1);
        exitButton.setScaleX(1);
        exitButton.setTranslateY(15);
        exitButton.setTranslateX(15);
        exitButton.setOnAction(event ->{
            SceneList.MENU.setOnActive();
        });

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(640, 150);
        root.add(exitBox, 0, 0);
    }

    public static void topCenterGenesis(GridPane root){
        HBox titleBox = new HBox();

        Label title = new Label("Select Profile");
        title.setFont(Font.font("impact", 50));
        title.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(640, 150);

        root.add(titleBox, 1, 0);
    }

    public static void topRightGenesis(GridPane root){
        HBox statBox = new HBox();

        Button statsButton = new Button();
        statsButton.setGraphic(SpriteIcon.getIconImg(IconImg.STAT));
        statsButton.setBackground(new Background(bgFillDarkOrange));
        statsButton.setStyle("-fx-padding: 10, 10, 10, 10 ; -fx-cursor: hand;");
        statsButton.setTranslateX(475);
        statsButton.setTranslateY(15);

        statsButton.setOnAction(event -> SceneList.STATS.setOnActive());

        statBox.getChildren().add(statsButton);
        statBox.setPrefSize(640, 150);

        root.add(statBox, 2, 0);
    }

    public static void centerGenesis(GridPane root){
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
        profilesContainer.setTranslateX(50);
        profilesContainer.setGridLinesVisible(false);
        profilesContainer.setPrefSize(640, 780);

        root.add(profilesContainer, 1, 1);

    }

}
class ProfileButton extends StackPane {

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
                SceneTool.SceneList.GAME_MODE.setOnActive();
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
