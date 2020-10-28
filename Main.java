import java.util.*;

public class Main {

    public static void main(String[] args) {
        String name;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Hangman! What is your name?");
        name = sc.nextLine();

        Hangman game = new Hangman(name);

        Hangman.addWord("cheese");
        Hangman.addWord("burger");
        Hangman.addWord("mcdonalds");
        Hangman.addWord("pizza");
        Hangman.addWord("jackpot");
        Hangman.addWord("xylophone");
        Hangman.addWord("zombie");
        Hangman.addWord("syndrome");
        Hangman.addWord("zodiac");

        game.start();

        sc.close();
    }
}