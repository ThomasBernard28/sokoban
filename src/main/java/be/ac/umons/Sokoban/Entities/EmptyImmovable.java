package be.ac.umons.Sokoban.Entities;

public class EmptyImmovable implements immovableInterface
{
    @Override
    public TileType getNature() {
        return TileType.EMPTY;
    }
}
