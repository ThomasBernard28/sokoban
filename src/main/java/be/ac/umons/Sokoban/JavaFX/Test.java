package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application {

    private static Image IMAGE;

    private static final int COLUMNS  =   3;
    private static final int COUNT    =  16;
    private static final int OFFSET_X =  3;
    private static final int OFFSET_Y =  7;
    private static final int WIDTH    = 64;
    private static final int HEIGHT   = 64;

    private static final int SIZE = 8;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage){
        theStage.setTitle("Sokoban");

        Grid logicGrid = logicGridGenesis(8);

        Image sprite = new Image("images/sokoban_tilesheet.png");

        SpecialPane gamePane = new SpecialPane(logicGrid, 64, sprite);

        Scene gameScene = new Scene(gamePane);

        theStage.setScene(gameScene);
        SpriteAnimation anim = new SpriteAnimation(Duration.millis(1000), 8, 1,
                gamePane.SIZE, gamePane.SIZE, gamePane);

        gamePane.initiate();
        anim.setDirection(Direction.LEFT);
        anim.play();


        ImageView imageView = new ImageView(sprite);

        // animation.setCycleCount(Animation.INDEFINITE);
        // animation.play();
        // gamePane.translation(gamePane.getCell(ImageType.PLAYER), 1, 0, 64);


        theStage.show();
    }
    public Grid logicGridGenesis(int size){
        Grid grid = new Grid(size, size);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
        return grid;
    }
}
