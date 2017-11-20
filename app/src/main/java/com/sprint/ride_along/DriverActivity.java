package com.sprint.ride_along;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Driver);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Spinner spinnerModels = (Spinner) findViewById(R.id.spinner_Models);
        ArrayAdapter<CharSequence> adapterModels = ArrayAdapter.createFromResource(
                this,
                R.array.models_array,
                android.R.layout.simple_spinner_item
        );
        adapterModels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModels.setAdapter(adapterModels);

        Spinner spinnerSeats = (Spinner) findViewById(R.id.spinner_Seats);
        ArrayAdapter<CharSequence> adapterSeats = ArrayAdapter.createFromResource(
                this,
                R.array.seats_array,
                android.R.layout.simple_spinner_item
        );
        adapterSeats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeats.setAdapter(adapterSeats);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void driverMap(View view){

        Intent intent = new Intent(this, DriverMapActivity.class);
        startActivity(intent);
    }
}
