package dummies.palermo.models;

import java.util.HashMap;
import java.util.Map;

import dummies.palermo.Rules;

public class GameMaker {
    public boolean isMultiDevice;
    public String title;
    public int suggestedPlayerCount;
    public Map<Character, Integer> characters;

    private static GameMaker instance = null;

    private GameMaker() {
        reset();
    }

    public static GameMaker getInstance() {
        if (instance == null) { instance = new GameMaker(); }
        return instance;
    }

    public void reset() {
        isMultiDevice = false;
        title = "";
        suggestedPlayerCount = Rules.MIN_PLAYERS;
        characters = new HashMap<>();
    }
}
