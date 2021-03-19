package be.ac.umons.Sokoban.Test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class MyWindow extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    //Method to create an image and ;oad it with absolute path
    public static Image createImage(Object context, String resourceName)
    {
        URL _url = context.getClass().getResource(resourceName);
        return new Image(_url.toExternalForm());
    }


    public void start(Stage theStage)
    {
        theStage.setTitle("Sokoban");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(1280, 720);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        // The comments below are the previous method
        //File file = new File("/Users/thomasbernard/IdeaProjects/sokoban/src/main/resources/images/player.png");
        //String localUrl = file.toURI().toString();
        //Image mario = new Image(localUrl);
        Image mario = new Image("images/player.png");

        gc.drawImage(mario, 620, 340);

        theStage.show();
    }
}