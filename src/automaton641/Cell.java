package automaton641;

import java.util.Random;

public class Cell {
    public Cell(Random random) {
        this.random = random;
    }
    private Random random;
    public int value;
    public void randomize(int modulus) {
        value = random.nextInt(modulus);
    }
}
