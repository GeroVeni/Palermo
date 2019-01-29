package dummies.palermo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import dummies.palermo.game.GameActivity;
import dummies.palermo.models.GameMaker;

public class NewGameFragment extends Fragment {

    EditText titleText;
    EditText playerCountText;
    ImageView decreaseCountButton;
    ImageView increaseCountButton;
    Button createButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);

        titleText = view.findViewById(R.id.fragment_new_game_title);
        playerCountText = view.findViewById(R.id.fragment_new_game_player_count);
        decreaseCountButton = view.findViewById(R.id.fragment_new_game_decrease_button);
        increaseCountButton = view.findViewById(R.id.fragment_new_game_increase_button);
        createButton = view.findViewById(R.id.fragment_new_game_create_button);

        titleText.setText("Game Title");
        
        playerCountText.setText(String.valueOf(Rules.MIN_PLAYERS));
        playerCountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateCount(getPlayers());
            }
        });

        // TODO: 29-Jan-19 Implement onHoldListeners to increase/decrease numbers faster
        decreaseCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = getPlayers();
                --val;
                if (val < Rules.MIN_PLAYERS) { val = Rules.MIN_PLAYERS; }
                updateCount(val);
            }
        });

        increaseCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = getPlayers();
                ++val;
                updateCount(val);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start game with current settings
                String title = titleText.getText().toString();
                int playerCount = getPlayers();

                GameMaker gm = GameMaker.getInstance();
                gm.setTitle(title);
                gm.setSuggestedPlayerCount(playerCount);

                startActivity(new Intent(getActivity(), CharacterSelectActivity.class));

//                if (isMultiDevice) {
//                    Intent intent = LobbyActivity.newIntent(getActivity(), true, title,
//                            playerCount);
//                    startActivity(intent);
//                } else {
//                    Intent intent = GameActivity.newIntent(getActivity(), title, playerCount);
//                    startActivity(intent);
//                }
            }
        });

        return view;
    }

    private void updateCount(int newValue) {
        String newString = String.valueOf(newValue);
        String prevString = playerCountText.getText().toString();
        Log.i("TAG", String.format("\"%s\" -> \"%s\"", prevString, newString));
        if (prevString.equals(newString)) return;
        playerCountText.setText(newString);
    }

    private int getPlayers() {
        String content = playerCountText.getText().toString();
        if (content.equals("")) return Rules.MIN_PLAYERS;
        int val = Integer.valueOf(content);
        if (val < Rules.MIN_PLAYERS) return Rules.MIN_PLAYERS;
        return val;
    }
}
