package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.IconImg;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.bernardhofmanshouba.Save.ProfileList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class StatScene extends SceneTool{

    public static GridPane root = new GridPane();

    public static void makeScene(){
        root.setMinHeight(720);
        root.setMinWidth(1280);

        topRowGenesis(root);
        centerLeftGenesis(root);
        centerGenesis(root);
        centerRightGenesis(root);

        root.setBackground(new Background(bgFillLightBlue));
        Scene scene = new Scene(root);
        SceneList.STATS.setScene(scene);
    }

    public static  void topRowGenesis(GridPane root){
        HBox empty = new HBox();
        empty.setPrefSize(640, 150);
        root.add(empty, 2,0);

        ImageView trophy  = SpriteIcon.getIconImg(IconImg.TROPHY);
        ImageView trophy2  = SpriteIcon.getIconImg(IconImg.TROPHY);

        HBox exitBox = new HBox();

        ExitButton exitButton = new ExitButton(SceneList.PROFILE);
        exitButton.setTranslateX(15);
        exitButton.setTranslateY(15);

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(640, 150);
        root.add(exitBox, 0, 0);

        HBox titleBox = new HBox();
        Label title = new Label("HALL OF FAME");
        title.setFont(Font.font("impact", 48));
        title.setTextFill(Color.web("gold"));
        title.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(trophy, title, trophy2);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(640,150);
        root.add(titleBox, 1, 0);

    }

    public static void centerLeftGenesis(GridPane root){
        VBox levels = new VBox(20);
        HBox spacing = new HBox();
        spacing.setMinHeight(75);

        levels.getChildren().add(spacing);

        for (int i = 0; i < 10; i++) {

            Label lvlName = new Label("Level " + (i + 1));
            lvlName.setFont(Font.font("impact", 40));
            lvlName.setAlignment(Pos.CENTER);

            if (i == 0 || i == 1) {
                lvlName.setTextFill(Color.web("white"));
            }
            if (i == 2 || i == 3) {
                lvlName.setTextFill(Color.web("#77D04F"));
            }
            if (i == 4 || i == 5) {
                lvlName.setTextFill(Color.web("#FFCD00"));
            }
            if (i == 6 || i == 7){
                lvlName.setTextFill(Color.web("red"));
            }
            if(i == 8 || i == 9){
                lvlName.setTextFill(Color.web("black"));
            }
            levels.getChildren().add(lvlName);
        }
        levels.setPrefSize(400,780);
        levels.setAlignment(Pos.CENTER);
        root.add(levels, 0, 1);
    }

    public static void centerGenesis(GridPane root){
        VBox profileNames = new VBox(20);
        HBox spacing = new HBox();
        spacing.setMinHeight(75);

        profileNames.getChildren().add(spacing);

        for (int i = 0; i < 10; i++){
            Label bestProfile = new Label(ProfileList.getBestPofileName(i));
            bestProfile.setFont(Font.font("impact", 40));
            bestProfile.setAlignment(Pos.CENTER);
            if (i == 0 || i == 1) {
                bestProfile.setTextFill(Color.web("white"));
            }
            if (i == 2 || i == 3) {
                bestProfile.setTextFill(Color.web("#77D04F"));
            }
            if (i == 4 || i == 5) {
                bestProfile.setTextFill(Color.web("#FFCD00"));
            }
            if (i == 6 || i == 7){
                bestProfile.setTextFill(Color.web("red"));
            }
            if(i == 8 || i == 9){
                bestProfile.setTextFill(Color.web("black"));
            }
            profileNames.getChildren().add(bestProfile);
        }
        profileNames.setPrefSize(400, 780);
        profileNames.setAlignment(Pos.CENTER);
        root.add(profileNames, 1,1);
    }

    public static void centerRightGenesis(GridPane root){
        VBox bestMoves = new VBox(20);
        HBox spacing = new HBox();
        spacing.setMinHeight(75);

        bestMoves.getChildren().add(spacing);

        for (int i = 0; i < 10; i++) {
            Label bestMov = new Label("Best Moves: " + (ProfileList.getBestMoveForLvl(i)));
            bestMov.setFont(Font.font("impact", 40));
            bestMov.setAlignment(Pos.CENTER);
            if (i == 0 || i == 1) {
                bestMov.setTextFill(Color.web("white"));
            }
            if (i == 2 || i == 3) {
                bestMov.setTextFill(Color.web("#77D04F"));
            }
            if (i == 4 || i == 5) {
                bestMov.setTextFill(Color.web("#FFCD00"));
            }
            if (i == 6 || i == 7){
                bestMov.setTextFill(Color.web("red"));
            }
            if(i == 8 || i == 9){
                bestMov.setTextFill(Color.web("black"));
            }
            bestMoves.getChildren().add(bestMov);
        }
        bestMoves.setPrefSize(400,780);
        bestMoves.setAlignment(Pos.CENTER);
        root.add(bestMoves, 2,1);
    }

}
