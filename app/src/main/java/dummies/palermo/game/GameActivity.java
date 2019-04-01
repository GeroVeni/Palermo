package dummies.palermo.game;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dummies.palermo.models.Character;
import dummies.palermo.models.CharacterBase;
import dummies.palermo.models.CharacterItem;
import dummies.palermo.models.GameMaker;
import dummies.palermo.models.Player;
import dummies.palermo.models.SaveGame;
import dummies.palermo.R;
import dummies.palermo.SingleFragmentActivity;

public class GameActivity extends SingleFragmentActivity {

    public static final String TAG = "GameActivity";

    private GameMaker gameMaker;
    private SaveGame saveGame;
    private int currentPosition;

    private List<Integer> characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameMaker = GameMaker.getInstance();

        String title = gameMaker.getTitle();
        int playerCount = gameMaker.getSuggestedPlayerCount();

        saveGame = new SaveGame(title, playerCount);
        currentPosition = 0;
        assignCharacters();
        showPlayer(currentPosition);
    }

    @Override
    protected Fragment createFragment() {
        return new GameFirstStageFragment();
    }

    void assignCharacters() {
        characters = new ArrayList<>();
        for (int i = 0; i < gameMaker.getCharactersSize(); i++) {
            for (int j = 0; j < gameMaker.getCharacterCount(i); j++) {
                characters.add(i);
            }
        }
        Collections.shuffle(characters);

        // TODO: 22-Jan-19 Add check when player size is bigger than character size
    }

    private void showPlayer(int position) {
        FragmentManager fm = getSupportFragmentManager();

        int characterId = characters.get(position);
        CharacterItem character = CharacterBase.getCharacterBase().getCharacterItem(characterId);
        Fragment fragment = GameFirstStageFragment.newInstance(character.getImageID());

        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    public void onPlayerFinished() {
        currentPosition ++;
        showPlayer(currentPosition);
    }
}
