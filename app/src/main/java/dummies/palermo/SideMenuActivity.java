package dummies.palermo;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import dummies.palermo.MainMenuActivity;
import dummies.palermo.MainMenuFragment;
import dummies.palermo.R;

public abstract class SideMenuActivity extends AppCompatActivity {

    public static final String TAG = "SideMenuActivity";

    abstract void onSideItemClicked(int id);

    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.activity_nav_drawer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes the support title bar and makes the app fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getLayoutRes());

        // Load ad
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("1A0247517010645472F63A7324CE6AF0").build();
        adView.loadAd(adRequest);

        // Side menu buttons
        int [] menuItemIds = {
                R.id.side_menu_item_account,
                R.id.side_menu_item_settings,
                R.id.side_menu_item_characters,
                R.id.side_menu_item_rules,
                R.id.side_menu_item_info
        };
        for (int menuItemId : menuItemIds) {
            TextView item = findViewById(menuItemId);
            item.setOnClickListener(new SideMenuButtonOnClickListener());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    class SideMenuButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            closeSideMenu();
            onSideItemClicked(v.getId());
        }
    }

    public void openSideMenu() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    public void closeSideMenu() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
