package dummies.palermo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyFragment extends Fragment {

    public static final int PERMISSIONS_REQUEST_CODE = 1;

    public static final String EXTRA_IS_HOST = "com.dummies.palermo.isHost";
    public static final String EXTRA_TITLE = "com.dummies.palermo.title";
    public static final String EXTRA_SUGGESTED_PLAYERS = "com.dummies.palermo.suggestedPlayers";

    ConnectionsManager manager;
    String title;
    String username;
    List<String> endpoints;
    int suggestedPlayers;
    boolean isHost;

    TextView usernameView;
    TextView titleView;
    TextView playerCountView;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        username = UUID.randomUUID().toString();
        manager = new ConnectionsManager(getActivity(), username);

        endpoints = new ArrayList<>();
        endpoints.add(username);

        // Get extras
        Intent extraIntent = getActivity().getIntent();
        // Check if you are the owner of the lobby
        isHost = extraIntent.getBooleanExtra(EXTRA_IS_HOST, false);
        // Get title extra
        title = extraIntent.getStringExtra(EXTRA_TITLE);
        // Get suggested players
        suggestedPlayers = extraIntent.getIntExtra(EXTRA_SUGGESTED_PLAYERS, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lobby, container, false);

        usernameView = view.findViewById(R.id.fragment_lobby_username);
        titleView = view.findViewById(R.id.fragment_lobby_title_view);
        recyclerView = view.findViewById(R.id.fragment_lobby_recycler_view);
        playerCountView = view.findViewById(R.id.fragment_lobby_player_count);

        usernameView.setText(username);

        titleView.setText(title);

        updatePlayersView(endpoints.size());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NearbyAdapter());

        getPermissions();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isHost) {
            manager.startAdvertising();
        }
    }

    @Override
    public void onStop() {
        manager.stopAdvertising();
        super.onStop();
    }

    private class NearbyHolder extends RecyclerView.ViewHolder {
        TextView view;

        public NearbyHolder(View itemView) {
            super(itemView);
            view = (TextView)itemView;
        }

        void bindNearby(int position) {
            view.setText(endpoints.get(position));
        }
    }

    private class NearbyAdapter extends RecyclerView.Adapter<NearbyHolder> {
        @NonNull
        @Override
        public NearbyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = new TextView(getActivity());
            return new NearbyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NearbyHolder holder, int position) {
            holder.bindNearby(position);
        }

        @Override
        public int getItemCount() {
            return endpoints.size();
        }
    }

    void updatePlayersView(int players) {
        playerCountView.setText(String.format("%d/%d", players, suggestedPlayers));
    }

    private void getPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation ?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String [] {Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST_CODE);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String [] {Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission is already granted
        }
    }
}
