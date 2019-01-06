package dummies.palermo;

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
import android.widget.Switch;

public class NewGameFragment extends Fragment {

    public static final int MAX_PLAYERS = 100;

    EditText titleText;
    EditText playerCountText;
    Button decreaseCountButton;
    Button increaseCountButton;
    Switch modeSwitch;
    Button createButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);

        titleText = view.findViewById(R.id.fragment_new_game_title);
        playerCountText = view.findViewById(R.id.fragment_new_game_player_count);
        decreaseCountButton = view.findViewById(R.id.fragment_new_game_decrease_button);
        increaseCountButton = view.findViewById(R.id.fragment_new_game_increase_button);
        modeSwitch = view.findViewById(R.id.fragment_new_game_mode_switch);
        createButton = view.findViewById(R.id.fragment_new_game_create_button);

        titleText.setText("Game Title");

        playerCountText.setText("2");
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

        decreaseCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = getPlayers();
                --val;
                if (val < 2) { val = 2; }
                updateCount(val);
            }
        });

        increaseCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = getPlayers();
                ++val;
                if (val > MAX_PLAYERS) { val = MAX_PLAYERS; }
                updateCount(val);
            }
        });

        modeSwitch.setChecked(false);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start game with current settings
                String title = titleText.getText().toString();
                int playerCount = getPlayers();
                boolean mode = modeSwitch.isChecked();
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
        if (content.equals("")) return 2;
        int val = Integer.valueOf(content);
        if (val < 2) return 2;
        if (val > MAX_PLAYERS) return MAX_PLAYERS;
        return val;
    }
}
