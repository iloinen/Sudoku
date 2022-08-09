package app.sudoku.services;

import app.sudoku.models.SudokuTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final TableGenerator tableGenerator;
    private final DisplayService displayService;

    @Autowired
    public GameService(TableGenerator tableGenerator, DisplayService displayService) {
        this.tableGenerator = tableGenerator;
        this.displayService = displayService;
    }

    public SudokuTable getNewTable() {
        SudokuTable table = tableGenerator.generateNewTable();

        displayService.setTableForGame(table);

        return table;
    }

}
