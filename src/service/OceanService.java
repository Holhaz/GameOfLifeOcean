package service;

import model.Cell;
import model.Ocean;

public class OceanService {
    Ocean ocean;
    public void process() {
        for (int i = 0; i < ocean.getNumRows(); i++) {
            for (int j = 0; j < ocean.getNumCols(); j++) {
                ocean.getCell(i, j).process();
            }
        }
    }

    public void init (int rows, int cols, int numObs, int numPreds, int numPrey){
        ocean = new Ocean(rows, cols, numObs, numPreds, numPrey);
    }

    public int getNumRows() {
        return ocean.getNumRows();
    }

    public int getNumCols() {
        return ocean.getNumCols();
    }

    public Cell getCell(int i, int j) {
        return ocean.getCell(i, j);
    }
}
