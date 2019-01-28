package dummies.palermo.characters;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dummies.palermo.models.CharacterBase;
import dummies.palermo.models.CharacterItem;
import dummies.palermo.R;

public class CharactersBaseFragment extends Fragment {

    public static final int CHARACTERS_PER_ROW = 2;

    RecyclerView recyclerView;
    Parcelable recyclerViewState = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters_base, container, false);

        recyclerView = view.findViewById(R.id.fragment_characters_base_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), CHARACTERS_PER_ROW));

        setupAdapter();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerViewState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }
    }

    @Override
    public void onPause() {
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        super.onPause();
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

        public CharacterHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.character_item_small_icon);

            itemView.setOnClickListener(this);
        }

        void bindCharacter(int characterID) {
            this.characterID = characterID;
            CharacterItem item = CharacterBase.getCharacterBase().getCharacterItem(characterID);

            icon.setImageResource(item.getIconID());
        }

        @Override
        public void onClick(View view) {
            // Open character view pager
            ((CharactersFragment)getParentFragment())
                    .onOpenCharacter(characterID);
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
