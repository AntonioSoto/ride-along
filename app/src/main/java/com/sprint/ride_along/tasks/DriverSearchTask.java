package com.sprint.ride_along.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.sprint.ride_along.model.Driver;
import com.sprint.ride_along.parsers.DriversJsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jorge-cano on 21/11/17.
 */

public class DriverSearchTask extends AsyncTask<Void, Void, String> {

    private LatLng userPoint;
    //private Activity activity

    public DriverSearchTask(LatLng userPoint) {
        this.userPoint = userPoint;
    }

    @Override
    protected String doInBackground(Void... voids) {

        InputStream inputStream;
        String result = "";

        try {
            String url =
                    "http://bdemg.pythonanywhere.com/search_rutes/latitude=" +
                            this.userPoint.latitude + "/logitude=" + this.userPoint.longitude;

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = httpclient.execute(get);

            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null)
                result = StreamConverter.convertInputStreamToString(inputStream);
            else
                result = "Problemas!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }


    @Override
    protected void onPostExecute(String result) {

        if (!result.equals("Problemas!")) {

            ArrayList<Driver> drivers;
            try {
                drivers = new DriversJsonParser().parseDriversList(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //this.activity.displayDrivers(drivers);
        }
        else{
            //this.activity.displayDrivers(new ArrayList());
        }
    }
}
