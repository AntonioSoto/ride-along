package com.sprint.ride_along.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.sprint.ride_along.parsers.DriverDetailsParser;
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

public class DriverInfoTask extends AsyncTask<Void, Void, String> {

    private String studentId;
    //private Activity activity

    public DriverInfoTask(String studentId) {
        this.studentId = studentId;
    }

    @Override
    protected String doInBackground(Void... voids) {

        InputStream inputStream;
        String result = "";

        try {
            String url =
                    "http://bdemg.pythonanywhere.com/get_all_driver_info/student_id=" + this.studentId;

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            HttpResponse httpResponse = httpclient.execute(get);

            if(httpResponse.getStatusLine().getStatusCode() == 200) {

                inputStream = httpResponse.getEntity().getContent();
                if (inputStream != null)
                    result = StreamConverter.convertInputStreamToString(inputStream);
                else
                    result = "Problemas!";
            } else {

                result = String.valueOf(httpResponse.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }


    @Override
    protected void onPostExecute(String result) {

        if (!result.equals("404")) {

            DriverDetailsParser driverInfo = null;

            try {
                driverInfo = new DriverDetailsParser(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //this.activity.displayDriverInfo(driverInfo.getDriver(), driverInfo.getRute());
        }
        else{
            //this.activity.displayDrivers(new ArrayList());
        }
    }
}
