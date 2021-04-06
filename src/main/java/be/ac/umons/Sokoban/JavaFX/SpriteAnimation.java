package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Direction;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


class SpriteAnimation extends Transition {

    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;
    private final SpecialPane gP;
    private Direction dir;
    private boolean boxAnim;
    private boolean running = false;

    private int lastIndex;


    public SpriteAnimation(Duration duration, int count,   int columns,
                           int width,   int height, SpecialPane gamePane) {
        this.count     = count;
        this.columns   = columns;
        this.width     = width;
        this.height    = height;
        this.gP = gamePane;
        this.setDirection(Direction.DOWN);
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }
    @Override
    protected void interpolate(double k) {
        this.running = true;
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX * width;
            final int y = offsetY * width;

            ImageView cell = new ImageView(gP.SPRITE);
            cell.setViewport(new Rectangle2D(x, y, width, height));

            final int length = width - (width/ count) * (index + 1);

            gP.translationFull(cell, dir, length, boxAnim);
            lastIndex = index;
            if(index == count - 1){
                //last step
                if(boxAnim){
                    gP.lastBoxPrint(dir, length);
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
