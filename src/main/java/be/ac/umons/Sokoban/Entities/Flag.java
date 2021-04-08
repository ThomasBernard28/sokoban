package be.ac.umons.Sokoban.Entities;

public class Flag implements immovableInterface
{
    @Override
    public TileType getNature()
    {
        return TileType.FLAG;
    }
}
