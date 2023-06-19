package JoaoPerera.controller;

import JoaoPerera.view.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final Screen screen;
    private final String text;

    public ButtonController(Screen screen, String text) {
        this.screen = screen;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (text) {
            case "back" -> screen.back();
            case "check" -> screen.check();
        }
    }
}
