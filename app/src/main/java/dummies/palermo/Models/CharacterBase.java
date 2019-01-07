package dummies.palermo.Models;

import java.util.ArrayList;
import java.util.List;

import dummies.palermo.R;

public class CharacterBase {

    private List<CharacterItem> characterItemList;

    private static CharacterBase instance = null;

    public static CharacterBase getCharacterBase() {
        if (instance == null) {
            instance = new CharacterBase();
        }
        return instance;
    }

    private CharacterBase() {
        // Make character item list
        characterItemList = new ArrayList<>();

        for (Character character : Character.values()) {
            characterItemList.add(getCharacter(character));
        }
    }

    private CharacterItem getCharacter(Character character) {
        switch (character) {
            case ThiefKnown:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_hidden_thief_icon)
                        .addImageID(R.drawable.ic_hidden_thief_image)
                        .addTitle("Thief Known")
                        .addSubtitle("Out and about")
                        .addInfo("The decoy")
                        .create();
            case ThiefSecret:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_visible_thief_icon)
                        .addImageID(R.drawable.ic_visible_thief_image)
                        .addTitle("Thief Secret")
                        .addSubtitle("???")
                        .addInfo("Who knows ... ?")
                        .create();
            case Policeman:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_policeman_icon)
                        .addImageID(R.drawable.ic_policeman_image)
                        .addTitle("Policeman")
                        .addSubtitle("The Law")
                        .addInfo("Fuck the police, coming straight from the underground")
                        .create();
            case Whore:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_whore_icon)
                        .addImageID(R.drawable.ic_whore_image)
                        .addTitle("Whore")
                        .addSubtitle("The lookout")
                        .addInfo("I'm such a fucking whore; I love it")
                        .create();
            case Doctor:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_doctor_icon)
                        .addImageID(R.drawable.ic_doctor_image)
                        .addTitle("Doctor")
                        .addSubtitle("The saver")
                        .addInfo("An apple a day keeps the doctor away")
                        .create();
            case Citizen:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_citizen_icon)
                        .addImageID(R.drawable.ic_citizen_image)
                        .create();
            case Witch:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_witch_icon)
                        .addImageID(R.drawable.ic_witch_image)
                        .create();
            case Hunter:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_hunter_icon)
                        .addImageID(R.drawable.ic_hunter_image)
                        .create();
            case Milkman:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_milkman_icon)
                        .addImageID(R.drawable.ic_milkman_image)
                        .create();
            case Roommates:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.ic_roomates_icon)
                        .addImageID(R.drawable.ic_roomates_image)
                        .create();
        }

        // TODO: 06-Jan-19 Add error / warning; character missing
        return new CharacterItem();
    }

    public CharacterItem getCharacter(int position) {
        return characterItemList.get(position);
    }

    public List<CharacterItem> getCharacters() {
        return characterItemList;
    }

    public int getSize() {
        return characterItemList.size();
    }
}
