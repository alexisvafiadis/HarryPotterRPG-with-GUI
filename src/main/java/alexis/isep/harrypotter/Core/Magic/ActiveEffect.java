package alexis.isep.harrypotter.Core.Magic;

public class ActiveEffect {
    //This could be the result of a spell or potions

    private int nbOfRoundsLeft;
    private double value;//could be a probability affecting the accuracy, or a stat multiplier

    public ActiveEffect(int nbOfRoundsLeft, double value) {
        this.nbOfRoundsLeft = nbOfRoundsLeft;
        this.value = value;
    }

    public int getNbOfRoundsLeft() {
        return nbOfRoundsLeft;
    }

    public double getValue() {
        return value;
    }

    public void reduceNbOfRounds() {
        this.nbOfRoundsLeft = nbOfRoundsLeft - 1;
    }
}
