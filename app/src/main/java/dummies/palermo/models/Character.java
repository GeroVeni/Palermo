package dummies.palermo.models;

public enum Character {
    ThiefKnown,
    ThiefSecret,
    Policeman,
    Whore,
    Doctor,
    Citizen,
    Witch,
    Hunter,
    Milkman,
    Roommates;

    public static boolean isCharacterGood(Character character) {
        // TODO: 08-Jan-19 Add more bad characters
        switch (character) {
            case ThiefKnown:
            case ThiefSecret:
            case Whore:
                return false;
            default:
                return true;
        }
    }
}


