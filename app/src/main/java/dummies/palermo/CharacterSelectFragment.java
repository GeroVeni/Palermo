package dummies.palermo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dummies.palermo.game.GameActivity;
import dummies.palermo.models.Character;
import dummies.palermo.models.GameMaker;

public class CharacterSelectFragment extends Fragment {

    GameMaker gameMaker;
    boolean charactersUnlocked;

    RecyclerView recyclerView;
    Button unlockCharactersButton;
    Button createButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameMaker = GameMaker.getInstance();
        charactersUnlocked = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_select, container, false);

        recyclerView = view.findViewById(R.id.fragment_character_select_recycler_view);
        unlockCharactersButton = view.findViewById(R.id.fragment_character_select_unlock_characters_button);
        createButton = view.findViewById(R.id.fragment_character_select_create_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new CharacterAdapter());

        unlockCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charactersUnlocked = true;
                updateUI();
            }
        });

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
        int unlockVisibility = View.VISIBLE;
        if (charactersUnlocked) {
            unlockVisibility = View.INVISIBLE;
        }
        unlockCharactersButton.setVisibility(unlockVisibility);
        recyclerView.getAdapter().notifyDataSetChanged();
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

            if (charactersUnlocked) {
                increaseButton.setVisibility(View.VISIBLE);
                if (charCount == 0) {
                    decreaseButton.setVisibility(View.INVISIBLE);
                } else {
                    decreaseButton.setVisibility(View.VISIBLE);
                }
            } else {
                increaseButton.setVisibility(View.INVISIBLE);
                decreaseButton.setVisibility(View.INVISIBLE);
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
