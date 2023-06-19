package JoaoPerera.controller;

import JoaoPerera.view.Screen;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ScreenController implements WindowListener {

    public final Screen screen;

    public ScreenController(Screen screen) {
        this.screen = screen;
    }
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
