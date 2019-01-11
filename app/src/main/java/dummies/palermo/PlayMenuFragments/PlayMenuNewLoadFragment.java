package dummies.palermo.PlayMenuFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dummies.palermo.LoadGameActivity;
import dummies.palermo.NewGameActivity;
import dummies.palermo.R;

public class PlayMenuNewLoadFragment extends PlayMenuFragment {

    @Override
    protected int currentPage() {
        return 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_menu_new_load, container, false);

        ImageView newGameButton = view.findViewById(R.id.play_menu_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new game activity
                setMenuPage(currentPage() + 1);
            }
        });

        ImageView loadGameButton = view.findViewById(R.id.play_menu_load_game_button);
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start load game activity
                Intent intent = new Intent(getActivity(), LoadGameActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
