package dummies.palermo.play_menu_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dummies.palermo.R;

public class PlayMenuMainFragment extends PlayMenuFragment {

    @Override
    protected int currentPage() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_menu_play_button, container, false);

        ImageView playButton = view.findViewById(R.id.play_menu_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMenuPage(1);
            }
        });

        return view;
    }
}
