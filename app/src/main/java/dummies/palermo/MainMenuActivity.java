package dummies.palermo;

import android.media.Image;
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

import dummies.palermo.Characters.CharactersFragment;

public class MainMenuActivity extends AppCompatActivity {

    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.activity_nav_drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes the support title bar and makes the app fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getLayoutRes());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MainMenuFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        // Side menu buttons
        int [] menuItemIds = {
                R.id.side_menu_item_account,
                R.id.side_menu_item_settings,
                R.id.side_menu_item_characters,
                R.id.side_menu_item_rules,
                R.id.side_menu_item_info
        };
        for (int i = 0; i < menuItemIds.length; i++) {
            TextView item = findViewById(menuItemIds[i]);
            item.setOnClickListener(new SideMenuButtonOnClickListener());
        }

        // TODO: 06-Jan-19 Temp back button
        ImageView accountButton = findViewById(R.id.side_menu_header_icon);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeSideMenu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,
                                new MainMenuFragment())
                        .commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

    class SideMenuButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            closeSideMenu();
            onSideItemClicked(v.getId());
        }
    }

    void onSideItemClicked(int id) {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;

        switch (id) {
            case R.id.side_menu_item_account:
                fragment = new AccountFragment();
                break;
            case R.id.side_menu_item_settings:
                fragment = new SettingsFragment();
                break;
            case R.id.side_menu_item_characters:
                fragment = new CharactersFragment();
                break;
            case R.id.side_menu_item_info:
                fragment = new InfoFragment();
                break;
            case R.id.side_menu_item_rules:
                fragment = new RulesFragment();
                break;
            default:
                fragment = null;
                break;
        }

        if (fragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

    }
}
