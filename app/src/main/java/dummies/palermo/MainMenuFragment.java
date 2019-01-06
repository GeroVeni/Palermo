package dummies.palermo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.VideoView;

public class MainMenuFragment extends Fragment {

    public static final String TAG = "MainMenuFragment";

    View view;
    FrameLayout playMenuFrame;
    View playButtonView;
    View playOptsView;

    int screenWidth = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main_menu, container, false);

        ImageView image = view.findViewById(R.id.main_menu_side_menu_button);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open side menu
                ((MainMenuActivity)getActivity()).openSideMenu();
            }
        });

        Space space = view.findViewById(R.id.closing_space);
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close side menu
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.main_menu_side_menu_frame);
                if (fragment != null) {
                    fragment = new SideMenuFragment();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                    transaction.hide(fragment);
                    transaction.commit();
                }
            }
        });

        playMenuFrame = view.findViewById(R.id.main_menu_play_menu_frame);
        playButtonView = inflater.inflate(R.layout.play_menu_play_button, playMenuFrame, false);
        playOptsView = inflater.inflate(R.layout.play_menu_new_game_opts, playMenuFrame, false);
        playMenuFrame.addView(playButtonView);
        playMenuFrame.addView(playOptsView);

        resetPlayMenu();

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                screenWidth = playMenuFrame.getWidth();
                Log.i(TAG, "Width: " + screenWidth);
            }
        });

        ImageView playButton = playButtonView.findViewById(R.id.play_menu_play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(playButtonView, "x",
                        0, -screenWidth);
                animator.setDuration(300);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        playButtonView.setVisibility(View.INVISIBLE);
                    }
                });

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(playOptsView, "x",
                        screenWidth, 0);
                animator2.setDuration(300);
                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        playOptsView.setVisibility(View.VISIBLE);
                    }
                });

                AnimatorSet set = new AnimatorSet();
                set.play(animator).with(animator2);
                set.start();
            }
        });

        ImageView newGameButton = playOptsView.findViewById(R.id.play_menu_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new game activity
                Intent intent = new Intent(getActivity(), NewGameActivity.class);
                startActivity(intent);
            }
        });

        ImageView loadGameButton = playOptsView.findViewById(R.id.play_menu_load_game_button);
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start load game activity
                Intent intent = new Intent(getActivity(), LoadGameActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        resetPlayMenu();
    }

    @Override
    public void onResume() {
        super.onResume();

        final VideoView videoView = view.findViewById(R.id.main_menu_intro_animation_frame);
        Uri uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.video_v3);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }

    void resetPlayMenu() {
        playButtonView.setX(0);
        playButtonView.setVisibility(View.VISIBLE);
        playOptsView.setX(screenWidth);
        playOptsView.setVisibility(View.INVISIBLE);
    }
}

//View getPlayMenuView(Context context, int index) {
//    FrameLayout playFrame = new FrameLayout(context);
//    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//    playFrame.setLayoutParams(params);
//    switch (index) {
//        case 0:
//            // Play button
//
//
//    }
//}
