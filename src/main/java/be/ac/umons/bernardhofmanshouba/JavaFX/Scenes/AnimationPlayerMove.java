package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.Entities.Direction;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteTile;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.TileImg;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimationPlayerMove extends Transition {
    private final int count = 8;
    private final int column = 3;
    private int offsetX;
    private int offsetY;

    private final GamePane gamePane;
    private Direction dir;

    private boolean boxAnim;
    private boolean running = false;

    private int lastIndex;

    public AnimationPlayerMove(GamePane gamePane, Duration duration){
        this.gamePane = gamePane;

        setDirection(Direction.DOWN);

        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double k) {
        this.running = true;
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % column) * SpriteTile.getSize()  + offsetX * SpriteTile.getSize();
            final int y = offsetY * SpriteTile.getSize();

            ImageView cell = new ImageView(SpriteTile.getGameSheet());
            cell.setViewport(new Rectangle2D(x, y, SpriteTile.getSize(), SpriteTile.getSize()));

            final int length = SpriteTile.getSize() - (SpriteTile.getSize()/ count) * (index + 1);

            gamePane.translation(cell, dir, length, boxAnim);
            lastIndex = index;
            if(index == count - 1){
                //last step
                if(boxAnim && gamePane.getGridFromPlayer(dir).isFlaggedBox()){
                    gamePane.setTileBg(gamePane.getPlayerX() + dir.x, gamePane.getPlayerY() + dir.y);
                    gamePane.setAt(
                            SpriteTile.getTileImg(TileImg.FLAGGED_BOX),
                            gamePane.getPlayerX() + dir.x,
                            gamePane.getPlayerY() +dir.y
                    );
                }
                this.running = false;
                this.stop();
            }

        }
    }



    public void setDirection(Direction direction){
        this.dir = direction;
        switch (dir){
            case UP:
                offsetX = 3;
                offsetY = 5;
                break;
            case DOWN:
                offsetX = 0;
                offsetY = 5;
                break;
            case LEFT:
                offsetX = 3;
                offsetY = 7;
                break;
            case RIGHT:
                offsetX = 0;
                offsetY = 7;
                break;
        }

    }

    public void setAnimation(boolean box){
        boxAnim = box;
    }
    public boolean isNotRunning(){
        return !running;
    }
}
