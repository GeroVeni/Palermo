package dummies.palermo.models;

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
            characterItemList.add(getCharacterItem(character));
        }
    }

    private CharacterItem getCharacterItem(Character character) {
        switch (character) {
            case ThiefKnown:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_thief_visible)
                        .addImageID(R.drawable.image_thief_visible)
                        .addTitleID(R.string.character_thief_known_title)
                        .addSubtitleID(R.string.character_thief_known_subtitle)
                        .addInfoID(R.string.character_thief_known_info)
                        .addColorID(R.color.character_thief_known)
                        .create();
            case ThiefSecret:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_thief_hidden)
                        .addImageID(R.drawable.image_thief_hidden)
                        .addTitleID(R.string.character_thief_secret_title)
                        .addSubtitleID(R.string.character_thief_secret_subtitle)
                        .addInfoID(R.string.character_thief_secret_info)
                        .addColorID(R.color.character_thief_secret)
                        .create();
            case Policeman:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_policeman)
                        .addImageID(R.drawable.image_policeman)
                        .addTitleID(R.string.character_policeman_title)
                        .addSubtitleID(R.string.character_policeman_subtitle)
                        .addInfoID(R.string.character_policeman_info)
                        .addColorID(R.color.character_policeman)
                        .create();
            case Whore:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_whore)
                        .addImageID(R.drawable.image_whore)
                        .addTitleID(R.string.character_whore_title)
                        .addSubtitleID(R.string.character_whore_subtitle)
                        .addInfoID(R.string.character_whore_info)
                        .addColorID(R.color.character_whore)
                        .create();
            case Doctor:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_doctor)
                        .addImageID(R.drawable.image_doctor)
                        .addTitleID(R.string.character_doctor_title)
                        .addSubtitleID(R.string.character_doctor_subtitle)
                        .addInfoID(R.string.character_doctor_info)
                        .addColorID(R.color.character_doctor)
                        .create();
            case Citizen:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_citizen)
                        .addImageID(R.drawable.image_citizen)
                        .addTitleID(R.string.character_citizen_title)
                        .addSubtitleID(R.string.character_citizen_subtitle)
                        .addInfoID(R.string.character_citizen_info)
                        .addColorID(R.color.character_citizen)
                        .create();
            case Witch:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_witch)
                        .addImageID(R.drawable.image_witch)
                        .addTitleID(R.string.character_witch_title)
                        .addSubtitleID(R.string.character_witch_subtitle)
                        .addInfoID(R.string.character_witch_info)
                        .addColorID(R.color.character_witch)
                        .create();
            case Hunter:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_hunter)
                        .addImageID(R.drawable.image_hunter)
                        .addTitleID(R.string.character_hunter_title)
                        .addSubtitleID(R.string.character_hunter_subtitle)
                        .addInfoID(R.string.character_hunter_info)
                        .addColorID(R.color.character_hunter)
                        .create();
            case Milkman:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_milkman)
                        .addImageID(R.drawable.image_milkman)
                        .addTitleID(R.string.character_milkman_title)
                        .addSubtitleID(R.string.character_milkman_subtitle)
                        .addInfoID(R.string.character_milkman_info)
                        .addColorID(R.color.character_milkman)
                        .create();
            case Roommates:
                return new CharacterItem.Builder()
                        .addIconID(R.drawable.icon_roomates)
                        .addImageID(R.drawable.image_roomates)
                        .addTitleID(R.string.character_roommates_title)
                        .addSubtitleID(R.string.character_roommates_subtitle)
                        .addInfoID(R.string.character_roommates_info)
                        .addColorID(R.color.character_roommates)
                        .create();
        }

        // TODO: 06-Jan-19 Add error / warning; character missing
        return new CharacterItem();
    }

    public CharacterItem getCharacterItem(int position) {
        return characterItemList.get(position);
    }

    public List<CharacterItem> getCharacters() {
        return characterItemList;
    }

    public int getSize() {
        return characterItemList.size();
    }
}
