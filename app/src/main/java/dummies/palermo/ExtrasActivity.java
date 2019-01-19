package dummies.palermo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dummies.palermo.Characters.CharactersFragment;

public class ExtrasActivity extends SideMenuActivity {

    public static final String EXTRA_MENU_ITEM_ID = "com.dummies.menu_item_id";

    public static Intent newIntent(Context packageContext, @IdRes int menuItemID) {
        Intent intent = new Intent(packageContext, ExtrasActivity.class);
        intent.putExtra(EXTRA_MENU_ITEM_ID, menuItemID);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int menuItemID = getIntent().getIntExtra(EXTRA_MENU_ITEM_ID, 0);
        onSideItemClicked(menuItemID);
    }

    @Override
    void onSideItemClicked(int id) {
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

        setFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
