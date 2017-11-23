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

        models = (Spinner) findViewById(R.id.spinner_Models);
        ArrayAdapter<CharSequence> adapterModels = ArrayAdapter.createFromResource(
                this,
                R.array.models_array,
                android.R.layout.simple_spinner_item
        );
        adapterModels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        models.setAdapter(adapterModels);

        seats = (Spinner) findViewById(R.id.spinner_Seats);
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

        if( !areTextFieldsEmpty() ){

            String phone = ((EditText)findViewById(R.id.editText_RegisterPhone)).getText().toString();
            String time1 = ((EditText)findViewById(R.id.editText_RegisterTime1)).getText().toString();
            String time2 = ((EditText)findViewById(R.id.editText_RegisterTime2)).getText().toString();
            String plate = ((EditText)findViewById(R.id.editText_RegisterPlate)).getText().toString();

            Intent intent = new Intent(this, DriverMapActivity.class);
            intent.putExtra("phone", phone);
            intent.putExtra("time1", time1);
            intent.putExtra("time2", time2);
            intent.putExtra("model", models.getSelectedItem().toString());
            intent.putExtra("plate", plate);
            intent.putExtra("seat", seats.getSelectedItem().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(
                    this,
                    "Faltan campos por completar",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private boolean areTextFieldsEmpty(){

        String phone = ((EditText)findViewById(R.id.editText_RegisterPhone)).getText().toString();
        String time1 = ((EditText)findViewById(R.id.editText_RegisterTime1)).getText().toString();
        String time2 = ((EditText)findViewById(R.id.editText_RegisterTime2)).getText().toString();
        String plate = ((EditText)findViewById(R.id.editText_RegisterPlate)).getText().toString();

        return phone.compareTo("")==0 ||
                time1.compareTo("")==0 ||
                time2.compareTo("")==0 ||
                plate.compareTo("")==0;
    }
}
