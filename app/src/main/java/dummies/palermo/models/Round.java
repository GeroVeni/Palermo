package dummies.palermo.models;

public class Round {
    int number;
    boolean night;
    Character character;

    public Round() {
        number = 0;
        night = true;
        character = null;
    }

    Round inc() {
        // TODO: 22-Jan-19 Increase round

        return this;
    }
}
