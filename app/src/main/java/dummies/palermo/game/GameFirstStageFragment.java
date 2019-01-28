package dummies.palermo.game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dummies.palermo.R;

public class GameFirstStageFragment extends Fragment {

    public static final String ARG_CHARACTER_IMG_ID = "argCharacterImgId";

    boolean isConfirmed;

    EditText playerNameEditText;
    Button confirmButton;
    ImageView characterImageView;

    public static GameFirstStageFragment newInstance(@DrawableRes int characterImageId) {

        Bundle args = new Bundle();
        args.putInt(ARG_CHARACTER_IMG_ID, characterImageId);

        GameFirstStageFragment fragment = new GameFirstStageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isConfirmed = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_stage, container, false);

        // Extract fragment arguments
        @DrawableRes int characterImageId = getArguments().getInt(ARG_CHARACTER_IMG_ID, 0);

        playerNameEditText = view.findViewById(R.id.fragment_first_stage_name_edit_text);
        confirmButton = view.findViewById(R.id.fragment_first_stage_confirm_button);
        characterImageView = view.findViewById(R.id.fragment_first_stage_character_image_view);

        characterImageView.setAlpha(0f);
        if (characterImageId != 0) {
            characterImageView.setImageResource(characterImageId);
        }

        confirmButton.setText("Confirm");
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConfirmed) {
                    String name = playerNameEditText.getText().toString();
                    if (name.isEmpty()) {
                        Toast.makeText(getActivity(), "Please input a player name",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int animationDuration = 500;
                    isConfirmed = true;

                    Animator imageAnim = ObjectAnimator.ofFloat(characterImageView,
                            "alpha", 0f, 1f);
                    imageAnim.setDuration(animationDuration);
                    imageAnim.setInterpolator(new LinearInterpolator());
                    imageAnim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            confirmButton.setText("Next Player");
                        }
                    });
                    imageAnim.start();
                } else {
                    // Proceed to the next character

                }
            }
        });

        return view;
    }
}
