package app.sudoku.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberFieldTest {

    @Test
    void numberField_equals() {
        NumberField numberField = new NumberField(42);

        assertEquals(numberField, new NumberField(42));
        assertEquals(numberField, 42);

        assertNotEquals(numberField, new NumberField(1));
        assertNotEquals(numberField, 4242);
        assertNotEquals(numberField, "text");
    }

}