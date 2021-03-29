package be.ac.umons.Sokoban.Entities;

public class Flag implements immovableInterface
{
    @Override
    public Load getNature()
    {
        return Load.FLAG;
    }
}
