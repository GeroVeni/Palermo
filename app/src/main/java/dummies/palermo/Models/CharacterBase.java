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
        // Policeman
        int imgID = R.drawable.sidemenu_acount;
        CharacterItem item = new CharacterItem.Builder()
                .addIconID(imgID)
                .addImageID(imgID)
                .addTitle("Policeman")
                .addSubtitle("The Law")
                .addInfo("Fuck the police, coming straight from the underground")
                .create();
        characterItemList.add(item);

        // Whore
        imgID = R.drawable.sidemenu_settings;
        item = new CharacterItem.Builder()
                .addIconID(imgID)
                .addImageID(imgID)
                .addTitle("Whore")
                .addSubtitle("The lookout")
                .addInfo("I'm such a fucking whore; I love it")
                .create();
        characterItemList.add(item);
    }



    public CharacterItem getCharacter(int position) {
        return characterItemList.get(position);
    }

    public List<CharacterItem> getCharacters() {
        return characterItemList;
    }
}
