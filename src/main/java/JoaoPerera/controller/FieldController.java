package JoaoPerera.controller;

import JoaoPerera.model.Board;
import JoaoPerera.view.Screen;


import javax.swing.*;
import java.awt.event.*;

public class FieldController implements KeyListener{

    private final Screen screen;
    private final Board board;
    private final int row;
    private final int col;
    private final JTextField field;

    public FieldController(Screen screen, Board board , int row, int col , JTextField field) {
        this.screen = screen;
        this.board = board;
        this.row = row;
        this.col = col;
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == '\b'){
            this.field.setText("");
            this.board.setBoardValue(this.row, this.col, 0);
            return;
        }
        this.field.setText(String.valueOf(e.getKeyChar()));
        this.board.setBoardValue(this.row, this.col, getInt(e));
        if (this.board.isBoardFull()) {
            this.screen.check();
        }
        if (this.board.isValueValid(this.row, this.col, getInt(e))) {
            this.field.setBackground(java.awt.Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Value", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.screen.updateBoard(this.board);
    }

    private int getInt(KeyEvent e){
        return Integer.parseInt(String.valueOf(e.getKeyChar()));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
