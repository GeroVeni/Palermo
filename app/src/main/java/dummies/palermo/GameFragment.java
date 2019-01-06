package dummies.palermo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dummies.palermo.Models.SaveGame;

public class GameFragment extends Fragment {

    public static final String ARG_TITLE = "title";
    public static final String ARG_COUNT = "playerCount";
    public static final String ARG_IS_MULTI = "isMulti";

    SaveGame saveGame;

    public static GameFragment newInstance(String title, int playerCount, boolean isMultiDevice) {

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_COUNT, playerCount);
        args.putBoolean(ARG_IS_MULTI, isMultiDevice);

        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);



        return view;
    }
}
