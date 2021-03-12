package be.ac.umons.Sokoban.Entities;

public class Wall implements immovableInterface {
    @Override
    public char getNature() {
        return 'w';
    }
}
