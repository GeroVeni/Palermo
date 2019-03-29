package dummies.palermo.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveGame {

    public static final String TAG = "SaveGame";

    public String title;
    public Date creationDate;
    public Date lastChange;
    public Round round;
    public List<Player> players;

    public SaveGame(String title, int playerCount) {
        this.title = title;
        this.players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            this.players.add(new Player());
        }
        this.round = new Round();
        this.creationDate = new Date();
        this.lastChange = creationDate;
    }
}
