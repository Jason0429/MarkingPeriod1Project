import java.util.ArrayList;

public class Hangman {

    private String wordToGuess;
    private String currentWordProgress; // "c___s_"
    private ArrayList<String> listOfLettersGuessed = new ArrayList<String>();
    ArrayList<String> listOfWords = new ArrayList<String>();
    private int numOfWrongGuesses = 0;

    public Hangman() {

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Add new word to list
    public void addWord(String word) {
        listOfWords.add(word.toLowerCase());
    }

    // Set a random word as the word to guess
    public void setRandomWord() {

        // Length of <listOfWords> ArrayList
        int max = listOfWords.size();

        // Random Index between 0 and <max>
        int rndIndex = (int) (Math.random() * max);

        // Word at index <rndIndex> of <listOfWords>
        wordToGuess = listOfWords.get(rndIndex);

        // Setting blanks in <currentWordProgress>
        String blanks = "";

        for (int i = 0; i < wordToGuess.length(); i++) {
            blanks += "_";
        }

        currentWordProgress = blanks;
    }

    // Returns a formatted HTML of hangman
    public String generateHangman() {
        String head = numOfWrongGuesses >= 1 ? "O" : " ";
        String body = numOfWrongGuesses >= 2 ? "|" : " ";
        String leftArm = numOfWrongGuesses >= 3 ? "/" : " ";
        String rightArm = numOfWrongGuesses >= 4 ? "\\" : " ";
        String leftLeg = numOfWrongGuesses >= 5 ? "/" : " ";
        String rightLeg = numOfWrongGuesses >= 6 ? "\\" : " ";

        return String.format("<html><div style=\"font-size:14px;\">__<br>|   %s<br>| %s%s%s<br>| %s %s<br>___<br></div></html>", head, leftArm, body, rightArm, leftLeg,
                rightLeg);
    }

    // Returns a formatted HTML of the current word
    public String generateWord() {
        String progress = "";

        for (int i = 0; i < wordToGuess.length(); i++) {
            progress += "_";
        }

        return String.format("<html><div style=\"letter-spacing: 2px;\">%s</div></html>", progress);    
    }

    

    // Checks to see if letter is valid.
    // If so, reveal letter.
    // If not, remain "_"
    public void guessLetter(String letter) {
        // Going to populate with newly revised String
        String replaceCurrentWordProgress  = "";

        for (int i = 0; i < wordToGuess.length(); i++) {
            String letterOfCurrentWordProgress = currentWordProgress.substring(i, i + 1);
            String letterOfWordToGuess = wordToGuess.substring(i, i + 1);
            if (!letterOfCurrentWordProgress.equals("_")) {
                replaceCurrentWordProgress += letterOfCurrentWordProgress;
            } else if (letterOfWordToGuess.equals(letter)) {
                replaceCurrentWordProgress += letter;
            } else {
                replaceCurrentWordProgress += "_";
            }
        }

        // If the strings are the same, it means the letter was not valid
        // If so, increment number of wrong guesses
        if (replaceCurrentWordProgress.equals(currentWordProgress)) {
            numOfWrongGuesses++;
            hangman.setText(generateHangman());
        }

        currentWordProgress = replaceCurrentWordProgress;
        word.setText(currentWordProgress);
    }
}
