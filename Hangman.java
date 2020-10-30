import java.util.ArrayList;

public class Hangman {
    private int numOfWrongGuesses;
    private String hangman;
    private String progressWord; // "c___s_"
    private String wordToGuess;
    ArrayList<String> listOfWords = new ArrayList<String>();

    public Hangman() {
        numOfWrongGuesses = 0;
        hangman = "<html><div style=\"font-size:14px;\">__<br>|   O<br>| /|\\<br>| / \\<br>___<br></div></html>";
    }

    // Add new word to list
    public void addWord(String word) {
        listOfWords.add(word.toLowerCase());
    }

    // Set a random word as the word to guess
    public void setRandomWord() {
        int max = listOfWords.size();
        int rndIndex = (int) (Math.random() * max);
        wordToGuess = listOfWords.get(rndIndex);
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
                "<html><div style=\"font-size:14px;\">__<br>|   %s<br>| %s%s%s<br>| %s %s<br>___<br></div></html>",
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

        progressWord = String.format("<html><div style=\"letter-spacing: 2px;\">%s</div></html>", word);
    }

    // Sets a formatted HTML of the current word
    public void setProgressWord(String word) {
        progressWord = String.format("<html><div style=\"letter-spacing: 2px;\">%s</div></html>", word);
    }

    public String getProgressWord() {
        return progressWord;
    }

    // Checks to see if letter is valid.
    // If so, reveal letter.
    // If not, remain "_"
    public void guessLetter(String letter) {
        // Going to populate with newly revised String
        String replaceprogressWord = "";

        for (int i = 0; i < wordToGuess.length(); i++) {
            String letterOfprogressWord = progressWord.substring(i, i + 1);
            String letterOfWordToGuess = wordToGuess.substring(i, i + 1);
            if (!letterOfprogressWord.equals("_")) {
                replaceprogressWord += letterOfprogressWord;
            } else if (letterOfWordToGuess.equals(letter)) {
                replaceprogressWord += letter;
            } else {
                replaceprogressWord += "_";
            }
        }

        // If the strings are the same, it means the letter was not valid
        // If so, increment number of wrong guesses
        if (replaceprogressWord.equals(progressWord)) {
            numOfWrongGuesses++;
        }

        progressWord = replaceprogressWord;
    }

    // Checks if letter is in the word
    public boolean isValid(String letter) {
        return wordToGuess.indexOf(letter) != -1;
    }

    public void start() {
        if (listOfWords.size() == 0) {
            System.out.println("Please add words using the \"addWord(String word)\" method");
        } else {
            setRandomWord();
            setProgressWord();
            setHangman();
            new GUI();
        }
    }
}
