package JoaoPerera.model;

public class Board {
private int[][] board;

    public Board() {
        this.board = new int[9][9];
    }

    public Board(int[][] board) {
        this.board = board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoardValue(int row, int column, int value) {
        this.board[row][column] = value;
    }

    public int getBoardValue(int row, int column) {
        return this.board[row][column];
    }

    public boolean isBoardFull(){
        for(int row = 0; row < 9; row++) {
            for(int column = 0; column < 9; column++) {
                if(this.board[row][column] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean indexValid(int row, int column) {
        return row >= 0 && row < 9 && column >= 0 && column < 9;
    }
}
