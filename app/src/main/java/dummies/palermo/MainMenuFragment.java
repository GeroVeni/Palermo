package dummies.palermo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import dummies.palermo.play_menu_fragments.PlayMenuHostJoinFragment;
import dummies.palermo.play_menu_fragments.PlayMenuMainFragment;
import dummies.palermo.play_menu_fragments.PlayMenuNewLoadFragment;
import dummies.palermo.play_menu_fragments.PlayMenuSingleMultiFragment;

public class MainMenuFragment extends Fragment {

    public static final String TAG = "MainMenuFragment";

    public static final int PLAY_MENU_PAGES = 4;

    View view;
    ViewPager playMenuPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        ImageView image = view.findViewById(R.id.main_menu_side_menu_button);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open side menu
                ((MainMenuActivity)getActivity()).openSideMenu();
            }
        });

        playMenuPager = view.findViewById(R.id.main_menu_play_menu_pager);
        playMenuPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new PlayMenuMainFragment();
                    case 1:
                        return new PlayMenuNewLoadFragment();
                    case 2:
                        return new PlayMenuSingleMultiFragment();
                    case 3:
                        return new PlayMenuHostJoinFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return PLAY_MENU_PAGES;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        final VideoView videoView = view.findViewById(R.id.main_menu_intro_animation_frame);
        Uri uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"
                + R.raw.main_menu_background_video);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }

    public void setPlayMenuPage(int page) {
        playMenuPager.setCurrentItem(page, true);
    }

    public int getPlayMenuPage() { return playMenuPager.getCurrentItem(); }
}
