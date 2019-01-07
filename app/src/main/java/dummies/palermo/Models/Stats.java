package dummies.palermo.Models;

import android.util.ArrayMap;

import java.util.Map;

public class Stats {

    // Per role stats
    private Map<Character, CharacterStats> statsPerCharacter;

    public Stats() {
        statsPerCharacter = new ArrayMap<>();
        clearGames();
    }

    public void addGame(Character character, InfoGame game, InfoPlayer player) {
        CharacterStats characterStats = statsPerCharacter.get(character);
        characterStats.add(game, player);
    }

    private void clearGames() {

        for (Character character : Character.values()) {
            statsPerCharacter.put(character, new CharacterStats());
        }
    }

    public static class CharacterStats {
        private int totalAlive;
        private int totalDeadDay;
        private int totalDeadNight;

        private int wonAlive;
        private int wonDeadDay;
        private int wonDeadNight;

        public CharacterStats() {
            totalAlive = 0;
            totalDeadDay = 0;
            totalDeadNight = 0;

            wonAlive = 0;
            wonDeadDay = 0;
            wonDeadNight = 0;
        }

        public void add(InfoGame game, InfoPlayer player) {
            if (game == InfoGame.TOTAL
                    || player == InfoPlayer.TOTAL
                    || player == InfoPlayer.DEAD) { return; }

            switch (player) {
                case ALIVE:
                    ++totalAlive;
                    if (game == InfoGame.WON) { ++wonAlive; }
                    return;
                case DEAD_DAY:
                    ++totalDeadDay;
                    if (game == InfoGame.WON) { ++wonDeadDay; }
                    return;
                default: // DEAD_NIGHT
                    ++totalDeadNight;
                    if (game == InfoGame.WON) { ++wonDeadNight; }
            }
        }

        public int get(InfoGame game, InfoPlayer player) {
            if (game == InfoGame.LOST) {
                return get(InfoGame.TOTAL, player) - get(InfoGame.WON, player);
            }
            if (player == InfoPlayer.TOTAL) {
                return get(game, InfoPlayer.ALIVE) + get(game, InfoPlayer.DEAD);
            }
            if (player == InfoPlayer.DEAD) {
                return get(game, InfoPlayer.DEAD_DAY) + get(game, InfoPlayer.DEAD_NIGHT);
            }

            if (game == InfoGame.TOTAL) {
                switch (player) {
                    case ALIVE: return totalAlive;
                    case DEAD_DAY: return totalDeadDay;
                    default: return totalDeadNight; // DEAD_NIGHT
                }
            }

            // WON
            switch (player) {
                case ALIVE: return wonAlive;
                case DEAD_DAY: return wonDeadDay;
                default: return wonDeadNight; // DEAD_NIGHT
            }
        }
    }

    public enum InfoGame {
        TOTAL,
        WON,
        LOST
    }

    public enum InfoPlayer {
        TOTAL,
        ALIVE,
        DEAD,
        DEAD_DAY,
        DEAD_NIGHT
    }
}
