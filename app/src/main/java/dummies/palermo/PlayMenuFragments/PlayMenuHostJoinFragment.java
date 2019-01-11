package dummies.palermo.PlayMenuFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dummies.palermo.NewGameActivity;
import dummies.palermo.R;

public class PlayMenuHostJoinFragment extends PlayMenuFragment {
    @Override
    protected int currentPage() {
        return 3;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_menu_host_join, container, false);

        ImageView hostButton = view.findViewById(R.id.play_menu_host_game_button);
        ImageView joinButton = view.findViewById(R.id.play_menu_join_game_button);

        hostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Host game
                startActivity(NewGameActivity.newIntent(getActivity(), true));
                Snackbar.make(view, "Hosting game", Snackbar.LENGTH_SHORT).show();
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Join game
                Snackbar.make(view, "Joining game", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
