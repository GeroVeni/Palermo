package dummies.palermo.Models;

public enum Character {
    Policeman,
    ThiefKnown,
    ThiefSecret,
    Whore,
    Doctor;
//    Witch,
//    Citizen

    public static boolean isCharacterGood(Character character) {
        switch (character) {
            case ThiefKnown:
                return false;
            case ThiefSecret:
                return false;
            case Whore:
                return false;
            default:
                return true;
        }
    }
}


