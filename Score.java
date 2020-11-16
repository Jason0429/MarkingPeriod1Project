public class Score {
    private int currentScore;
    private static int min = Integer.MIN_VALUE;
    private static int max = Integer.MAX_VALUE;

    public Score() {
        currentScore = 0;
    }

    public double getScore() {
        return currentScore;
    }

    public static int getRandomInt() {
        return (int) Math.sqrt(Math.pow(Math.abs((Math.random() * (max - min + 1) + min)), 1));
    }
    
    public void subtractPoints() {
        currentScore -= getRandomInt();
    }

    public void addPoints() {
        currentScore += getRandomInt();
    }
}
