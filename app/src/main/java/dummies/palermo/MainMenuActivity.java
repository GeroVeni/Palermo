package dummies.palermo;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

import dummies.palermo.Characters.CharactersFragment;

public class MainMenuActivity extends SideMenuActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            setFragment(new MainMenuFragment());
        }
    }

    @Override
    void onSideItemClicked(@IdRes int id) {
        Intent intent = ExtrasActivity.newIntent(this, id);
        startActivity(intent);
    }
}
