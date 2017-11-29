package com.sprint.ride_along;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.sprint.ride_along.tasks.DriverRegistryTask;

import java.util.ArrayList;

public class DriverMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button registerButton;
    private ArrayList<LatLng> rutes;
    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);

        rutes = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_DriverMap);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        registerButton = (Button) findViewById(R.id.button_RegisterDriver);
        registerButton.setVisibility(View.GONE);

        this.driver = (Driver) getIntent().getSerializableExtra("driver");
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

        showInstructions();
    }

    private void showInstructions(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setTitle("¡Atención!");
        builder.setMessage("Mantén presionado sobre el mapa para marcar los puntos por donde pasas.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.show();
    }

    private void selectPoint(LatLng userPoint){

        mMap.addMarker(new MarkerOptions()
                    .position(userPoint)
                    .title("")
                    .snippet("")
        ).setPosition(userPoint);

        // Se añade mínimo un punto para hacer el registro.
        rutes.add(userPoint);

        registerButton.setVisibility(View.VISIBLE);
    }

    public void registerDriver(View view){

        new DriverRegistryTask(driver, rutes, this).execute();
    }

    public void displayMessage(int result){

        Toast.makeText(
                this,
                "Código: "+result,
                Toast.LENGTH_LONG
        ).show();
    }

    public void displayDriverDetails() {

        Intent intent = new Intent(this, DriverProfileActivity.class);
        intent.putExtra("driver", driver );
        startActivity(intent);
    }
}
