package dummies.palermo.PlayMenuFragments;

import android.support.v4.app.Fragment;

import dummies.palermo.MainMenuFragment;

abstract public class PlayMenuFragment extends Fragment {
    protected void setMenuPage(int page) {
        ((MainMenuFragment)getParentFragment())
                .setPlayMenuPage(page);
    }

    abstract protected int currentPage();
}
