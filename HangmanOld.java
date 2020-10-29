import java.util.*;

public class HangmanOld {
    private static ArrayList<String> listOfWords = new ArrayList<String>();
    private ArrayList<String> listOfChosenLetters = new ArrayList<String>();
    private String name;
    private String hangman;
    private String wordToGuess;
    private String currentWord;
    private String lastGuess;
    private int compareTo;
    private int totalGuesses;
    private int wrongGuesses;
    private final double RANDOM_SCORE = (double) (Math
            .abs(Math.sqrt(Math.pow(Math.random() * 1000 + 1, Math.random() * 5 + 1))));
    private boolean isOver;

    public HangmanOld() {
        this.name = "User";
        this.totalGuesses = 0;
        this.wrongGuesses = 0;
        this.isOver = false;
    }

    public HangmanOld(String name) {
        this.name = name;
        this.totalGuesses = 0;
        this.wrongGuesses = 0;
        this.isOver = false;
    }

    public HangmanOld(String name, ArrayList<String> words) {
        this.name = name;
        this.totalGuesses = 0;
        this.wrongGuesses = 0;
        listOfWords = words;
        this.isOver = false;
    }

    public String getName() {
        return this.name;
    }

    public int getGuesses() {
        return this.totalGuesses;
    }

    public static ArrayList<String> getListOfWords() {
        return listOfWords;
    }

    public int getCompareTo() {
        return this.compareTo;
    }

    public static void addWord(String word) {
        listOfWords.add(word.toLowerCase());
    }

    public String toString() {
        return String.format("Hello %s. Welcome to Hangman!", this.name);
    }

    private void getRandomWord() {
        // Length of <listOfWords> ArrayList
        int max = listOfWords.size();

        // Random Index between 0 and <max>
        int rndIndex = (int) (Math.random() * max);

        // Word at index <rndIndex> of <listOfWords>
        this.wordToGuess = listOfWords.get(rndIndex);

        // Makes "_" for <currentWord>
        String blanks = "";

        for (int i = 0; i < this.wordToGuess.length(); i++) {
            blanks += "_";
        }

        this.currentWord = blanks;
    }

    private void updateHangman() {
        // Draw hangman and render body part conditionally
        String head = this.wrongGuesses >= 1 ? "O" : " ";
        String body = this.wrongGuesses >= 2 ? "|" : " ";
        String leftArm = this.wrongGuesses >= 3 ? "/" : " ";
        String rightArm = this.wrongGuesses >= 4 ? "\\" : " ";
        String leftLeg = this.wrongGuesses >= 5 ? "/" : " ";
        String rightLeg = this.wrongGuesses >= 6 ? "\\" : " ";

        this.hangman = String.format("___\n|  %s\n| %s%s%s\n| %s %s\n___\n", head, leftArm, body, rightArm, leftLeg,
                rightLeg);
    }

    private void updateCurrentWord(String word) {
        if (word.length() > 1) {
            if (this.wordToGuess.equals(word)) {
                this.currentWord = word;
            }
        } else {
            int wordLength = this.wordToGuess.length();

            // String that will be assigned to <this.currentWord>. Ex: "appl_s"
            String currentWord = "";

            for (int i = 0; i < wordLength; i++) {
                String currentLetter = this.wordToGuess.substring(i, i + 1);

                // If user already chose letter, replace "_" with the actual letter
                if (this.listOfChosenLetters.contains(currentLetter)) {
                    currentWord += currentLetter;
                } else {
                    currentWord += "_";
                }
            }
            this.currentWord = currentWord;
        }
    }

    private boolean isComplete() {
        for (int i = 0; i < this.currentWord.length(); i++) {
            if (this.currentWord.substring(i, i + 1).equals("_")) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlreadyGuessed(String letter) {
        return this.listOfChosenLetters.contains(letter);
    }

    private boolean isMatch(String str) {
        // indexOf() returns -1 if <letter> not found in <wordToGuess>
        // returns true if it does not equal -1
        // returns false if it does equal -1

        if (str.length() > 1) {
            return this.wordToGuess.equals(str);
        } else {
            return this.wordToGuess.indexOf(str) != -1;
        }

    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.printf("Hello %s. Welcome to Hangman!\n", this.name);

        getRandomWord();
        updateHangman();

        // Display hangman
        System.out.println(this.hangman);

        // Display word status
        System.out.println(this.currentWord);

        while (!this.isOver) {
            System.out.println("Guess a letter or the word (or type \"give up\"):");

            // input the user guesses
            input = sc.nextLine().toLowerCase();

            if (input.equals("give up")) {
                this.wrongGuesses = 6;
                this.isOver = true;
                break;
            }

            // If <input> is already chosen, restarts loop
            if (isAlreadyGuessed(input)) {
                System.out.println("Letter/Word already guessed");
                continue;
            } else {

                // Set <lastGuess> to current <input>
                this.lastGuess = input;

                // Increment total number of guesses
                this.totalGuesses++;

                // Add user picked <input> to <listOfChosenLetters>
                this.listOfChosenLetters.add(input);

                // No practical purpose... just for compareTo() method
                this.compareTo = this.lastGuess.compareTo(input);

                if (isMatch(input)) {
                    updateCurrentWord(input);
                } else {
                    // Increment number of wrong guesses
                    this.wrongGuesses++;
                    updateHangman();
                }

                // Display hangman
                System.out.println(this.hangman);

                // Display word status
                System.out.println(this.currentWord);

                if (this.wrongGuesses == 6 || isComplete()) {
                    this.isOver = true;
                }
            }
        }

        if (this.wrongGuesses == 6) {
            System.out.println("=== You Lost! ===");
        } else {
            System.out.println("=== You Won! ===");
        }

        System.out.printf("Thank you for playing %s\n", this.name);
        System.out.printf("The word was: %s\n", this.wordToGuess);

        if (this.wrongGuesses == 6)
            System.out.printf("It took you %d guesses\n", this.totalGuesses);

        System.out.printf("=== Your score is: %f ===\n", this.RANDOM_SCORE);

        sc.close();
    }
}
