package dummies.palermo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dummies.palermo.Rules;

public class GameMaker {
    private boolean multiDevice;
    private String title;
    private int suggestedPlayerCount;
    private List<Character> characters;
    private List<Integer> characterCounts;

    private static GameMaker instance = null;

    private GameMaker() {
        characters = new ArrayList<>();
        characterCounts = new ArrayList<>();
        for (Character character : Character.values()) {
            characters.add(character);
        }
        reset();
    }

    public static GameMaker getInstance() {
        if (instance == null) { instance = new GameMaker(); }
        return instance;
    }

    public boolean isMultiDevice() {
        return multiDevice;
    }

    public void setMultiDevice(boolean multiDevice) {
        this.multiDevice = multiDevice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSuggestedPlayerCount() {
        return suggestedPlayerCount;
    }

    public void setSuggestedPlayerCount(int suggestedPlayerCount) {
        this.suggestedPlayerCount = suggestedPlayerCount;
    }

    public void setDefaultCharacters() {
        // TODO: 29-Jan-19 Set default characters depending on the suggestedPlayerCount
    }

    public Character getCharacter(int position) {
        return characters.get(position);
    }

    public int getCharacterCount(int position) {
        return characterCounts.get(position);
    }

    public void setCharacterCount(int position, int value) throws Exception {
        // TODO: 29-Jan-19 Add checks for character counts
        characterCounts.set(position, value);
    }

    public int getCharactersSize() {
        return characters.size();
    }

    public void reset() {
        multiDevice = false;
        title = "";
        suggestedPlayerCount = Rules.MIN_PLAYERS;
        characterCounts.clear();
        for (int i = 0; i < characters.size(); ++i) {
            characterCounts.add(0);
        }
    }
}
