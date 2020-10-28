import java.util.*;

public class Main {

    public static void main(String[] args) {
        String name;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Hangman! What is your name?");
        name = sc.nextLine();

        Hangman game = new Hangman(name);

        game.addWord("cheese");
        game.addWord("burger");
        game.addWord("mcdonalds");
        game.addWord("pizza");
        game.addWord("jackpot");
        game.addWord("xylophone");
        game.addWord("zombie");
        game.addWord("syndrome");
        game.addWord("puppy");
        game.addWord("microwave");
        game.addWord("zodiac");

        game.start();

        sc.close();
    }
}