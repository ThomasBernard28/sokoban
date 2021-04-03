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
    private SpecialPane gP;
    private Direction dir;
    private boolean boxAnim;

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
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX * width;
            final int y = offsetY * width;

            ImageView cell = new ImageView(gP.SPRITE);
            cell.setViewport(new Rectangle2D(x, y, width, height));

            gP.translationFull(cell, dir, width - (width/ count) * (index + 1), boxAnim);
            lastIndex = index;

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
}
