package be.ac.umons.Sokoban.Entities;

public class Wall implements immovableInterface {
    @Override
    public Load getNature() {
        return Load.WALL;
    }
}
