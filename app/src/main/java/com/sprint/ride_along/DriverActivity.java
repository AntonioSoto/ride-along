package com.sprint.ride_along;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sprint.ride_along.model.Driver;

import java.util.regex.Pattern;

public class DriverActivity extends AppCompatActivity {

    private Spinner models;
    private Spinner seats;

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

        models = (Spinner) findViewById(R.id.register_spinnerModels);
        ArrayAdapter<CharSequence> adapterModels = ArrayAdapter.createFromResource(
                this,
                R.array.models_array,
                android.R.layout.simple_spinner_item
        );
        adapterModels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        models.setAdapter(adapterModels);

        seats = (Spinner) findViewById(R.id.register_spinnerSeats);
        ArrayAdapter<CharSequence> adapterSeats = ArrayAdapter.createFromResource(
                this,
                R.array.seats_array,
                android.R.layout.simple_spinner_item
        );
        adapterSeats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seats.setAdapter(adapterSeats);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void driverMap(View view){

        if( areTextFieldsEmpty() ){

            Toast.makeText(
                    this,
                    "Faltan campos por completar",
                    Toast.LENGTH_SHORT
            ).show();
        }
        else if( isHourFormatValid() ){

            int internalKey = 0;
            String name = ((EditText)findViewById(R.id.register_Name)).getText().toString();
            String studentId = getIntent().getStringExtra("studentId");
            String carBrand = models.getSelectedItem().toString();
            String carColor = "Color";
            String licensePlate = ((EditText)findViewById(R.id.register_Plate)).getText().toString();
            String phonenumber = ((EditText)findViewById(R.id.register_Phone)).getText().toString();
            int capacity = Integer.valueOf(seats.getSelectedItem().toString());
            String entryHour = ((EditText)findViewById(R.id.register_Time1)).getText().toString();
            String exitHour = ((EditText)findViewById(R.id.register_Time2)).getText().toString();

            Driver driver = new Driver(
                    internalKey,
                    name,
                    studentId,
                    carBrand,
                    carColor,
                    licensePlate,
                    phonenumber,
                    capacity,
                    entryHour,
                    exitHour
            );

            Intent intent = new Intent(this, DriverMapActivity.class);
            intent.putExtra("driver", driver);
            startActivity(intent);
        }
        else{

            Toast.makeText(
                    this,
                    "El formato de la hora no es válido",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private boolean areTextFieldsEmpty(){

        String name = ((EditText)findViewById(R.id.register_Name)).getText().toString();
        String phone = ((EditText)findViewById(R.id.register_Phone)).getText().toString();
        String time1 = ((EditText)findViewById(R.id.register_Time1)).getText().toString();
        String time2 = ((EditText)findViewById(R.id.register_Time2)).getText().toString();
        String plate = ((EditText)findViewById(R.id.register_Plate)).getText().toString();

        return name.compareTo("")==0 ||
                phone.compareTo("")==0 ||
                time1.compareTo("")==0 ||
                time2.compareTo("")==0 ||
                plate.compareTo("")==0;
    }

    private boolean isHourFormatValid(){

        String time1 = ((EditText)findViewById(R.id.register_Time1)).getText().toString();
        String time2 = ((EditText)findViewById(R.id.register_Time2)).getText().toString();

        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(time1).find() &&
                pattern.matcher(time2).find();
    }
}
