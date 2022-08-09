package app.sudoku.services;

import app.sudoku.models.NumberField;
import app.sudoku.models.SudokuTable;
import org.springframework.stereotype.Service;

@Service
public class TableGenerator {

    public SudokuTable generateNewTable() {
        SudokuTable table = fillWithNumbers();

        randomize(table);

        return table;
    }

    private SudokuTable fillWithNumbers() {
        NumberField[][] fields = new NumberField[9][9];

        int value;

        for (int i = 0; i < fields.length; i++) {
            value = countStarterValue(i, fields);

            for (int j = 0; j < fields[i].length; j++) {
                if (value > 9) {
                    value = 1;
                }
                fields[i][j] = new NumberField(value);
                value++;
            }
        }

        return new SudokuTable(fields);
    }

    private int countStarterValue(int i, NumberField[][] fields) {
        if (i % 3 == 0) {
            return i / 3 + 1;
        }

        return fields[i - 1][0].getNumber() + 3;
    }

    private void randomize(SudokuTable table) {
        for (int i = 0; i < 2; i++) {
            swapCols(table.getNumberFields());
            swapRows(table.getNumberFields());
        }
    }

    private void swapCols(NumberField[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            int increaseValue = (int) (Math.random() * 2 + 1);
            int swapWith = (i % 3) + 3 * increaseValue;

            if (swapWith != i) {
                swapCols(fields, i, swapWith);
            }
        }
    }

    private void swapCols(NumberField[][] fields, int base, int swapWith) {
        for (int i = 0; i < fields.length; i++) {
            NumberField temp = fields[i][base];
            fields[i][base] = fields[i][swapWith];
            fields[i][swapWith] = temp;
        }
    }

    private void swapRows(NumberField[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            int increaseValue = (int) (Math.random() * 3);
            int swapWith = i / 3 * 3 + increaseValue;

            if (swapWith != i) {
                swapRows(fields, i, swapWith);
            }
        }
    }

    private void swapRows(NumberField[][] fields, int base, int swapWith) {
        NumberField[] temp = fields[base];
        fields[base] = fields[swapWith];
        fields[swapWith] = temp;
    }

}
