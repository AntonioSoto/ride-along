package com.sprint.ride_along;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sprint.ride_along.R;
import com.sprint.ride_along.model.Driver;

public class DriverProfileActivity extends AppCompatActivity {

    private Spinner seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        Driver driver = (Driver) getIntent().getSerializableExtra("driver");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_DriverProfile);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        seats = (Spinner) findViewById(R.id.driverProfile_Seats);
        ArrayAdapter<CharSequence> adapterSeats = ArrayAdapter.createFromResource(
                this,
                R.array.seats_array,
                android.R.layout.simple_spinner_item
        );
        adapterSeats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seats.setAdapter(adapterSeats);

        this.displayDriverInfo( driver );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void displayDriverInfo( Driver driver ){

        ((TextView)findViewById(R.id.driverProfile_Name)).setText( driver.getName() );
        ((TextView)findViewById(R.id.driverProfile_Phone)).setText( driver.getPhonenumber() );
        ((TextView)findViewById(R.id.driverProfile_Time1)).setText( driver.getEntryHour() );
        ((TextView)findViewById(R.id.driverProfile_Time2)).setText( driver.getExitHour() );
        ((TextView)findViewById(R.id.driverProfile_Model)).setText( driver.getCarBrand() );
        ((TextView)findViewById(R.id.driverProfile_Plate)).setText( driver.getLicensePlate() );
        seats.setSelection( driver.getCapacity() - 1 );
    }
}
