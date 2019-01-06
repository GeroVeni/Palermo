package dummies.palermo.Characters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dummies.palermo.Models.CharacterBase;
import dummies.palermo.Models.CharacterItem;
import dummies.palermo.R;

public class CharactersBaseFragment extends Fragment {

    RecyclerView recyclerView;
    CharactersFragment parentFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_base, container, false);

        recyclerView = view.findViewById(R.id.fragment_characters_base_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        parentFragment = (CharactersFragment)getParentFragment();

        setupAdapter();

        return view;
    }

    private void setupAdapter() {
        if (isAdded()) {
            recyclerView.setAdapter(new CharacterAdapter());
        }
    }

    private class CharacterHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        int characterID;

        ImageView icon;
        TextView title;
        TextView subtitle;

        public CharacterHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.character_item_small_icon);
            title = itemView.findViewById(R.id.character_item_small_title);
            subtitle = itemView.findViewById(R.id.character_item_small_subtitle);

            itemView.setOnClickListener(this);
        }

        void bindCharacter(int characterID) {
            this.characterID = characterID;
            CharacterItem item = CharacterBase.getCharacterBase().getCharacter(characterID);

            icon.setImageResource(item.getIconID());
            title.setText(item.getTitle());
            subtitle.setText(item.getSubtitle());
        }

        @Override
        public void onClick(View view) {
            // Open character view pager
            Snackbar.make(view, "Open character " + title.getText().toString(),
                    Snackbar.LENGTH_SHORT).show();
            parentFragment.onOpenCharacter(characterID);
        }
    }

    private class CharacterAdapter extends RecyclerView.Adapter<CharacterHolder> {

        @NonNull
        @Override
        public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.character_item_small, parent, false);
            return new CharacterHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {
            holder.bindCharacter(position);
        }

        @Override
        public int getItemCount() {
            return CharacterBase.getCharacterBase().getSize();
        }
    }
}
