package com.sprint.ride_along.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sprint.ride_along.R;
import com.sprint.ride_along.model.Driver;

import java.util.ArrayList;

/**
 * Created by jorge-cano on 22/11/17.
 */

public class DriverListAdapter extends ArrayAdapter{

    private ArrayList<Driver> drivers;

    public DriverListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Driver> drivers) {
        super(context, resource, drivers.toArray());
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View driverEntry;

        if(convertView != null){

            driverEntry = convertView;
        } else {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            driverEntry = vi.inflate(R.layout.driver_list_item, null);
        }

        Driver driver = this.drivers.get(position);

        TextView nameLabel = driverEntry.findViewById(R.id.result_driver_name);
        TextView availableSeatsLabel = driverEntry.findViewById(R.id.result_available_seats);
        TextView scheduleLabel = driverEntry.findViewById(R.id.result_driver_schedule);

        nameLabel.setText(driver.getName());

        availableSeatsLabel.setText(String.valueOf(driver.getCapacity()));

        String schedule = "De " + driver.getEntryHour() + " a " + driver.getExitHour();
        scheduleLabel.setText(schedule);

        return driverEntry;
    }
}
