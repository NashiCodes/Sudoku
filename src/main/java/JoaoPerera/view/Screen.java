package JoaoPerera.view;

import JoaoPerera.controller.ScreenController;

import javax.swing.*;

public class Screen extends JFrame {

    public Screen() {
        super("Sudoku");
    }

    public void display() {
        setSize(300, 300);
        addWindowListener(new ScreenController(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        pack();
    }

}
