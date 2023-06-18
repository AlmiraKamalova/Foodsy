package com.example.foodsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private EditText addressEditText;
    private Button showAddressButton;
    private Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        addressEditText = findViewById(R.id.address_edit_text);
        showAddressButton = findViewById(R.id.show_address_button);
        orderButton = findViewById(R.id.order_button);

        showAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressEditText.getText().toString();
                if (!address.isEmpty()) {
                    // Place a marker on the map for the entered address
                    addMarkerOnMap(address);
                } else {
                    Toast.makeText(Map.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receiptIntent = new Intent(Map.this, ReceiptActivity.class);
                startActivity(receiptIntent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set initial map location
        LatLng initialLocation = new LatLng(46.4834, 30.6994); // Example: San Francisco coordinates
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 12));

        // Enable zoom controls
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Set the map click listener
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear(); // Clear all previous markers on the map

        // Add a marker at the clicked position
        mMap.addMarker(new MarkerOptions().position(latLng));

        // Perform actions with the selected building
        // For example, save the position or display building details
        // Your code here...
    }

    private void addMarkerOnMap(String address) {
        // Geocode the address to get the LatLng coordinates
        // You need to implement the geocoding logic here using a geocoding service or API of your choice
        // Once you have the LatLng coordinates, you can add a marker to the map

        // Example code for adding a marker
        LatLng latLng = new LatLng(37.7749, -122.4194); // Example: San Francisco coordinates
        mMap.addMarker(new MarkerOptions().position(latLng).title(address));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
