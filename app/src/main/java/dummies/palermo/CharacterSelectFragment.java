package dummies.palermo;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dummies.palermo.game.GameActivity;
import dummies.palermo.models.Character;
import dummies.palermo.models.GameMaker;

public class CharacterSelectFragment extends Fragment {

    GameMaker gameMaker;

    RecyclerView recyclerView;
    ImageView createButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameMaker = GameMaker.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_select, container, false);

        recyclerView = view.findViewById(R.id.fragment_character_select_recycler_view);
        createButton = view.findViewById(R.id.fragment_character_select_create_button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecorator dividerItemDecoration = new DividerItemDecorator(
                ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(new CharacterAdapter());

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 29-Jan-19 Create save game point
                if (gameMaker.isMultiDevice()) {
                    // multi device game as host
                    startActivity(LobbyActivity.newIntent(getActivity(), true,
                            gameMaker.getTitle(), gameMaker.getSuggestedPlayerCount()));
                } else {
                    startActivity(new Intent(getActivity(), GameActivity.class));
                }
            }
        });

        updateUI();

        return view;
    }

    void updateUI() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public class DividerItemDecorator extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public DividerItemDecorator(Drawable divider) {
            mDivider = divider;
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i <= childCount - 2; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }
    }

    class CharacterHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        int characterPosition;

        TextView characterNameTextView;
        TextView characterAmountTextView;
        ImageView decreaseButton;
        ImageView increaseButton;

        CharacterHolder(View itemView) {
            super(itemView);

            characterNameTextView = itemView.findViewById(R.id.entry_character_select_name);
            characterAmountTextView = itemView.findViewById(R.id.entry_character_select_amount);
            decreaseButton = itemView.findViewById(R.id.entry_character_select_decrease);
            increaseButton = itemView.findViewById(R.id.entry_character_select_increase);

            increaseButton.setOnClickListener(this);
            decreaseButton.setOnClickListener(this);
        }

        void bindHolder(int position) {
            characterPosition = position;
            Character character = gameMaker.getCharacter(position);
            int charCount = gameMaker.getCharacterCount(position);

            characterNameTextView.setText(character.toString());
            characterAmountTextView.setText(String.valueOf(charCount));

            if (charCount == 0) {
                decreaseButton.setVisibility(View.INVISIBLE);
            } else {
                decreaseButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            int change;
            if (view.getId() == decreaseButton.getId()) {
                change = -1;
            } else {
                change = +1;
            }
            int charCount = gameMaker.getCharacterCount(characterPosition);
            try {
                if (charCount + change < 0) change = -charCount;
                gameMaker.setCharacterCount(characterPosition, charCount + change);
                updateUI();
            } catch (Exception e) {
                // Operation invalid; show user cause
                Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    class CharacterAdapter extends RecyclerView.Adapter<CharacterHolder> {
        @NonNull
        @Override
        public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.entry_character_select, parent, false);
            return new CharacterHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {
            holder.bindHolder(position);
        }

        @Override
        public int getItemCount() {
            return gameMaker.getCharactersSize();
        }
    }
}
