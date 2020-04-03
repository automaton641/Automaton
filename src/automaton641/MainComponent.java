package automaton641;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainComponent extends JComponent {
    public int cellSize;
    public Automaton automaton;
    public BufferedImage bufferedImage;
    public Color[] colors;
    public int getImageWidth() {
        return bufferedImage.getWidth();
    }
    public int getImageHeight() {
        return bufferedImage.getHeight();
    }

    public void drawAutomaton() {
        for (int row = 0; row < automaton.height; row++) {
            for (int column = 0; column < automaton.width; column++) {
                drawCell(row, column);
            }
        }
        repaint();
    }

    public void calculateColors() {
        int modulus = automaton.modulus;
        colors = new Color[modulus];
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;
        float accumulator  = 0.0f;
        float step = 3.0f / (float) (modulus - 1);
        for (int colorIndex = 0; colorIndex < modulus; colorIndex++) {
            if (accumulator > 3.1f) {
                System.out.println("PANIC " + accumulator);
                System.exit(0);
            } else if (accumulator > 3.0f) {
                accumulator = 3.0f;
            }
            if (accumulator > 2.0f) {
                blue = 1.0f;
                red = 1.0f;
                green = accumulator - 2.0f;
            } else if (accumulator > 1.0f) {
                blue = 1.0f;
                red = accumulator - 1.0f;
                green = 0.0f;
            } else {
                blue = accumulator;
                red = 0.0f;
                green = 0.0f;
            }
            colors[colorIndex] = new Color(red, green, blue);
            accumulator += step;
        }
    }

    public void drawCell(int row, int column) {
        int value = automaton.cells[row][column].value;
        Color color = colors[value];
        for (int y = row * cellSize; y < row * cellSize + cellSize; y++) {
            for (int x = column * cellSize; x < column * cellSize + cellSize; x++) {
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
    }
    public MainComponent(Automaton automaton, int cellSize) {
        this.automaton = automaton;
        this.cellSize = cellSize;
        bufferedImage = new BufferedImage(automaton.width *cellSize, automaton.height *cellSize, BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < bufferedImage.getHeight(); row++) {
            for (int column = 0; column < bufferedImage.getWidth(); column++) {
                bufferedImage.setRGB(column, row, Color.BLACK.getRGB());
            }
        }
        calculateColors();
        setPreferredSize(new Dimension(automaton.width *cellSize, automaton.height *cellSize));
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this);
    }
}
