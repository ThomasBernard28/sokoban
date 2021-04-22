package be.ac.umons.Sokoban.Entities;

public enum Direction {

    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    public final int x;
    public final int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static Direction match(String mov){
        switch (mov) {
            case "z":
                return Direction.UP;
            case "q":
                return Direction.LEFT;
            case "s":
                return Direction.DOWN;
            case "d":
                return Direction.RIGHT;
        }
        return null;
    }

    public static Direction getOpposedOf(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                throw new IllegalArgumentException("Unexpected enum value: " + direction);
        }
    }
}
