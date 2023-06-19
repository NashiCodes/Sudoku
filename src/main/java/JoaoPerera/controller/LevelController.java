package JoaoPerera.controller;

import JoaoPerera.view.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelController implements ActionListener {

    private final Screen screen;

    public LevelController(Screen screen) {
        this.screen = screen;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Easy")) screen.easy();
        else if (e.getActionCommand().equals("Medium")) screen.medium();
        else if (e.getActionCommand().equals("Hard")) screen.hard();

        screen.showBoard();
    }

}
