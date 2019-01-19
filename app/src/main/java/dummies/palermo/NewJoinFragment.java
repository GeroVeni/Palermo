package dummies.palermo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

public class NewJoinFragment extends Fragment {
    ConnectionsManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new ConnectionsManager(getActivity(), UUID.randomUUID().toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_join, container, false);

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
}
