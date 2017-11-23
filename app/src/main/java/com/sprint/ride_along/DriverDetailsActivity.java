package com.sprint.ride_along;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sprint.ride_along.model.Driver;
import com.sprint.ride_along.tasks.DriverRuteTask;

import java.util.ArrayList;
import java.util.Iterator;

public class DriverDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.driver = (Driver) getIntent().getSerializableExtra("selectedDriver");
        displayDriverDetails();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.rute_map_fragment);
        mapFragment.getMapAsync(this);
    }

    private void displayDriverDetails() {

        TextView nameLabel = findViewById(R.id.details_driver_name);
        TextView availableSeatsLabel = findViewById(R.id.details_available_seats);
        TextView scheduleLabel = findViewById(R.id.details_driver_schedule);
        TextView phonenumber = findViewById(R.id.driver_phonenumber);
        TextView carDetails = findViewById(R.id.details_car);

        nameLabel.setText(this.driver.getName());

        availableSeatsLabel.setText(String.valueOf(this.driver.getCapacity()));

        String schedule = "De " + this.driver.getEntryHour() + " a " + this.driver.getExitHour();
        scheduleLabel.setText(schedule);

        phonenumber.setText(this.driver.getPhonenumber());

        String carDetailsText = this.driver.getCarBrand() + " "
                + this.driver.getCarColor() + " (" +
                this.driver.getLicensePlate() +")";

        carDetails.setText(carDetailsText);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        CameraUpdate meridaCam = CameraUpdateFactory.newLatLngZoom(new LatLng(20.9802, -89.621935), 12);
        mMap.moveCamera(meridaCam);

        new DriverRuteTask(this.driver.getInternalKey(), this).execute();

        setupFMATMarker();
    }

    private void setupFMATMarker() {

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(21.047410, -89.644225))
                .title("Facultad de Matemáticas")
                .snippet("")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        );
    }

    public void displayRute(ArrayList<LatLng> rutePoints) {

        Iterator<LatLng> iterator = rutePoints.iterator();
        while (iterator.hasNext()){

            LatLng routePoint = iterator.next();
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(routePoint)
                    .title("")
                    .snippet("")
            );
        }
    }

    public void sendWhatsApp(View view) {


        String smsNumber= this.driver.getPhonenumber() + "@s.whatsapp.net";
        Uri uri = Uri.parse("smsto:" + smsNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //i.putExtra("sms_body", "Prakash Gajera");
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }
}
