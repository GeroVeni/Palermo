package dummies.palermo;

import android.support.v4.app.Fragment;

public class NewHostActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NewHostFragment();
    }
}
