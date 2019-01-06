package dummies.palermo.Models;

import android.util.ArrayMap;

import java.util.Map;

public class Stats {
    // Total stats
    private int gamesPlayed;
    private int gamesWon;

    // Good stats
    private int gamesPlayedGood;
    private int gamesWonGood;

    // Per role stats
    private Map<Character, Integer> gamesPlayedPerCharacter;
    private Map<Character, Integer> gamesWonPerCharacter;

    public Stats() {
        gamesPlayedPerCharacter = new ArrayMap<>();
        gamesWonPerCharacter = new ArrayMap<>();
        clearGames();
    }

    public void addGame(Character character, boolean won) {
        boolean isGood = Character.isCharacterGood(character);
        ++gamesPlayed;
        if (isGood) {
            ++gamesPlayedGood;
        }
        int val = gamesPlayedPerCharacter.get(character);
        gamesPlayedPerCharacter.put(character, val + 1);

        if (won) {
            ++gamesWon;
            if (isGood) {
                ++gamesWonGood;
            }
            val = gamesWonPerCharacter.get(character);
            gamesWonPerCharacter.put(character, val + 1);
        }
    }

    private void clearGames() {
        gamesPlayed = 0;
        gamesWon = 0;
        gamesPlayedGood = 0;
        gamesWonGood = 0;

        for (Character character : Character.values()) {
            gamesPlayedPerCharacter.put(character, 0);
            gamesWonPerCharacter.put(character, 0);
        }
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesPlayedGood() {
        return gamesPlayedGood;
    }

    public int getGamesWonGood() {
        return gamesWonGood;
    }
}
