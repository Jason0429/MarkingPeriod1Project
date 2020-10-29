import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class GUI implements ActionListener {
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private JButton button;
    private JLabel hangman; // Panel
    private JLabel word; // Panel
    
    public GUI(Hangman game) {
        // Main frame window
        frame = new JFrame();

        // Define panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Define hangman container
        hangman = new JLabel();
        // hangman.setFont(new Font("Serif", Font.PLAIN, 14));
        hangman.setText(game.generateHangman());
        hangman.setHorizontalAlignment(JLabel.CENTER);
        hangman.setSize(200, 200);

        // Define word container
        word = new JLabel();
        word.setText(game.generateWord());
        word.setHorizontalAlignment(JLabel.CENTER);
        word.setSize(400, 200);

        // Define labels
        label = new JLabel("Welcome to Hangman!");
        label.setHorizontalAlignment(JLabel.CENTER);
        
        // Add components
        panel.add(label);
        panel.add(hangman);
        panel.add(word);
        panel.add(generateKeyboard());
        frame.add(panel);
        
        // Set default conditions
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hangman by Jason Cheung");
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true); // Put last

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Renders a keyboard with the alphabet in lowercase
    public JPanel generateKeyboard() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        for (char letter = 'a'; letter <= 'z'; letter++) {
            JButton button = new JButton(letter + "");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String letterInButton = button.getText();
                    listOfLettersGuessed.add(letterInButton);
                    guessLetter(letterInButton);
                    button.setEnabled(false);
                }
            });
            panel.add(button);
        }

        return panel;
    }
}
