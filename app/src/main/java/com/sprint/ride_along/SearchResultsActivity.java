package com.sprint.ride_along;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayList<Driver> drivers;

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

        setClickListener();
        new DriverSearchTask(userPoint, this).execute();
    }

    private void setClickListener() {

        ((ListView) findViewById(R.id.search_results)).setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Driver driver = drivers.get(position);
                showDriverDetails(driver);
            }
        });
    }

    private void showDriverDetails(Driver driver) {

        Intent intent = new Intent(this, DriverDetailsActivity.class);
        intent.putExtra("selectedDriver", driver);
        startActivity(intent);
    }

    public void displayDrivers(ArrayList<Driver> drivers) {

        if(!drivers.isEmpty()){

            findViewById(R.id.loading_message).setVisibility(View.GONE);

            this.drivers = drivers;

            ListView resultList = (ListView) findViewById(R.id.search_results);
            resultList.setAdapter(
                    new DriverListAdapter(this, R.layout.driver_list_item, drivers)
            );
        } else {

            ((TextView)findViewById(R.id.loading_message)).setText(rutesNotFoundMessage);
        }
    }
}
