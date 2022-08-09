package app.sudoku.controllers;

import app.sudoku.models.SudokuTable;
import app.sudoku.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = {"/game"})
    public String getNewBoard(Model model) {
        SudokuTable table = gameService.getNewTable();

        model.addAttribute("table", table);

        return "game";
    }

}


