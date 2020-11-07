import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Hangman implements ActionListener {
    private final String title = "Hangman By Jason";
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private JLabel hangmanLabel;
    private JLabel wordLabel;
    private String name;
    private int numOfWrongGuesses;
    private String hangman;
    private String progressWord;
    private String wordToGuess;
    private ArrayList<String> listOfWords = new ArrayList<String>();

    public Hangman() {
        numOfWrongGuesses = 0;
        hangman = "<html><div style=\"font-size:14px;\">__<br>|    <br>|    <br>|    <br>_____<br></div></html>";
        name = "User";
    }

    public Hangman(String name) {
        numOfWrongGuesses = 0;
        hangman = "<html><div style=\"font-size:14px;\">__<br>|    <br>|    <br>|    <br>_____<br></div></html>";
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Renders a keyboard with the alphabet in lowercase
    public JPanel generateKeyboard() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Bind Action Events to buttons
        for (char letter = 'a'; letter <= 'z'; letter++) {
            JButton button = new JButton(letter + "");
            button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                    String letterInButton = button.getText();
                    buttonPress(letterInButton);
					button.setEnabled(false);  
				}   
            });
            panel.add(button);
        }

        return panel;
    }

    public JPanel generateCheatButton() {
        JPanel panel = new JPanel();

        // Bind Action Events to buttons
        JButton button = new JButton("Reveal Word");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // giveUp();
                emptyFrame();
            }   
        });
        panel.add(button);

        return panel;
    }

    // Add new word to list
    public void addWord(String word) {
        listOfWords.add(word.toLowerCase());
    }

    // Set a random word as the word to guess
    public void setWordToGuess() {
        int max = listOfWords.size();
        int rndIndex = (int) (Math.random() * max);
        wordToGuess = listOfWords.get(rndIndex);
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public String getFormattedWordToGuess() {
        String formatted = "";

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (i % 2 == 0) {
                formatted += wordToGuess.substring(i, i + 1);
            } else {
                formatted += " " + wordToGuess.substring(i, i + 1) + " ";
            }
        }
        
        return formatted.trim();
    }

    // Sets a formatted HTML of hangman
    public void setHangman() {
        String head = numOfWrongGuesses >= 1 ? "O" : " ";
        String body = numOfWrongGuesses >= 2 ? "|" : " ";
        String leftArm = numOfWrongGuesses >= 3 ? "/" : " ";
        String rightArm = numOfWrongGuesses >= 4 ? "\\" : " ";
        String leftLeg = numOfWrongGuesses >= 5 ? "/" : " ";
        String rightLeg = numOfWrongGuesses >= 6 ? "\\" : " ";

        hangman = String.format(
                "<html><div style=\"font-size: 14px\">__<br>|   %s<br>| %s%s%s<br>| %s %s<br>______</div></html>",
                head, leftArm, body, rightArm, leftLeg, rightLeg);
    }

    public String getHangman() {
        return hangman;
    }

    // Set progressWord to underscores the length of wordToGuess
    public void setProgressWord() {
        String word = "";

        for (int i = 0; i < wordToGuess.length(); i++) {
            word += "_";
        }

        progressWord = word;
    }

    public String getProgressWord() {
        return progressWord;
    }

    public String getFormattedProgressWord() {
        String formatted = "";

        for (int i = 0; i < progressWord.length(); i++) {
            if (i % 2 == 0) {
                formatted += progressWord.substring(i, i + 1);
            } else {
                formatted += " " + progressWord.substring(i, i + 1) + " ";
            }
        }

        return formatted.trim();
    }

    // Checks if letter is in the word
    public boolean isValid(String letter) {
        return wordToGuess.indexOf(letter) != -1;
    }

    // Updates progressWord to show chosen letter
    public void revealLetter(String letter) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.substring(i, i + 1).equals(letter)) {
                progressWord = progressWord.substring(0, i) + letter + progressWord.substring(i + 1);
            }
        }
    }

    public void buttonPress(String letter) {
        if (isValid(letter)) {
            revealLetter(letter);
            wordLabel.setText(getFormattedProgressWord());
        } else {
            numOfWrongGuesses++;
            setHangman();
            hangmanLabel.setText(getHangman());
        }
        
        if (isGameOver()) {
            end();
        }
    }

    public boolean isGameOver() {
        return progressWord.equals(wordToGuess) || numOfWrongGuesses == 6; 
    }

    public void end() {
        if (progressWord.equals(wordToGuess)) {
            wordLabel.setText(String.format("Congratulations %s! The word was: %s", name, wordToGuess));
        } else {
            wordLabel.setText(String.format("Nice try %s! The word was: %s", name, wordToGuess));
        }
    }

    public void createGUI() {
        // Main frame window
        frame = new JFrame();

        // Define panel
        // panel = new JPanel();
        // panel.setLayout(new GridLayout(5, 1));

        // Define hangman container
        // hangmanLabel = new JLabel();
        // hangman.setFont(new Font("Serif", Font.PLAIN, 14));
        // hangmanLabel.setText(getHangman());
        // hangmanLabel.setHorizontalAlignment(JLabel.CENTER);
        // hangmanLabel.setSize(200, 200);

        // Define word container
        // wordLabel = new JLabel();
        // wordLabel.setText(getFormattedProgressWord());
        // wordLabel.setHorizontalAlignment(JLabel.CENTER);
        // wordLabel.setSize(400, 200);

        // Define labels
        // label = new JLabel("Welcome to Hangman!");
        // label.setHorizontalAlignment(JLabel.CENTER);
        
        // Add components
        // panel.add(label);
        // panel.add(hangmanLabel);
        // panel.add(wordLabel);
        // panel.add(generateKeyboard());
        // panel.add(generateCheatButton());
        // frame.add(panel);

        // Set default conditions
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
        
    }

    public void emptyFrame() {
        frame.removeAll();
    }

    /* Adds word to the list and displays in panel */
    public void updateListOfWordsPanel(JPanel panel, ArrayList<String> wordList) {
        for (String word : wordList) {
            panel.add(new JLabel(word, JLabel.CENTER));
        }
    }

    /*
    * The first screen users will see.
    * Input fields for their name and words they want to use for the game
    */
    public void createInputScreen() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(5, 1));

        // Row 1
        JLabel welcome = new JLabel("Welcome to Hangman");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        newPanel.add(welcome);

        // Row 2
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        newPanel.add(namePanel);

        // Row 3
        JPanel listOfWordsPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setColumns(4);
        listOfWordsPanel.setLayout(gridLayout);
        
        JPanel addWordPanel = new JPanel();
        addWordPanel.setLayout(new FlowLayout());
        JLabel addWordLabel = new JLabel("Add new word: ");
        JTextField addWordField = new JTextField(20);
        JButton addWordButton = new JButton("Add Word");
        addWordPanel.add(addWordLabel);
        addWordPanel.add(addWordField);
        addWordPanel.add(addWordButton);
        newPanel.add(addWordPanel);

        // Row 4 (Components made in Row 3)
        newPanel.add(listOfWordsPanel);
        newPanel.setVisible(true);
        
        // Row 5
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Game Button Clicked");
            }
        });
        startGameButton.setSize(100, 80);
        newPanel.add(startGameButton);

        frame.add(newPanel);
        frame.setVisible(true); // Put last

        // Add event listener after adding the listOfWordsPanel
        addWordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String addWordText = addWordField.getText().toLowerCase().trim();

                // If the field is not empty and only one word
                if (!addWordText.equals("") && addWordText.split(" ").length == 1) {
                    System.out.println(addWordText);

                    // Add the word to list of words
                    listOfWords.add(addWordText);

                    for (int i = 0; i < listOfWords.size(); i++) {
                        listOfWordsPanel.add(new JLabel(listOfWords.get(i)));
                    }

                    // listOfWordsPanel.setVisible(true);

                    // updateListOfWordsPanel(listOfWordsPanel, listOfWords);

                    addWordField.setText("");
                } else {
                    System.out.println(Arrays.deepToString(addWordText.split(" ")));
                }
            }
        });
    }

    public void start() {
        createGUI();
        createInputScreen();

        // if (listOfWords.size() == 0) {
        //     System.out.println("Please add words using the \"addWord(String word)\" method");
        // } else {
        //     setWordToGuess();
        //     setProgressWord();
        //     createGUI();
        // }
    }

    public void giveUp() {
        wordLabel.setText(getFormattedWordToGuess());
        // Write give up and reveal word
    }
}
