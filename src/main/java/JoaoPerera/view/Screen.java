package JoaoPerera.view;

import JoaoPerera.controller.ButtonController;
import JoaoPerera.controller.FieldController;
import JoaoPerera.controller.LevelController;
import JoaoPerera.controller.ScreenController;
import JoaoPerera.model.Board;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

public class Screen extends JFrame {

    private Board board;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final int width = screenSize.width / 2;

    private final int height = screenSize.height / 2;
    private final Dimension preferredScreenSize = new Dimension(450, 200);
    private final Dimension boardSize = new Dimension(1000, 600);
    private final Dimension buttonSize = new Dimension(100, 50);
    private final Color backgroundColor = new Color(25, 30, 35);
    private final Color buttonColor = new Color(50, 55, 60);

    public Screen() {
        super("Sudoku");
    }

    public void display() {
        setSize(new Dimension(100, 100));
        setPreferredSize(preferredScreenSize);
        addWindowListener(new ScreenController(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(screenSize.width / 2 - 250, screenSize.height / 2 - height / 2 + 100);
        setVisible(true);

        getContentPane().add(Welcome());

        pack();
    }


    public JPanel Welcome() {
        //set the layout of the frame
        JPanel welcome = new JPanel();
        welcome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel Greetings = new JPanel();
        JPanel panelTitles = new JPanel();
        panelTitles.setLayout(new GridLayout(0, 1, 20, 20));


        //add the title
        JLabel Greeting = new JLabel("Welcome to Sudoku!");
        Greeting.setForeground(Color.WHITE);
        Greeting.setFont(new Font("Arial", Font.PLAIN, 30));
        panelTitles.add(Greeting);

        //add the instructions
        JLabel Level = new JLabel("Choose a level:");
        Level.setForeground(Color.WHITE);
        Level.setFont(new Font("Arial", Font.PLAIN, 20));
        panelTitles.add(Level);

        panelTitles.setBackground(backgroundColor);
        Greetings.add(panelTitles, BorderLayout.CENTER);
        Greetings.setBackground(backgroundColor);

        welcome.setLayout(new BorderLayout());

        //create the buttons
        JButton easy = new JButton("Easy");
        easy.setSize(buttonSize);
        JButton medium = new JButton("Medium");
        medium.setSize(buttonSize);
        JButton hard = new JButton("Hard");
        hard.setSize(buttonSize);

        //add functionality to the buttons
        easy.addActionListener(new LevelController(this));
        medium.addActionListener(new LevelController(this));
        hard.addActionListener(new LevelController(this));

        //add the buttons to the panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3, 5, 0));
        buttons.setBackground(backgroundColor);

        buttons.add(easy);
        buttons.add(medium);
        buttons.add(hard);

        Greetings.add(buttons, BorderLayout.SOUTH);

        welcome.add(Greetings, BorderLayout.CENTER);
        welcome.setBackground(backgroundColor);

        return welcome;
    }

    public void setEasy() {
        this.board = new Board(20);
    }

    public void setMedium() {
        this.board = new Board(40);
    }

    public void setHard() {
        this.board = new Board(60);
    }


    public void showBoard() {
        //Remove all components from the frame
        this.getContentPane().removeAll();

        //Set the layout of the frame
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, Y_AXIS));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boardPanel.setBackground(backgroundColor);

        for (int row = 0; row < 9; row++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, X_AXIS));
            for (int column = 0; column < 9; column++) {

                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(5, 5));
                textField.setFont(new Font("Arial", Font.PLAIN, 25));
                textField.setBackground(backgroundColor);
                textField.setForeground(Color.WHITE);

                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setText(String.valueOf(this.board.getValue(row, column)));
                textField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                textField.addKeyListener(new FieldController(this, board, row, column, textField));

                if (this.board.getValue(row, column) != 0) {
                    textField.setEditable(false);
                }

                rowPanel.add(textField);
            }
            boardPanel.add(rowPanel);
        }

        JButton back = new JButton("Back");
        back.addActionListener(new ButtonController(this, "back"));
        back.setBackground(buttonColor);
        back.setForeground(Color.WHITE);
        back.setPreferredSize(buttonSize);

        JButton check = new JButton("Check");
        check.addActionListener(new ButtonController(this, "check"));
        check.setBackground(buttonColor);
        check.setForeground(Color.WHITE);
        check.setPreferredSize(buttonSize);

        JButton Solve = new JButton("Solve");
        Solve.addActionListener(new ButtonController(this, "solve"));
        Solve.setBackground(buttonColor);
        Solve.setForeground(Color.WHITE);
        Solve.setPreferredSize(buttonSize);


        JPanel buttons = new JPanel();
        buttons.add(back);
        buttons.add(check);
        buttons.add(Solve);
        buttons.setBackground(backgroundColor);

        boardPanel.add(buttons, BorderLayout.SOUTH);

        this.add(boardPanel);
        this.setSize(boardSize);
        this.setPreferredSize(boardSize);
        this.setLocation(locationWidth(), locationHeight());
        this.pack();
    }

    public void back() {
        this.getContentPane().removeAll();
        display();
    }

    public void check() {
        if (this.board.isSolved()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You won!");
        } else {
            JOptionPane.showMessageDialog(this, "You have some mistakes. Try again!");
        }
    }

    public void updateBoard(Board board) {
        this.board.setBoard(board.getBoard());
        showBoard();
    }

    public void instructions() {

        //Remove all components from the frame
        this.getContentPane().removeAll();

        JPanel Instruction = new JPanel();
        Instruction.setLayout(new GridLayout(0, 1, 10, 10));
        Instruction.setBackground(backgroundColor);
        Instruction.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel Instructions = new JLabel("Instructions:");
        Instructions.setForeground(Color.WHITE);
        Instructions.setFont(new Font("Arial", Font.PLAIN, 20));
        Instruction.add(Instructions, BorderLayout.CENTER);

        JLabel Instructions1 = new JLabel("1. Click on the box you want to fill in.");
        Instructions1.setForeground(Color.WHITE);
        Instructions1.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions1, BorderLayout.CENTER);

        JLabel Instructions2 = new JLabel("2. Type the number you want to fill in.");
        Instructions2.setForeground(Color.WHITE);
        Instructions2.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions2, BorderLayout.CENTER);

        JLabel Instructions3 = new JLabel("3. Press enter to confirm.");
        Instructions3.setForeground(Color.WHITE);
        Instructions3.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions3, BorderLayout.CENTER);

        JLabel Instructions4 = new JLabel("4. If you want to delete a number, press backspace.");
        Instructions4.setForeground(Color.WHITE);
        Instructions4.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions4, BorderLayout.CENTER);


        JLabel Instructions5 = new JLabel("5. If you want to see the solution, press the solution button.");
        Instructions5.setForeground(Color.WHITE);
        Instructions5.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions5, BorderLayout.CENTER);

        JLabel Instructions6 = new JLabel("6. If you want to go back to the main menu, press the back button.");
        Instructions6.setForeground(Color.WHITE);
        Instructions6.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions6, BorderLayout.CENTER);

        JLabel Instructions7 = new JLabel("7. The number you fill in must be between 1 and 9.");
        Instructions7.setForeground(Color.WHITE);
        Instructions7.setFont(new Font("Arial", Font.PLAIN, 15));
        Instruction.add(Instructions7, BorderLayout.CENTER);

        JButton Continue = new JButton("Continue");
        Continue.setSize(buttonSize);
        Continue.setBackground(buttonColor);
        Continue.setForeground(Color.WHITE);
        Continue.addActionListener(e -> this.showBoard());
        Instruction.add(Continue, BorderLayout.SOUTH);

        this.add(Instruction);
        this.setPreferredSize(new Dimension(450, 450));
        this.setLocation(width / 2 +150, height / 2);
        this.pack();
    }

    public int locationWidth() {
        return screenSize.width / 2 - boardSize.width / 2;
    }

    public int locationHeight() {
        return screenSize.height / 2 - boardSize.height / 2;
    }

    public void solve() {
        this.board.solve();
        showBoard();
    }
}
