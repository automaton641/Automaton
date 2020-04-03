package automaton641;

import java.util.Random;

public class Automaton {
    public Automaton(int width, int height, int modulus) {
        this.width = width;
        this.height = height;
        this.modulus = modulus;
        random = new Random(0);
        initializeCells();
    }
    public Random random;
    public Cell[][] cells;
    public Cell[][] backCells;
    public int modulus;
    public int width;
    public int height;
    public void initializeCells() {
        cells = new Cell[height][width];
        backCells = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                cells[row][column] = new Cell(random);
                backCells[row][column] = new Cell(random);
            }
        }
        cells[height/2][width/2].value = modulus - 1;
        fillBackCells();
    }
    public void fillBackCells() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                backCells[row][column].value = cells[row][column].value;
            }
        }
    }
    public int getLeftValue(int row, int column) {
        if (column == 0) {
            column = width - 1;
        } else {
            column--;
        }
        return backCells[row][column].value;
    }
    public int getUpValue(int row, int column) {
        if (row == 0) {
            row = height - 1;
        } else {
            row--;
        }
        return backCells[row][column].value;
    }
    public int getRightValue(int row, int column) {
        if (column == width - 1) {
            column = 0;
        } else {
            column++;
        }
        return backCells[row][column].value;
    }
    public int getDownValue(int row, int column) {
        if (row == height - 1) {
            row = 0;
        } else {
            row++;
        }
        return backCells[row][column].value;
    }
    public int react(int row, int column) {
        int leftValue = getLeftValue(row, column);
        //System.out.println(leftValue);
        int upValue = getUpValue(row, column);
        int rightValue = getRightValue(row, column);
        int downValue = getDownValue(row, column);
        int value = backCells[row][column].value;
        value = (value + leftValue) % modulus;
        value = (value + upValue) % modulus;
        value = (value + rightValue) % modulus;
        value = (value + downValue) % modulus;
        return value;
    }
    public void iterate() {
        fillBackCells();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                cells[row][column].value = react(row, column);
            }
        }
    }
    public void print() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.println("[" + row + ", " + column + "] = " + cells[row][column].value);
            }
        }
    }
}
