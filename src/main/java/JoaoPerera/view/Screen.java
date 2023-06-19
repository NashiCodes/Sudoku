package JoaoPerera.view;

import JoaoPerera.controller.BoardController;
import JoaoPerera.controller.ScreenController;
import JoaoPerera.model.Board;

import javax.swing.*;

public class Screen extends JFrame {

    private Board board;

    public Screen() {
        super("Sudoku");
    }

    public void display() {
        setSize(300, 300);
        addWindowListener(new ScreenController(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        showBoard();

        pack();
    }

    public void showBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField idxInput = new JTextField(5);
                idxInput.setText(Integer.toString(board.getBoardValue(i, j)));
                idxInput.addActionListener(new BoardController(this, board, i, j));
            }
        }
    }

    public void loadBoard(Board board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board.getBoard();
    }
}
