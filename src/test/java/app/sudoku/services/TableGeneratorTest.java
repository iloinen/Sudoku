package app.sudoku.services;

import app.sudoku.models.NumberField;
import app.sudoku.models.SudokuTable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TableGeneratorTest {

    static TableGenerator generator;
    static int[][] basicTable;

    @BeforeAll
    static void setup() {
        generator = new TableGenerator();
        basicTable = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
    }

    @Test
    void test_fillWithNumbers() throws NoSuchMethodException {
        Method fillMethod = TableGenerator.class.getDeclaredMethod("fillWithNumbers");

        Object returnObject = invokeMethod(fillMethod, generator);

        if (returnObject != null) {
            SudokuTable testTable = (SudokuTable) returnObject;
            int[][] testNumbers = convertNumberFieldsToInt(testTable.getNumberFields());

            assertEquals(Arrays.deepToString(basicTable), Arrays.deepToString(testNumbers));
        }
    }

    @Test
    void test_randomize() throws NoSuchMethodException {
        Method fillMethod = TableGenerator.class.getDeclaredMethod("fillWithNumbers");
        Method randomizeMethod = TableGenerator.class.getDeclaredMethod("randomize", SudokuTable.class);

        Object returnObject = invokeMethod(fillMethod, generator);

        if (returnObject != null) {
            SudokuTable testTable = (SudokuTable) returnObject;
            invokeMethod(randomizeMethod, generator, testTable);

            int[][] testNumbers = convertNumberFieldsToInt(testTable.getNumberFields());

            assertNotEquals(Arrays.deepToString(basicTable), Arrays.deepToString(testNumbers));

            assertRowsCorrect(testNumbers);
            assertColsCorrect(testNumbers);
            assertBlocksCorrect(testNumbers);
        }
    }

    private void assertRowsCorrect(int[][] testNumbers) {
        for (int[] row : testNumbers) {
            assertTrue(isRowCorrect(row));
        }
    }

    private void assertColsCorrect(int[][] testNumbers) {
        for (int i = 0; i < testNumbers.length; i++) {
            assertTrue(isColCorrect(testNumbers, i));
        }
    }

    private void assertBlocksCorrect(int[][] testNumbers) {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                assertTrue(isBlockCorrect(testNumbers, i, j));
            }
        }
    }

    private boolean isRowCorrect(int[] testRow) {
        int[] numbersCounter = new int[9];

        for (int elem : testRow) {
            numbersCounter[elem - 1] += 1;
        }

        return doesNumbersRepresentOnce(numbersCounter);
    }

    private boolean isColCorrect(int[][] testNumbers, int index) {
        int[] numberCounter = new int[9];

        for (int[] row : testNumbers) {
            int elem = row[index];
            numberCounter[elem - 1] += 1;
        }

        return doesNumbersRepresentOnce(numberCounter);
    }

    private boolean isBlockCorrect(int[][] testNumbers, int row, int col) {
        int[] numbersCounter = new int[9];

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                int elem = testNumbers[i][j];
                numbersCounter[elem - 1] += 1;
            }
        }

        return doesNumbersRepresentOnce(numbersCounter);
    }

    private boolean doesNumbersRepresentOnce(int[] numbersCounter) {
        for (int counter : numbersCounter) {
            if (counter != 1) {
                return false;
            }
        }

        return true;
    }

    private int[][] convertNumberFieldsToInt(NumberField[][] fields) {
        int[][] matrix = new int[9][9];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = fields[i][j].getNumber();
            }
        }

        return matrix;
    }

    private Object invokeMethod(Method method, Object obj, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}