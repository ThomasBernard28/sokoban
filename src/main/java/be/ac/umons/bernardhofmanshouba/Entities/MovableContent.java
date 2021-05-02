package be.ac.umons.bernardhofmanshouba.Entities;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;

/**
 * Content that can be loaded into a tile and can be moved
 */
public enum MovableContent {
    PLAYER,
    BOX,
    EMPTY;

    /**
     * Checks whether the direction selected is a valid target
     * @param logicGrid grid in which the content is
     * @param dir Direction of the movement
     * @return true if valid and false if not
     */
    public boolean checkMove(Grid logicGrid, Direction dir){
        switch(this){
            case PLAYER:
                if(logicGrid.getGridFromPlayer(dir).hasBox()){
                    return logicGrid.getGridFromPlayer(dir).checkMove(logicGrid, dir);
                }
                return !logicGrid.getGridFromPlayer(dir).isWall();
            case BOX:
                return !logicGrid.getGridFromPlayer(dir.x * 2, dir.y * 2).isWall() &&
                        !logicGrid.getGridFromPlayer(dir.x * 2, dir.y * 2).hasBox();
            default:
                throw new IllegalStateException("Unexpected enum value: " + this);
        }
    }

    /**
     * Move the content in the given direction
     * @param logicGrid grid in which the content is located
     * @param dir direction of the movement
     */
    public void move(Grid logicGrid, Direction dir){
        switch (this){
            case PLAYER:
                if(logicGrid.getGridFromPlayer(dir).hasBox()){
                    logicGrid.getGridFromPlayer(dir).move(logicGrid, dir);
                }
                logicGrid.getGridFromPlayer(dir).setMovableContent(MovableContent.PLAYER);
                logicGrid.movePlayer(dir);
                break;
            case BOX:
                logicGrid.getGridFromPlayer(dir.x * 2, dir.y * 2).setMovableContent(MovableContent.BOX);
                break;
            default:
                throw new IllegalStateException("Unexpected enum value");
            }
        }
    }
