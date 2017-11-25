package com.sprint.ride_along;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sprint.ride_along.model.Driver;

import java.util.ArrayList;

public class DriverSearchActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker currentMarker = null;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Passenger);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.search_map);
        mapFragment.getMapAsync(this);

        searchButton = (Button) findViewById(R.id.rute_search_button);
        searchButton.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        CameraUpdate meridaCam = CameraUpdateFactory.newLatLngZoom(new LatLng(20.9802, -89.621935), 12);
        mMap.moveCamera(meridaCam);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                selectPoint(latLng);
            }
        });

        Toast.makeText(
                this,
                "Mentén presionado tu punto de encuentro en el mapa",
                Toast.LENGTH_LONG
        ).show();
    }

    private void selectPoint(LatLng userPoint){

        if(currentMarker == null){
            currentMarker = mMap.addMarker(new MarkerOptions()
                    .position(userPoint)
                    .title("")
                    .snippet(""));
        }
        currentMarker.setPosition(userPoint);
        searchButton.setVisibility(View.VISIBLE);
    }

    public void searchDrivers(View view){

        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("latitude", this.currentMarker.getPosition().latitude);
        intent.putExtra("longitude", this.currentMarker.getPosition().longitude);
        startActivity(intent);
    }
}
