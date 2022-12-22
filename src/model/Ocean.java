package model;

import utils.Random;

public class Ocean {
    private final int numRows;
    private final int numCols;
    public Random rand;
    private Cell[][] cells;

    public Ocean(int rows, int cols, int numObs, int numPreds, int numPrey) {
        rand = new Random();
        numRows = rows;
        numCols = cols;
        cells = new Cell[numRows][numCols];
        fillOcean(numObs, numPreds, numPrey);
    }

    private void fillOcean(int numObs, int numPreds, int numPrey) {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                cells[r][c] = new Cell(new Coordinate(r, c), this);
            }
        }

        while (numObs > 0) {
            int x = rand.nextIntBetween(0, numRows - 1);
            int y = rand.nextIntBetween(0, numCols - 1);
            if (cells[x][y].getImage() == '-') {
                cells[x][y] = new Obstacle(new Coordinate(x, y), this);
                numObs--;
            }
        }

        while (numPreds > 0) {
            int x = rand.nextIntBetween(0, numRows - 1);
            int y = rand.nextIntBetween(0, numCols - 1);
            if (cells[x][y].getImage() == '-') {
                cells[x][y] = new Predator(new Coordinate(x, y), this);
                numPreds--;
            }
        }

        while (numPrey > 0) {
            int x = rand.nextIntBetween(0, numRows - 1);
            int y = rand.nextIntBetween(0, numCols - 1);
            if (cells[x][y].getImage() == '-') {
                cells[x][y] = new Prey(new Coordinate(x, y), this);
                numPrey--;
            }
        }
    }

        public Ocean(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cells = new Cell[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cells[i][j] = new Cell(new Coordinate(j, i), this);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, Coordinate coord) {
        cells[coord.getX()][coord.getY()] = cell;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}