package dummies.palermo;

import android.support.v4.app.Fragment;

public class LoadGameActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoadGameFragment();
    }
}
