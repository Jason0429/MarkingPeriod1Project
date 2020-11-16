public class Score {
    private int currentScore;
    private static int min;
    private static int max;

    public Score() {
        currentScore = 0;
        max = Integer.MAX_VALUE;
        min = Integer.MIN_VALUE;
    }

    public int getScore() {
        return currentScore;
    }

    public int getRandomInt() {
        return (int) Math.sqrt(Math.pow(Math.abs((Math.random() * (max - min + 1) + min)), 1));
    }
    
    public void subtractPoints() {
        currentScore -= getRandomInt();
    }

    public void addPoints() {
        currentScore += getRandomInt();
    }
}