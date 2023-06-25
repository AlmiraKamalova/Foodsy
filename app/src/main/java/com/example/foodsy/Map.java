package com.example.foodsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.List;

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
                Intent receiptIntent = new Intent(Map.this, PaymentActivity.class);
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

    private void addMarkerOnMap(String streetName) {
        Geocoder geocoder = new Geocoder(this); // Создаем экземпляр Geocoder

        try {
            List<Address> addresses = geocoder.getFromLocationName(streetName, 1); // Получаем список адресов по заданному имени улицы

            if (!addresses.isEmpty()) {
                Address firstAddress = addresses.get(0); // Получаем первый адрес из списка
                double latitude = firstAddress.getLatitude(); // Получаем широту
                double longitude = firstAddress.getLongitude(); // Получаем долготу
                LatLng latLng = new LatLng(latitude, longitude); // Создаем объект LatLng с полученными координатами

                mMap.addMarker(new MarkerOptions().position(latLng).title(streetName)); // Добавляем маркер на карту
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15)); // Перемещаем камеру к указанным координатам
            } else {
                Toast.makeText(this, "Street not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
