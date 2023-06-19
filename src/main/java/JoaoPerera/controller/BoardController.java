package JoaoPerera.controller;

import JoaoPerera.model.Board;
import JoaoPerera.view.Screen;


import java.awt.event.*;

public class BoardController implements ActionListener {

    private final Screen screen;

    private final Board board;

    private final int row;

    private final int column;

    public BoardController(Screen screen, Board board, int row, int column) {
        this.screen = screen;
        this.board = board;
        this.row = row;
        this.column = column;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
