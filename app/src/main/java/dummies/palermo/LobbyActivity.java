package dummies.palermo;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

public class LobbyActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LobbyFragment();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LobbyFragment.PERMISSIONS_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                String text;
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    text = "Permission accepted";
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    text = "Permission denied";
                }
                Snackbar.make(findViewById(android.R.id.content),
                        text, Snackbar.LENGTH_SHORT).show();
                return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
