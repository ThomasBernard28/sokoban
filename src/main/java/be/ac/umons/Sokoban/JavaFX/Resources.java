package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.Image;

public class Resources {
    private Image player;
    private Image box;
    private Image flag;
    private Image wall;
    private Image bg;
    private Image boxFlagged;

    public void loadImages(){
        player = new Image("images/player.png");
        box = new Image("images/box.png");
        flag = new Image("images/flag.png");
        wall = new Image("images/wall.png");
        bg = new Image("images/bg_40.png");
        boxFlagged = new Image("images/box_flagged.png");
    }

    public Image getPlayer(){
        return player;
    }
    public Image getBg() {
        return bg;
    }
    public Image getBox() {
        return box;
    }
    public Image getFlag() {
        return flag;
    }
    public Image getWall() {
        return wall;
    }
    public Image getBoxFlagged() {
        return boxFlagged;
    }
}
