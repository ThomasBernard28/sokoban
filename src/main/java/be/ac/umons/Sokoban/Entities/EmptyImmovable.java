package be.ac.umons.Sokoban.Entities;

public class EmptyImmovable implements immovableInterface
{
    @Override
    public Load getNature() {
        return Load.EMPTY;
    }
}
