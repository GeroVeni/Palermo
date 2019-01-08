package dummies.palermo.PlayMenuFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dummies.palermo.MainMenuFragment;
import dummies.palermo.R;

public class PlayMenuMainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_menu_play_button, container, false);

        ImageView playButton = view.findViewById(R.id.play_menu_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuFragment)getParentFragment())
                        .setPlayMenuPage(1);
            }
        });

        return view;
    }
}
