package com.sprint.ride_along;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.sprint.ride_along.adapters.DriverListAdapter;
import com.sprint.ride_along.model.Driver;
import com.sprint.ride_along.tasks.DriverSearchTask;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    private String rutesNotFoundMessage =
            "No se encontraron rutas que pasen por el punto de encuentro indicado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LatLng userPoint = new LatLng(
                getIntent().getDoubleExtra("latitude", 0),
                getIntent().getDoubleExtra("longitude", 0)
        );

        new DriverSearchTask(userPoint, this).execute();
    }

    public void displayDrivers(ArrayList<Driver> drivers) {

        if(!drivers.isEmpty()){

            findViewById(R.id.loading_message).setVisibility(View.GONE);
            ListView resultList = (ListView) findViewById(R.id.search_results);
            resultList.setAdapter(
                    new DriverListAdapter(this, R.layout.driver_list_item, drivers)
            );
        } else {

            ((TextView)findViewById(R.id.loading_message)).setText(rutesNotFoundMessage);
        }
    }
}
