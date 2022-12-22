package model;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int aX, int aY) {
        x = aX;
        y = aY;
    }

    public Coordinate() {
        x = 0;
        y = 0;
    }

    public Coordinate(Coordinate aCoord) {
        x = aCoord.x;
        y = aCoord.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void assign(Coordinate aCoord) {
        x = aCoord.x;
        y = aCoord.y;
    }

    public boolean equal(Coordinate aCoord) {
        return (x == aCoord.x && y == aCoord.y);
    }

    public boolean notEqual(Coordinate aCoord) {
        return (x != aCoord.x || y != aCoord.y);
    }
}
