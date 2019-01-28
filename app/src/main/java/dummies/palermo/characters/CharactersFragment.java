package dummies.palermo.characters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dummies.palermo.R;

public class CharactersFragment extends Fragment {

    public static final String OPEN_CHARACTER = "openCharacter";

    FragmentManager fragmentManager;

    boolean isOnBase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getChildFragmentManager();

        isOnBase = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters, container, false);

        Fragment fragment = fragmentManager.findFragmentById(R.id.character_fragment_container);

        if (fragment == null) {
            fragment = new CharactersBaseFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.character_fragment_container, fragment)
                    .commit();
        }

        return view;
    }

    public void onOpenCharacter(int characterID) {
        CharactersPagerFragment fragment = CharactersPagerFragment
                .newInstance(characterID);
        fragmentManager.beginTransaction()
                .replace(R.id.character_fragment_container, fragment)
                .addToBackStack(OPEN_CHARACTER)
                .commit();
        isOnBase = false;
    }

    public void onCloseCharacter() {
        fragmentManager.popBackStack();
        isOnBase = true;
    }

    public boolean onBackPressed() {
        if (isOnBase) { return false; }
        onCloseCharacter();
        return true;
    }
}
