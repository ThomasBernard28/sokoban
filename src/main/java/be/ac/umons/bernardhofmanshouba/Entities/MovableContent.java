package be.ac.umons.bernardhofmanshouba.Entities;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;

public enum MovableContent {
    PLAYER,
    BOX,
    EMPTY;

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
