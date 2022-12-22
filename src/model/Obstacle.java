package model;

import utils.Constants;

public class Obstacle extends Cell {
    public Obstacle(Coordinate aCoord, Ocean l) {
        super(aCoord, l);
        image = Constants.OBSTACLE_IMAGE;
    }
}
