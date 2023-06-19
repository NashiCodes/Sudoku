package JoaoPerera.view;

import JoaoPerera.controller.BoardController;
import JoaoPerera.controller.LevelController;
import JoaoPerera.controller.ScreenController;
import JoaoPerera.model.Board;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.BoxLayout.*;

public class Screen extends JFrame {

    private Board board;
    private Dimension maxScreenSize = new Dimension(600, 600);

    private Dimension minScreenSize = new Dimension(400, 400);

    private Dimension preferredScreenSize = new Dimension(450, 200);

    private Dimension boardSize = new Dimension(600, 600);

    private Dimension buttonSize = new Dimension(100, 100);

    private Color backgroundColor = new Color(25, 30, 35);


    public Screen() {
        super("Sudoku");
        this.board = new Board();
    }

    public void display() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(100 , 100));
        setPreferredSize(preferredScreenSize);
        addWindowListener(new ScreenController(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(screenSize.width / 2 - preferredScreenSize.width / 2, screenSize.height / 2 - preferredScreenSize.height / 2);
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
        buttons.setLayout(new GridLayout(1, 3 ,5,0));
        buttons.setBackground(backgroundColor);

        buttons.add(easy);
        buttons.add(medium);
        buttons.add(hard);

        Greetings.add(buttons, BorderLayout.SOUTH);

        welcome.add(Greetings, BorderLayout.CENTER);
        welcome.setBackground(backgroundColor);

        return welcome;
    }

    public void easy() {
        this.board = new Board(60);
    }

    public void medium() {
        this.board = new Board(40);
    }

    public void hard() {
        this.board = new Board(20);
    }


    public void showBoard() {

        this.getContentPane().removeAll();

        List<List<Integer>> board = this.board.getBoard();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, Y_AXIS));
        for (int row = 0; row < 9; row++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, X_AXIS));
            for (int column = 0; column < 9; column++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setText(String.valueOf(board.get(row).get(column)));
                textField.setEditable(false);
                rowPanel.add(textField);
            }
            panel.add(rowPanel);
        }
        this.add(panel);
        this.pack();
    }

}
