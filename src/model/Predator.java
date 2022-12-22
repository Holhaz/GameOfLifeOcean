package model;

import utils.Constants;

public class Predator extends Cell {
    private int timeToFeed;
    private int timeToReproduce;

    public Predator(Coordinate aCord, Ocean l) {
        super(aCord, l);
        image = Constants.DEFAULT_PRED_IMAGE;
        timeToFeed = Constants.TIME_TO_FEED;
        timeToReproduce = Constants.TIME_TO_REPRODUCE;
    }

    @Override
    protected Cell reproduce(Coordinate anOffset) {
        return new Predator(anOffset, Viewer);
    }

    @Override
    public void process() {
        // move to a prey cell if possible
        Coordinate preyCoord = getPreyNeighborCoord();

        if(timeToFeed <= 0){
            dead();
            return;
        }

        timeToFeed--;


        if (!preyCoord.equals(offset)) {
            assignCellAt(offset, new Cell(offset, Viewer));
            setOffset(preyCoord);
            assignCellAt(offset, this);
            timeToFeed = Constants.TIME_TO_FEED;
            return;
        }

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

        // decrease timeToFeed
    }

    private void dead() {
        Viewer.setCell(new Cell(offset, Viewer), offset);
    }
}