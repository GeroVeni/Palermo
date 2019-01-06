package dummies.palermo.Characters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dummies.palermo.Models.CharacterBase;
import dummies.palermo.R;

public class CharactersPagerFragment extends Fragment {

    public static final String ARG_CHAR_ID = "characterID";

    ViewPager viewPager;

    public static CharactersPagerFragment newInstance(int characterID) {

        Bundle args = new Bundle();
        args.putInt(ARG_CHAR_ID, characterID);

        CharactersPagerFragment fragment = new CharactersPagerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_single, container, false);

        viewPager = view.findViewById(R.id.fragment_characters_single_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int characterID) {
                return CharactersPageFragment.newInstance(characterID);
            }

            @Override
            public int getCount() {
                return CharacterBase.getCharacterBase().getCharacters().size();
            }
        });

        int currentItem = getArguments().getInt(ARG_CHAR_ID);
        viewPager.setCurrentItem(currentItem);

        return view;
    }
}
