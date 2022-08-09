package app.sudoku.models;

public class SudokuTable {

    private final NumberField[][] numberFields;

    public SudokuTable() {
        this.numberFields = new NumberField[9][9];
    }

    public SudokuTable(NumberField[][] numberFields) {
        this.numberFields = numberFields;
    }

    public NumberField[][] getNumberFields() {
        return numberFields;
    }

}
