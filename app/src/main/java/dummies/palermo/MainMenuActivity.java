package dummies.palermo;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

public class MainMenuActivity extends SideMenuActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainMenuFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        // TODO: 20-Jan-19 Find a more clever way to not duplicate this function in two places
        // MainMenu activity AND SideMenuActivity
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        MainMenuFragment fragment = (MainMenuFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (fragment.getPlayMenuPage() != 0) {
            int page = fragment.getPlayMenuPage();
            fragment.setPlayMenuPage(page - 1);
            return;
        }

        super.onBackPressed();

    }

    @Override
    void onSideItemClicked(@IdRes int id) {
        Intent intent = ExtrasActivity.newIntent(this, id);
        startActivity(intent);
    }
}
