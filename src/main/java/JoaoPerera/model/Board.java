package JoaoPerera.model;

import java.util.*;

public class Board {
    private List<List<Integer>> board;


    public Board(int level) {
        this.board = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            List<Integer> rowList = new ArrayList<>();
            for (int column = 0; column < 9; column++)
                rowList.add(0);

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

    public void setValue(int row, int column, int value) {
        this.board.get(row).set(column, value);
    }

    public int getValue(int row, int column) {
        return this.board.get(row).get(column);
    }

    public boolean isFull() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (this.board.get(row).get(column) == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public void generateBoard(int numberOfValues) {
        this.draftBoard();

        if (!this.checkBoard()) {
            this.fixBoard();
        }

        this.draftZeros(numberOfValues);
    }

    public void fixBoard() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (!this.checkValue(row, column, this.getValue(row, column))) {
                    this.setValue(row, column, 0);
                    List<Integer> possibleValues = this.getPossibleValues(row, column);
                    for (Integer possibleValue : possibleValues) {
                        if (this.checkValue(row, column, possibleValue)) {
                            this.setValue(row, column, possibleValue);
                            break;
                        }
                    }
                }
            }
        }

    }

    public boolean checkBoard() {
        return this.checkRows() && this.checkColumns() && this.checkSquares();
    }

    private boolean checkRows() {
        for (int row = 0; row < 9; row++) {
            List<Integer> rowList = new ArrayList<>();
            for (int column = 0; column < 9; column++) {
                rowList.add(this.board.get(row).get(column));
            }
            if (this.checkList(rowList))
                return false;
        }
        return true;
    }

    private boolean checkColumns() {
        for (int column = 0; column < 9; column++) {
            List<Integer> columnList = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                columnList.add(this.board.get(row).get(column));
            }
            if (this.checkList(columnList))
                return false;
        }
        return true;
    }

    private boolean checkSquares() {
        for (int row = 0; row < 9; row += 3) {
            for (int column = 0; column < 9; column += 3) {
                List<Integer> squareList = new ArrayList<>();
                for (int i = row; i < row + 3; i++) {
                    for (int j = column; j < column + 3; j++) {
                        squareList.add(this.board.get(i).get(j));
                    }
                }
                if (this.checkList(squareList))
                    return false;
            }
        }
        return true;
    }

    private boolean checkList(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        for (int i = 0; i < 9; i++) {
            if (sortedList.get(i) != i + 1)
                return true;
        }
        return false;
    }

    public void draftZeros(int numberOfValues) {
        for (int i = 0; i < numberOfValues; i++) {
            Random random = new Random();
            int row = random.nextInt(9);
            int column = random.nextInt(9);

            if (this.board.get(row).get(column) != 0)
                this.setValue(row, column, 0);
            else
                i--;
        }
    }

    private void draftBoard() {
        this.draftFirstRow();
        this.draftFirstColumn();
        this.draftFirstSquare();
        this.draftRest();
    }

    private void draftFirstRow() {
        List<Integer> row = new ArrayList<>();
        for (int i = 1; i <= 9; i++)
            row.add(i);

        Collections.shuffle(row);
        for (int column = 0; column < 9; column++)
            this.setValue(0, column, row.get(column));
    }

    private void draftFirstColumn() {
        List<Integer> column = new ArrayList<>();
        for (int i = 1; i <= 9; i++)
            column.add(i);

        column.remove((Integer) this.getValue(0, 0));
        Collections.shuffle(column);
        for (int row = 1; row < 9; row++)
            this.setValue(row, 0, column.get(row - 1));
    }

    private void draftFirstSquare() {
        List<Integer> square = new ArrayList<>();
        for (int i = 1; i <= 9; i++)
            square.add(i);

        Collections.shuffle(square);
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                List<Integer> possibleValues = this.getPossibleValues(row, column);
                Collections.shuffle(possibleValues);
                for (int value : possibleValues) {
                    if (this.checkValue(row, column, value)) {
                        this.setValue(row, column, value);
                        break;
                    }
                }
            }
        }
    }

    private void draftRest() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (this.getValue(row, column) == 0) {
                    List<Integer> possibleValues = this.getPossibleValues(row, column);
                    Collections.shuffle(possibleValues);
                    for (int value : possibleValues) {
                        if (this.checkValue(row, column, value)) {
                            this.setValue(row, column, value);
                            break;
                        }
                    }
                }
            }
        }
    }

    private List<Integer> getPossibleValues(int row, int column) {
        List<Integer> possibleValues = new ArrayList<>();
        for (int i = 1; i <= 9; i++)
            possibleValues.add(i);

        for (int i = 0; i < 9; i++) {
            possibleValues.remove((Integer) this.getValue(row, i));
            possibleValues.remove((Integer) this.getValue(i, column));
        }

        int squareRow = row / 3;
        int squareColumn = column / 3;
        for (int i = squareRow * 3; i < squareRow * 3 + 3; i++) {
            for (int j = squareColumn * 3; j < squareColumn * 3 + 3; j++)
                possibleValues.remove((Integer) this.getValue(i, j));
        }

        return possibleValues;
    }

    public boolean checkValue(int row, int column, int value) {
        return this.isRowValid(row, column, value) && this.isColumnValid(row,column, value) && this.isSquareValid(row, column, value);
    }

    private boolean isRowValid(int row, int col, int value) {
        for (int column = 0; column < 9; column++) {
            if (this.getValue(row, column) == value) {
                if (column != col)
                    return false;
            }
        }
        return true;
    }

    private boolean isColumnValid(int selfRow, int column, int value) {
        for (int row = 0; row < 9; row++) {
            if (this.getValue(row, column) == value) {
                if (row != selfRow)
                    return false;
            }
        }
        return true;
    }

    private boolean isSquareValid(int row, int column, int value) {
        int squareRow = row / 3;
        int squareColumn = column / 3;
        for (int i = squareRow * 3; i < squareRow * 3 + 3; i++) {
            for (int j = squareColumn * 3; j < squareColumn * 3 + 3; j++) {
                if (this.getValue(i, j) == value) {
                    if (i != row && j != column)
                        return false;
                }
            }
        }
        return true;
    }


    public boolean isSolved() {
        return this.isFull() && this.checkBoard();
    }

    public void solve() {
        if (this.isSolved()) {
            return;
        }

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                int value = this.getValue(row, column);
                if (value == 0) {
                    List<Integer> possibleValues = this.getPossibleValues(row, column);
                    for (int possibleValue : possibleValues) {
                        if (this.checkValue(row, column, possibleValue)) {
                            this.setValue(row, column, possibleValue);
                            if (this.isSolved()) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

}
