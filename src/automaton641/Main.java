package automaton641;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Automaton");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Automaton automaton = new Automaton(60, 60, 360);
        MainComponent mainComponent = new MainComponent(automaton, 12);
        //jFrame.setSize(mainComponent.getImageWidth(), mainComponent.getImageHeight());
        jFrame.add(mainComponent);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        /*
        automaton.print();
        automaton.iterate();
        automaton.print();
        */
        while (true) {
            try {
                //automaton.print();
                mainComponent.drawAutomaton();
                automaton.iterate();
                Thread.sleep(512);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
