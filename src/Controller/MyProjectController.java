package Controller;

import model.Cell;
import model.Ocean;
import service.OceanService;
import utils.Random;
import model.MyProjectData;
import utils.Constants;

public class MyProjectController {
    private OceanService service;
    private Random rand;

    public MyProjectController(OceanService service) {
        service.init(Constants.MAX_ROWS, Constants.MAX_COLS, 25, 30, 50);
        rand = new Random();
        this.service = service;
    }

    public void reset() {
        service.init(Constants.MAX_ROWS, Constants.MAX_COLS, 25, 30, 50);
    }

    public MyProjectData iterate() {
        service.process();
        return getData();
    }

    private MyProjectData getData() {
        int predator = 0;
        int prey = 0;
        int[][] oceanData = new int[service.getNumRows()][service.getNumCols()];

        for (int i = 0; i < service.getNumRows(); i++) {
            for (int j = 0; j < service.getNumCols(); j++) {
                Cell cell = service.getCell(i, j);
                oceanData[i][j] = cell.getType();

                if (cell.getType() == cell.PREDATOR) {
                    predator++;
                } else if (cell.getType() == cell.PREY) {
                    prey++;
                }
            }
        }

        return new MyProjectData(predator, prey, oceanData);
    }
}
