package model;

import utils.Constants;

public class Cell {
    protected Ocean Viewer = null;
    protected Coordinate offset;
    protected char image;
    public static final int PREDATOR = 2;
    public static final int PREY = 1;
    public static final int OBSTACLE = 3;
    public static final int EMPTY = 0;

    public Cell(Coordinate aCord, Ocean l) {
        Viewer = l;
        offset = new Coordinate(aCord);
        image = Constants.DEFAULT_IMAGE;
    }

    public Cell() {
    }

    protected Cell getNeighborWithImage(char anImage) {
        Cell[] neighbors = new Cell[4];
        int count = 0;
        if (north().getImage() == anImage) {
            neighbors[count++] = north();
        }
        if (south().getImage() == anImage) {
            neighbors[count++] = south();
        }
        if (east().getImage() == anImage) {
            neighbors[count++] = east();
        }
        if (west().getImage() == anImage) {
            neighbors[count++] = west();
        }
        if (count == 0) {
            return this;
        } else {
            return neighbors[Viewer.rand.nextIntBetween(0, count - 1)];
        }
    }

    protected void assignCellAt(Coordinate aCord, Cell aCell) {
        Viewer.setCell(aCell, aCord);
    }

    protected Coordinate getEmptyNeighborCoord() {
        return getNeighborWithImage(Constants.DEFAULT_IMAGE).getOffset();
    }

    protected Coordinate getPreyNeighborCoord() {
        return getNeighborWithImage(Constants.DEFAULT_PREY_IMAGE).getOffset();
    }

    protected Cell north() {
        int yValue;
        yValue = (offset.getX() > 0) ? (offset.getX() - 1) : (Viewer.getNumRows() - 1);
        return Viewer.getCell(yValue, offset.getY());
    }

    protected Cell south() {
        int yValue;
        yValue = (offset.getX() + 1) % Viewer.getNumRows();
        return Viewer.getCell(yValue, offset.getY());
    }

    protected Cell east() {
        int xValue;
        xValue = (offset.getY() + 1) % Viewer.getNumCols();
        return Viewer.getCell(offset.getX(), xValue);
    }

    protected Cell west() {
        int xValue;
        xValue = (offset.getY() > 0) ? (offset.getY() - 1) : (Viewer.getNumCols() - 1);
        return Viewer.getCell(offset.getX(), xValue);
    }

    protected Cell reproduce(Coordinate anOffset) {
        return new Cell(anOffset, this.Viewer);
    }

    public Coordinate getOffset() {
        return offset;
    }

    public void setOffset(Coordinate anOffset) {
        offset = anOffset;
    }

    public char getImage() {
        return image;
    }

    public void display() {
        System.out.print(image);
    }

    public void process() {
    }
    public int getType() {
        if (image == Constants.DEFAULT_PRED_IMAGE) {
            return PREDATOR;
        } else if (image == Constants.DEFAULT_PREY_IMAGE) {
            return PREY;
        } else if (image == Constants.OBSTACLE_IMAGE) {
            return OBSTACLE;
        } else {
            return EMPTY;
        }
    }
}