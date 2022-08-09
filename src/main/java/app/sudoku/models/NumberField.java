package app.sudoku.models;

public class NumberField {

    private int number;
    private int displayNumber;
    private boolean isDarker;

    public NumberField() {}

    public NumberField(int number) {
        this.number = number;
        this.displayNumber = number;
    }

    public boolean isCorrect() {
        return (this.number == this.displayNumber);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(int displayNumber) {
        this.displayNumber = displayNumber;
    }

    public boolean isDarker() {
        return isDarker;
    }

    public void setDarker(boolean darker) {
        isDarker = darker;
    }

}
