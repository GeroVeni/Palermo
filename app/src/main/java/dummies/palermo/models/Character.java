package dummies.palermo.models;

public enum Character {
    // TODO: 01-Apr-19 Change enum to constants
    ThiefSecret,
    ThiefKnown,
    Roommates,
    Hunter,
    Citizen,
    Witch,
    Policeman,
    Doctor,
    Whore,
    Milkman,

//    ThiefSecret,
//    ThiefKnown,
//    Hunter,
//    Roommates,
//    Citizen,
//    Witch,
//    Doctor,
//    Policeman,
//    Whore,
//    Milkman,
    ;

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

    public static int getPos(Character character) {
        for (int i = 0; i < values().length; i++) {
            if (character == values()[i]) return i;
        }
        return -1;
    }
}


