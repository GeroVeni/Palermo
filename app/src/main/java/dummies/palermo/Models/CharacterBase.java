package dummies.palermo.Models;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

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
            case Policeman:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.sidemenu_acount)
                        .addImageID(R.drawable.sidemenu_acount)
                        .addTitle("Policeman")
                        .addSubtitle("The Law")
                        .addInfo("Fuck the police, coming straight from the underground")
                        .create();
            case ThiefKnown:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.sidemenu_characters)
                        .addImageID(R.drawable.sidemenu_characters)
                        .addTitle("Thief Known")
                        .addSubtitle("Out and about")
                        .addInfo("The decoy")
                        .create();
            case ThiefSecret:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.sidemenu_info)
                        .addImageID(R.drawable.sidemenu_info)
                        .addTitle("Thief Secret")
                        .addSubtitle("???")
                        .addInfo("Who knows ... ?")
                        .create();
            case Whore:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.sidemenu_settings)
                        .addImageID(R.drawable.sidemenu_settings)
                        .addTitle("Whore")
                        .addSubtitle("The lookout")
                        .addInfo("I'm such a fucking whore; I love it")
                        .create();
            case Doctor:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.sidemenu_rules)
                        .addImageID(R.drawable.doctor_large)
                        .addTitle("Doctor")
                        .addSubtitle("The saver")
                        .addInfo("An apple a day keeps the doctor away")
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
