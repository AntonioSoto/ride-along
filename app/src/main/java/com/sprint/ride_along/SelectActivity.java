package com.sprint.ride_along;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sprint.ride_along.model.Driver;
import com.sprint.ride_along.tasks.DriverInfoTask;

public class SelectActivity extends AppCompatActivity {

    private String studentId;
    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        this.studentId = getIntent().getStringExtra("studentId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Select);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        new DriverInfoTask(studentId, this).execute();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void passenger(View view){

        Intent intent = new Intent(this, DriverSearchActivity.class);
        startActivity(intent);
    }

    public void driver(View view){

        if( this.driver != null ){

            // Perfil del conductor
            Intent intent = new Intent(this, DriverProfileActivity.class);
            intent.putExtra("driver", driver );
            startActivity(intent);
        }
        else{

            // Registro de conductor
            Intent intent = new Intent(this, DriverActivity.class);
            intent.putExtra("studentId", studentId);
            startActivity(intent);
        }
    }

    public void setupDriverInfo(Driver driver){

        this.driver = driver;
    }
}
