package dummies.palermo.PlayMenuFragments;

import android.content.Intent;
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

public class PlayMenuSingleMultiFragment extends PlayMenuFragment {

    @Override
    protected int currentPage() {
        return 2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_menu_single_multi, container, false);

        ImageView singleButton = view.findViewById(R.id.play_menu_single_device_button);
        ImageView multiButton = view.findViewById(R.id.play_menu_multi_device_button);

        singleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Single device game
                Intent intent = NewGameActivity.newIntent(getActivity(), false);
                startActivity(intent);
            }
        });

        multiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Multi device game
                setMenuPage(currentPage() + 1);
            }
        });

        return view;
    }
}
