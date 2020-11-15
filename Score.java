public class Score {
    private double currentScore;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    public Score() {
        currentScore = 0;
    }

    public double getScore() {
        return currentScore;
    }
    
    public void subtractPoints() {
        double randomNumber = Math.random() * (max - min + 1) + min;
        currentScore -= Math.sqrt(Math.abs(Math.pow(randomNumber, randomNumber)));
    }

    public void addPoints() {
        double randomNumber = Math.random() * (max - min + 1) + min;
        currentScore += Math.sqrt(Math.abs(Math.pow(randomNumber, randomNumber)));
    }
}
