package dummies.palermo.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dummies.palermo.models.CharacterBase;
import dummies.palermo.models.Player;
import dummies.palermo.models.SaveGame;
import dummies.palermo.R;
import dummies.palermo.SingleFragmentActivity;

public class GameActivity extends SingleFragmentActivity {

    public static final String TAG = "GameActivity";

    private SaveGame saveGame;
    private int currentPosition;

    public static final String EXTRA_TITLE = "com.dummies.palermo.extraTitle";
    public static final String EXTRA_PLAYER_COUNT = "com.dummies.palermo.extraPlayerCount";

    public static Intent newIntent(Context context, String title, int playerCount) {
        Intent intent = new Intent(context, GameActivity.class);

        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_PLAYER_COUNT, playerCount);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        int playerCount = getIntent().getIntExtra(EXTRA_PLAYER_COUNT, 0);

        saveGame = new SaveGame(title, playerCount);
        Log.i(TAG, "player count: " + playerCount);
        currentPosition = 0;
        assignCharacters(saveGame.players);
        showPlayer(currentPosition);
    }

    @Override
    protected Fragment createFragment() {
        return new GameFirstStageFragment();
    }

    void assignCharacters(List<Player> players) {
        ArrayList<Integer> characters = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            characters.add(i);
        }
        Collections.shuffle(characters);

        // TODO: 22-Jan-19 Add check when player size is bigger then character size

        for (int i = 0; i < players.size(); i++) {
            players.get(i).character = CharacterBase.getCharacterBase().getCharacterItem(i);
            Log.d(TAG, i + " -> " + characters.get(i).toString());
        }
    }

    private void showPlayer(int position) {
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = GameFirstStageFragment.newInstance(saveGame.players.get(position)
                .character.getImageID());

        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    public void onPlayerFinisher() {
        currentPosition ++;
        showPlayer(currentPosition);
    }
}
