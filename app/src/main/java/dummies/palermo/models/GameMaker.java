package dummies.palermo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dummies.palermo.R;
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
        // check if it is less than the minimum
        if (suggestedPlayerCount < Rules.MIN_PLAYERS) {
            suggestedPlayerCount = Rules.MIN_PLAYERS;
        }

        setDefaultCharacters();
    }

    public void setDefaultCharacters() {
        // TODO: 29-Jan-19 Set default characters depending on the suggestedPlayerCount
        int totalThieves = suggestedPlayerCount / 4;
        int secretThieves = 1;
        int knownThieves = totalThieves - secretThieves;
        int cops = 1;
        int citizens = suggestedPlayerCount - totalThieves - cops;
        try {
            setCharacterCount(Character.getPos(Character.ThiefSecret), secretThieves);
            setCharacterCount(Character.getPos(Character.ThiefKnown), knownThieves);
            setCharacterCount(Character.getPos(Character.Policeman), cops);
            setCharacterCount(Character.getPos(Character.Citizen), citizens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Character getCharacter(int position) {
        return characters.get(position);
    }

    public int getCharacterCount(int position) {
        return characterCounts.get(position);
    }

    public void setCharacterCount(int position, int value) throws Exception {
        // TODO: 29-Jan-19 Add checks for character counts

        // update total player count
        int prev = characterCounts.get(position);
        suggestedPlayerCount += value - prev;
        characterCounts.set(position, value);
    }

    public int getCharactersSize() {
        return characters.size();
    }

    public void reset(int suggestedPlayerCount) {
        multiDevice = false;
        title = "";
        characterCounts.clear();
        for (int i = 0; i < characters.size(); ++i) {
            characterCounts.add(0);
        }
        // TODO: 01-Apr-19 Remember last value used by user
        setSuggestedPlayerCount(suggestedPlayerCount);
    }

    public void reset() {
        reset(Rules.MIN_PLAYERS);
    }
}
