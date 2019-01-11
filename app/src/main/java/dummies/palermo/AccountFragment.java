package dummies.palermo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AccountFragment extends Fragment {

    boolean userLoggedIn;

    CustomProgressBar bar;
    ImageView profilePicture;
    TextView usernameView;
    Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        bar = new CustomProgressBar();
        bar.bar = view.findViewById(R.id.fragment_account_bar);
        bar.progressText = view.findViewById(R.id.fragment_account_progress_text);

        profilePicture = view.findViewById(R.id.fragment_account_profile_image);
        usernameView = view.findViewById(R.id.fragment_account_username_text);
        loginButton = view.findViewById(R.id.fragment_account_login_button);

        userLoggedIn = false;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLoggedIn = !userLoggedIn;
                if (userLoggedIn) {
                    profilePicture.setImageResource(R.drawable.side_menu_account);
                    usernameView.setText("George Venizelos");
                    loginButton.setText("Logout");
                    loginButton.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.ic_logout, 0);
                } else {
                    profilePicture.setImageResource(R.drawable.side_menu_account_icon_placeholder);
                    usernameView.setText("");
                    loginButton.setText("Login");
                    loginButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Animate stats
        ObjectAnimator animator = ObjectAnimator.ofInt(bar, "progress", 0, 25);
        animator.setDuration(700);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    class CustomProgressBar {
        private int progress;

        ProgressBar bar;
        TextView progressText;

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
            bar.setProgress(progress);
            progressText.setText(String.valueOf(progress));
        }
    }
}
