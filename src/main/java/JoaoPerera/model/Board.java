package JoaoPerera.model;

import java.util.*;

public class Board {
    private List<List<Integer>> board;

    public Board() {
        this.board = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            List<Integer> rowList = new ArrayList<>();
            for (int column = 0; column < 9; column++) {
                rowList.add(0);
            }
            this.board.add(rowList);
        }
    }

    public Board(int level) {
        this.board = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            List<Integer> rowList = new ArrayList<>();
            for (int column = 0; column < 9; column++) {
                rowList.add(0);
            }
            this.board.add(rowList);
        }

        this.generateBoard(level);
    }

    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    public List<List<Integer>> getBoard() {
        return this.board;
    }

    public void setBoardValue(int row, int column, int value) {
        this.board.get(row).set(column, value);
    }

    public int getBoardValue(int row, int column) {
        return this.board.get(row).get(column);
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (this.board.get(row).get(column) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean indexValid(int row, int column) {
        return row >= 0 && row < 9 && column >= 0 && column < 9;
    }

    public boolean valueValid(int value) {
        return value >= 1 && value <= 9;
    }

    private void draftValue() {
        Random random = new Random();
        int row = random.nextInt(9);
        int column = random.nextInt(9);
        int value = random.nextInt(9) + 1;
        if (this.indexValid(row, column) && this.valueValid(value)) {
            this.setBoardValue(row, column, value);
        }
    }

    public void generateBoard(int numberOfValues) {
        for (int i = 0; i < numberOfValues; i++) {
            this.draftValue();
        }
    }

    public boolean isBoardValid() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int value = this.getBoardValue(row, column);
                if (value != 0) {
                    this.setBoardValue(row, column, 0);
                    if (!this.isValueValid(row, column, value)) {
                        return false;
                    }
                    this.setBoardValue(row, column, value);
                }
            }
        }
        return true;
    }

    public boolean isValueValid(int row, int column, int value) {
        return this.isRowValid(row, value) && this.isColumnValid(column, value) && this.isSquareValid(row, column, value);
    }

    public boolean isRowValid(int row, int value) {
        for (int column = 0; column < 9; column++) {
            if (this.getBoardValue(row, column) == value) {
                return false;
            }
        }
        return true;
    }

    public boolean isColumnValid(int column, int value) {
        for (int row = 0; row < 9; row++) {
            if (this.getBoardValue(row, column) == value) {
                return false;
            }
        }
        return true;
    }

    public boolean isSquareValid(int row, int column, int value) {
        int squareRow = row - row % 3;
        int squareColumn = column - column % 3;
        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareColumn; j < squareColumn + 3; j++) {
                if (this.getBoardValue(i, j) == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBoardSolved() {
        return this.isBoardFull() && this.isBoardValid();
    }

    public boolean isBoardSolvable() {
        return this.isBoardValid();
    }

    public boolean isBoardEmpty() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (this.getBoardValue(row, column) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int row = 0; row < 9; row++) {
            System.out.print(row + " ");
            for (int column = 0; column < 9; column++) {
                System.out.print(this.getBoardValue(row, column) + " ");
                if (column == 2 || column == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (row == 2 || row == 5) {
                System.out.println("---------------------");
            }
        }
    }
}
