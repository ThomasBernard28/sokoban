package be.ac.umons.Sokoban.Entities;

public class Wall implements immovableInterface {
    @Override
    public TileType getNature() {
        return TileType.WALL;
    }
}
