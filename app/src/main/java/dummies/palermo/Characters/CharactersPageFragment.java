package dummies.palermo.Characters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dummies.palermo.Models.CharacterBase;
import dummies.palermo.Models.CharacterItem;
import dummies.palermo.R;

public class CharactersPageFragment extends Fragment {

    public static final String ARG_CHAR_ID = "characterID";

    public static CharactersPageFragment newInstance(int characterID) {

        Bundle args = new Bundle();
        args.putInt(ARG_CHAR_ID, characterID);

        CharactersPageFragment fragment = new CharactersPageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_item_large, container, false);

        int characterID = getArguments().getInt(ARG_CHAR_ID);
        CharacterItem item = CharacterBase.getCharacterBase().getCharacter(characterID);

        ImageView imageView = view.findViewById(R.id.character_item_large_image);
        TextView infoView = view.findViewById(R.id.character_item_large_info);

        imageView.setImageResource(item.getImageID());
        infoView.setText(item.getInfoID());
        infoView.setTextColor(getResources().getColor(item.getColorID()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharactersFragment fragment = (CharactersFragment)getParentFragment();
                fragment.onCloseCharacter();
            }
        });

        return view;
    }
}
