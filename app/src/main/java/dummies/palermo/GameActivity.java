package dummies.palermo;

import android.support.v4.app.Fragment;

public class GameActivity extends SingleFragmentActivity {

    public static final String EXTRA_TITLE = "com.dummies.palermo.title";
    public static final String EXTRA_COUNT = "com.dummies.palermo.playerCount";
    public static final String EXTRA_IS_MULTI = "com.dummies.palermo.isMulti";

    @Override
    protected Fragment createFragment() {
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        int playerCount = getIntent().getIntExtra(EXTRA_COUNT, 2);
        boolean isMultiDevice = getIntent().getBooleanExtra(EXTRA_IS_MULTI, false);
        return GameFragment.newInstance(title, playerCount, isMultiDevice);
    }
}
