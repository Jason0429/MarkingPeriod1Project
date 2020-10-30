public class Main {
    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.addWord("cheeseburger");
        game.addWord("xylophone");
        game.addWord("pizza");

        game.start();
    }
}
