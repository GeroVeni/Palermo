package dummies.palermo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class NewGameActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewGameFragment();
    }
}
