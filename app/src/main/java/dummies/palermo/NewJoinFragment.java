package dummies.palermo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NewJoinFragment extends Fragment {

    ConnectionsManager manager;
    List<Endpoint> endpoints;

    TextView titleTextView;
    RecyclerView hostRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        endpoints = new ArrayList<>();

        manager = new ConnectionsManager(getActivity(), UUID.randomUUID().toString());
        manager.setEndpointDiscoveryCallback(new EndpointDiscoveryCallback() {
            @Override
            public void onEndpointFound(@NonNull String endpoint,
                                        @NonNull DiscoveredEndpointInfo discoveredEndpointInfo) {
                // Add endpoint to host list
                endpoints.add(new Endpoint(endpoint, discoveredEndpointInfo.getEndpointName()));
                updateAdapter();
            }

            @Override
            public void onEndpointLost(@NonNull String endpoint) {
                // Remove endpoint from host list
                for (Endpoint endpoint1 : endpoints) {
                    if (endpoint1.getId().equals(endpoint)) {
                        endpoints.remove(endpoint1);
                        break;
                    }
                }
                updateAdapter();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_join, container, false);

        titleTextView = view.findViewById(R.id.fragment_new_join_text);
        hostRecyclerView = view.findViewById(R.id.fragment_new_join_host_recycler_view);

        hostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        hostRecyclerView.setAdapter(new HostAdapter());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        manager.startDiscovery();
    }

    @Override
    public void onStop() {
        manager.stopDiscovery();
        super.onStop();
    }

    private void updateAdapter() {
        hostRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private class HostAdapter extends RecyclerView.Adapter<HostHolder> {
        @NonNull
        @Override
        public HostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.host_item, parent, false);
            return new HostHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HostHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return endpoints.size();
        }
    }

    private class HostHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView textView;

        public HostHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.host_item_name);
        }

        public void bind(int position) {
            textView.setText(endpoints.get(position).getName());
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class Endpoint {
        private String id;
        private String name;

        public Endpoint(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
