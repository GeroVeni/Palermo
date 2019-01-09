package dummies.palermo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ConnectionsManager {

    private static final String SERVICE_ID = "com.dummies.palermo";

//    private static ConnectionsManager instance;
    private static Strategy STRATEGY = Strategy.P2P_CLUSTER;

    private Activity activity;
    private String username;

    public ConnectionsManager(Activity activity, String username) {
        this.activity = activity;
        this.username = username;
    }

    public void startAdvertising() {
        AdvertisingOptions advertisingOptions =
                new AdvertisingOptions.Builder().setStrategy(STRATEGY).build();
        Nearby.getConnectionsClient(activity)
                .startAdvertising(
                        getUserNickname(), SERVICE_ID,
                        connectionLifecycleCallback, advertisingOptions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // We are advertising!
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // We were unable to start advertising.
                    }
                });
    }

    public void stopAdvertising() {
        Nearby.getConnectionsClient(activity).stopAdvertising();
    }

    public void startDiscovery() {
        DiscoveryOptions discoveryOptions =
                new DiscoveryOptions.Builder().setStrategy(STRATEGY).build();
        Nearby.getConnectionsClient(activity)
                .startDiscovery(SERVICE_ID, endpointDiscoveryCallback, discoveryOptions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // We're discovering!
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // We're unable to start discovering.
                    }
                });
    }

    public void stopDiscovery() {
        Nearby.getConnectionsClient(activity).stopDiscovery();
    }

    private void sendBytes(byte [] bytes) {
        Payload payload = Payload.fromBytes(bytes);
        Nearby.getConnectionsClient(activity).sendPayload(getEndpointList(), payload);
        Log.i("TAG", "Sending: " + new String(bytes, UTF_8));
    }

    private void onBytesReceived(byte [] bytes) {
        Log.i("TAG", "Received: " + new String(bytes, UTF_8));
    }

    private ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() {
        @Override
        public void onConnectionInitiated(@NonNull final String endpointId, @NonNull ConnectionInfo connectionInfo) {
            // Automatically accept the connection on both sides.
//            Nearby.getConnectionsClient(activity).acceptConnection(endpointId, payloadCallback);

            new AlertDialog.Builder(activity)
                    .setTitle("Accept connection to " + connectionInfo.getEndpointName())
                    .setMessage("Confirm the code matches on both devices: " + connectionInfo.getAuthenticationToken())
                    .setPositiveButton(
                            "Accept",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // The user confirmed, so we can accept the connection.
                                    Nearby.getConnectionsClient(activity)
                                            .acceptConnection(endpointId,
                                                    new ReceiveBytesPayloadListener(
                                                            ConnectionsManager.this));
                                }
                            })
                    .setNegativeButton(
                            android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // The user canceled, so we should reject the connection.
                                    Nearby.getConnectionsClient(activity).rejectConnection(endpointId);
                                }
                            })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        @Override
        public void onConnectionResult(@NonNull String endpointId, @NonNull ConnectionResolution connectionResolution) {
            switch (connectionResolution.getStatus().getStatusCode()) {
                case ConnectionsStatusCodes.STATUS_OK:
                    // We're connected! Can now start sending and receiving data.
                    break;
                case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED:
                    // The connection was rejected by one or both sides.
                    break;
                case ConnectionsStatusCodes.STATUS_ERROR:
                    // The connection broke before it was able to be accepted.
                    break;
                default:
                    // Unknown status code
            }
        }

        @Override
        public void onDisconnected(@NonNull String endpointId) {
            // We've been disconnected from this endpoint. No more data can be
            // sent or received.
        }
    };

    private EndpointDiscoveryCallback endpointDiscoveryCallback = new EndpointDiscoveryCallback() {
        @Override
        public void onEndpointFound(@NonNull String endpointId  , @NonNull DiscoveredEndpointInfo discoveredEndpointInfo) {
            // An endpoint was found. We request a connection to it
            Nearby.getConnectionsClient(activity)
                    .requestConnection(getUserNickname(), endpointId, connectionLifecycleCallback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // We successfully requested a connection. Now both sides
                            // must accept before the connection is established.
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Nearby Connections failed to request the connection.
                        }
                    });
        }

        @Override
        public void onEndpointLost(@NonNull String s) {
            // A previously discovered endpoint has gone away
        }
    };

    static class ReceiveBytesPayloadListener extends PayloadCallback {

        ConnectionsManager manager;

        ReceiveBytesPayloadListener(ConnectionsManager manager) {
            this.manager = manager;
        }

        @Override
        public void onPayloadReceived(@NonNull String endpointId, @NonNull Payload payload) {
            // This always gets the full data of the payload. Will be null if it's not a BYTES
            // payload. You can check the payload type with payload.getType().
            byte[] receivedBytes = payload.asBytes();
            manager.onBytesReceived(receivedBytes);
        }

        @Override
        public void onPayloadTransferUpdate(String endpointId, PayloadTransferUpdate update) {
            // Bytes payloads are sent as a single chunk, so you'll receive a SUCCESS update immediately
            // after the call to onPayloadReceived().
        }
    }

    private List<String> getEndpointList() {
        return new ArrayList<>();
    }

    private String getUserNickname() {
        return username;
    }
}
