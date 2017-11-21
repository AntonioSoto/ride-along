package com.sprint.ride_along.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.sprint.ride_along.parsers.DriversJsonParser;
import com.sprint.ride_along.parsers.RuteJsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.parser.ParseException;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by jorge-cano on 21/11/17.
 */

public class DriverRuteTask extends AsyncTask<Void, Void, String> {

    private int driverKey;
    //Activity activity;

    public DriverRuteTask(int driverKey) {
        this.driverKey = driverKey;
    }

    @Override
    protected String doInBackground(Void... voids) {

        InputStream inputStream;
        String result = "";

        try {
            String url =
                    "http://bdemg.pythonanywhere.com/get_driver_rute/driver=" + this.driverKey;

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

            ArrayList<LatLng> rutePoints;
            try {
                rutePoints = new RuteJsonParser().parseRute(result);
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
