package dummies.palermo;

import android.support.v4.app.Fragment;

public class NewJoinActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NewJoinFragment();
    }
}
