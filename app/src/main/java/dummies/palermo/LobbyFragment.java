package dummies.palermo;

import android.Manifest;
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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyFragment extends Fragment {

    public static final int PERMISSIONS_REQUEST_CODE = 1;

    ConnectionsManager manager;
    String username;
    List<String> endpoints;

    TextView usernameView;
    TextView statusView;
    RecyclerView recyclerView;
    Button discoverButton;
    Button advertiseButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        username = UUID.randomUUID().toString();
        manager = new ConnectionsManager(getActivity(), username);

        endpoints = new ArrayList<>();
        endpoints.add(username);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lobby, container, false);

        usernameView = view.findViewById(R.id.fragment_lobby_username);
        statusView = view.findViewById(R.id.fragment_lobby_status_view);
        recyclerView = view.findViewById(R.id.fragment_lobby_recycler_view);
        discoverButton = view.findViewById(R.id.fragment_lobby_discover_button);
        advertiseButton = view.findViewById(R.id.fragment_lobby_advertise_button);

        usernameView.setText(username);

        statusView.setText("Choose an action");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NearbyAdapter());

        discoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start discovery
                manager.startDiscovery();

                // Deactivate buttons
                enableButtons(false, false);
            }
        });

        advertiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start advertising
                manager.startAdvertising();

                // Deactivate buttons
                enableButtons(false, false);
            }
        });

        getPermissions();

        enableButtons(true, true);

        return view;
    }

    @Override
    public void onStop() {
        manager.stopAdvertising();
        manager.stopDiscovery();
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

    void enableButtons(boolean discover, boolean advertise) {
        discoverButton.setEnabled(discover);
        advertiseButton.setEnabled(advertise);
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
