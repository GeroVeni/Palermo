package dummies.palermo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class NewGameActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, boolean isMultiDevice) {
        Intent intent = new Intent(context, NewGameActivity.class);
        intent.putExtra(NewGameFragment.EXTRA_IS_MULTI_DEVICE, isMultiDevice);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new NewGameFragment();
    }
}
