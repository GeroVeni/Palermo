package dummies.palermo.Game;

import android.support.v4.app.Fragment;

import dummies.palermo.SingleFragmentActivity;

public class GameActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new GameFirstStageFragment();
    }
}
