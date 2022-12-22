package model;

import utils.Constants;

public class Prey extends Cell {
    private int timeToReproduce;

    public Prey(Coordinate aCord, Ocean l) {
        super(aCord, l);
        image = Constants.DEFAULT_PREY_IMAGE;
        timeToReproduce = 13;
    }

    @Override
    protected Cell reproduce(Coordinate anOffset) {
        return new Prey(anOffset, Viewer);
    }

    @Override
    public void process() {
        // move to an empty cell if possible
        Coordinate emptyCoord = getEmptyNeighborCoord();
        if (!emptyCoord.equals(offset)) {
            assignCellAt(offset, new Cell(offset, Viewer));
            setOffset(emptyCoord);
            assignCellAt(offset, this);
        }

        // reproduce if timeToReproduce is 0
        if (timeToReproduce <= 0) {
            Coordinate reproductionCoord = getEmptyNeighborCoord();
            if (!reproductionCoord.equals(offset)) {
                assignCellAt(reproductionCoord, reproduce(reproductionCoord));
            }
            timeToReproduce = Constants.TIME_TO_REPRODUCE;
        } else {
            timeToReproduce--;
        }
    }
}