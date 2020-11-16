import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Hangman {
    private final String TITLE = "Hangman By Jason";
    private String name;
    private Score score;
    private JFrame frame;
    private JLabel hangmanLabel;
    private JLabel wordLabel;
    private int numOfWrongGuesses;
    private String hangmanString;
    private String progressWord;
    private String wordToGuess;
    private ArrayList<String> listOfWords = new ArrayList<String>();

    public Hangman() {
        name = null;
        score = new Score();
        numOfWrongGuesses = 0;
        hangmanString = "<html><div style=\"font-size:14px;\">__<br>|    <br>|    <br>|    <br>_____<br></div></html>";
    }

    public Hangman(String name) {
        this.name = name;
        score = new Score();
        numOfWrongGuesses = 0;
        hangmanString = "<html><div style=\"font-size:14px;\">__<br>|    <br>|    <br>|    <br>_____<br></div></html>";
    }

    public String toString() {
        return "Welcome to Hangman " + getName();
    }

    /*
       Returns user's name.
    */
    public String getName() {
        return name;
    }

    /*
        Sets user's name.
    */
    public void setName(String name) {
        this.name = name;
    }

    /*
        Returns the word player has to guess.
    */
    public String getWordToGuess() {
        return wordToGuess;
    }

    /*
        Assign a random word from the
        list of words to wordToGuess.
    */
    public void setRandomWordToGuess() {
        int max = listOfWords.size();
        int rndIndex = (int) (Math.random() * max);
        wordToGuess = listOfWords.get(rndIndex);
    }

    /*
        Adds word to the list of words to choose from.
    */
    public void addWord(String word) {
        listOfWords.add(word.toLowerCase());
    }

    /*
        Returns true if word only contains alphabetical letters.
    */
    public boolean isAlpha(String word) {
        return name.matches("[a-zA-Z]+");
    }

    /* 
        Returns true if word is one word.
    */
    public boolean isOneWord(String word) {
        return word.split(" ").length == 1;
    }

    /*
        Returns true if word is empty.
    */
    public boolean isEmpty(String word) {
        return word.equals("") || word == null;
    }
    /*
        Returns hangmanString
    */
    public String getHangman() {
        return hangmanString;
    }

    /*
        Sets hangmanString to a formatted HTML string
        based on number of wrong guesses.
    */
    public void setHangman() {
        String head = numOfWrongGuesses >= 1 ? "O" : " ";
        String body = numOfWrongGuesses >= 2 ? "|" : " ";
        String leftArm = numOfWrongGuesses >= 3 ? "/" : " ";
        String rightArm = numOfWrongGuesses >= 4 ? "\\" : " ";
        String leftLeg = numOfWrongGuesses >= 5 ? "/" : " ";
        String rightLeg = numOfWrongGuesses >= 6 ? "\\" : " ";

        hangmanString = String.format(
                "<html><div style=\"font-size: 14px\">__<br>|   %s<br>| %s%s%s<br>| %s %s<br>______</div></html>",
                head, leftArm, body, rightArm, leftLeg, rightLeg);
    }

    /*
        Sets progressWord to a string of underscores the length of wordToGuess.
    */
    public void setProgressWord() {
        String word = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            word += "_";
        }
        progressWord = word;
    }

    /*
        Sets progressWord
    */
    public void setProgressWord(String word) {
        progressWord = word;
    }

    /*
        Returns progressWord
    */
    public String getProgressWord() {
        return progressWord;
    }

    /*
        Returns progressWord with a space between each character.
        For visibility purposes.
    */
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

    /*
        Checks if letter is in the word
    */
    public boolean isValid(String letter) {
        return wordToGuess.indexOf(letter) != -1;
    }

    /*
        Updates progressWord to reveal the letter if it is in the word.
    */
    public void revealLetter(String letter) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.substring(i, i + 1).equals(letter)) {
                progressWord = progressWord.substring(0, i) + letter + progressWord.substring(i + 1);
            }
        }
    }

    /*
        Returns a JPanel of a-z keyboard buttons.
        When button is clicked, it guesses that letter.
    */
    public JPanel generateKeyboard() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
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

    /*
        Returns a JPanel with a button for 
        the player to give up and reveal the word.
    */
    public JPanel generateCheatButton() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Reveal Word");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
            }   
        });
        panel.add(button);
        return panel;
    }

    /*
        When a-z keyboard button is pressed, guess the letter.
        If letter is valid, reveal the letter and update displayed text.
        Else, increment number of wrong guesses and update hangman figure.
        Checks if game is over.
    */
    public void buttonPress(String letter) {
        
        // If correct
        if (isValid(letter)) {
            score.addPoints();
            revealLetter(letter);
            wordLabel.setText(getFormattedProgressWord());
        } else {
            // If wrong
            score.subtractPoints();
            numOfWrongGuesses++;
            setHangman();
            hangmanLabel.setText(getHangman());
        }
        if (isGameOver()) end();
    }

    /*
        Checks if game is over if one of the following conditions are satisfied:
        - User has guessed word
        - Number of wrong guesses equals 6
    */
    public boolean isGameOver() {
        return progressWord.equals(wordToGuess) || numOfWrongGuesses == 6; 
    }

    /*
        Creates JFrame and sets default settings
    */
    public void createGUI() {
        // Main frame window
        frame = new JFrame();
        // mainPanel = new JPanel();

        // Set default conditions
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
    }

    /*
        Sets a random word for player to guess.
        Creates the game screen
        Includes: 
        - Welcome
        - Hangman Figure
        - Word Label
        - a-z keyboard
    */
    public void createGameScreen() {
        setRandomWordToGuess();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Welcome Label
        String welcomeString = String.format("Welcome to Hangman, %s", getName());
        JLabel welcomeLabel = new JLabel(welcomeString);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcomeLabel);

        // Hangman Figure
        setHangman();
        hangmanLabel = new JLabel(getHangman());
        hangmanLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(hangmanLabel);

        // Word Label
        setProgressWord();
        wordLabel = new JLabel(getFormattedProgressWord());
        wordLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(wordLabel);

        // Keyboard
        panel.add(generateKeyboard());

        // Cheat Button
        panel.add(generateCheatButton());

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    /*
        Creates input screen for user to type in:
        - Name
        - Words to add to the list for them to guess later on
        - Button to start game
    */
    public void createInputScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Row 1
        JLabel welcome = new JLabel("Welcome to Hangman");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcome);

        // Row 2
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        panel.add(namePanel);

        // Row 3
        JPanel addWordPanel = new JPanel();
        addWordPanel.setLayout(new FlowLayout());
        JLabel addWordLabel = new JLabel("Add new word: ");
        JTextField addWordField = new JTextField(20);
        JButton addWordButton = new JButton("Add Word");
        addWordPanel.add(addWordLabel);
        addWordPanel.add(addWordField);
        addWordPanel.add(addWordButton);
        panel.add(addWordPanel);

        // Row 4
        JLabel wordCountLabel = new JLabel("0 words added");
        wordCountLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(wordCountLabel);
        
        // Row 5
        JButton startGameButton = new JButton("Start Game");
        
        // ActionListeners for addWordButton and startGameButton
        addWordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String addWordText = addWordField.getText().toLowerCase().trim();

                // If the field is not empty, only consists of letters, and only one word
                if (!isEmpty(addWordText) && isAlpha(addWordText) && isOneWord(addWordText)) {

                    // Add the word to list of words
                    listOfWords.add(addWordText);
                    String numOfWords = listOfWords.size() + "";
                    wordCountLabel.setText(numOfWords + " words added");

                    // Empty Text Field
                    addWordField.setText("");
                }
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Game Button Clicked");

                if (listOfWords.size() == 0) {
                    wordCountLabel.setText("You can't play Hangman without words. Add them.");
                }

                // Sets user name and launches game screen
                setName(nameField.getText());
                createGameScreen();
            }
        });
        panel.add(startGameButton);

        frame.setContentPane(panel);
        frame.setVisible(true); // Put last
    }

    /*
        Starts game
    */
    public void start() {
        createGUI();
        createInputScreen();
    }

    /* 
        Ends game
        Congratulates if winner
        Nice Try if loser
    */
    public void end() {
        if (progressWord.equals(wordToGuess)) {
            wordLabel.setText(String.format("Congratulations %s! The word was: %s. Your score is: %d", getName(), getWordToGuess(), score.getScore()));
        } else {
            wordLabel.setText(String.format("Nice try %s! The word was: %s. Your score is: %d", getName(), getWordToGuess(), score.getScore()));
        }
    }
}