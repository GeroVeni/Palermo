package dummies.palermo;

import android.support.v4.app.Fragment;

public class CharacterSelectActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CharacterSelectFragment();
    }
}
