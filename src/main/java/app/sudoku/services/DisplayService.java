package app.sudoku.services;

import app.sudoku.models.NumberField;
import app.sudoku.models.SudokuTable;
import org.springframework.stereotype.Service;

@Service
public class DisplayService {

    private int[] indexes;

    public DisplayService() {
        fillIndexes();
    }

    public void setTableForGame(SudokuTable table) {
        removeSomeDisplayNumbers(table.getNumberFields());
        setBackground(table.getNumberFields());
    }

    private void fillIndexes() {
        indexes = new int[9];

        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
    }

    private void removeSomeDisplayNumbers(NumberField[][] fields) {
        for (NumberField[] fieldRow : fields) {
            int remove = Math.random() < 0.5 ? 3 : 4;

            int[] indexes = randomIndexes();

            for (int i = 0; i < remove; i++) {
                fieldRow[indexes[i]].setDisplayNumber(0);
            }
        }
    }

    private int[] randomIndexes() {
        for (int i = 0; i < indexes.length; i++) {
            int random = (int) (Math.random() * indexes.length);
            int temp = indexes[random];
            indexes[random] = indexes[i];
            indexes[i] = temp;
        }

        return indexes;
    }

    private void setBackground(NumberField[][] fields) {
        boolean isDarker = false;

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                int index = i * fields[i].length + j;

                if (index % 3 == 0 && (index % 9 != 0 || i % 3 == 0)) {
                    isDarker = !isDarker;
                }

                fields[i][j].setDarker(isDarker);
            }
        }
    }

}
