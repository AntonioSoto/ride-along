package com.sprint.ride_along.tasks;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.sprint.ride_along.model.Driver;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jorge-cano on 22/11/17.
 */

public class DriverRegistryTask extends AsyncTask<Void, Void, Integer> {

    private Driver driver;
    private ArrayList<LatLng> rute;
    //private Activity activity;

    public DriverRegistryTask(Driver driver, ArrayList<LatLng> rute) {
        this.driver = driver;
        this.rute = rute;
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        String url = "http://bdemg.pythonanywhere.com/register_driver/";
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");

        String json = buildJson();
        ByteArrayEntity entity = null;

        int result = -1;

        try {
            entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            post.setEntity( entity );

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);

            result = response.getStatusLine().getStatusCode();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String buildJson() {

        String json = "{\n" +
                "\t\"student_id\": \""+ this.driver.getStudentId() +"\",\n" +
                "\t\"phonenumber\": \""+ this.driver.getPhonenumber() +"\",\n" +
                "\t\"car_brand\": \""+ this.driver.getCarBrand() +"\",\n" +
                "\t\"car_color\": \""+ this.driver.getCarColor() +"\",\n" +
                "\t\"license_plate\": \""+ this.driver.getLicensePlate() +"\",\n" +
                "\t\"capacity\": "+this.driver.getCapacity()+",\n" +
                "\t\"entry_hour\": \""+ this.driver.getEntryHour() +"\",\n" +
                "\t\"exit_hour\": \""+ this.driver.getExitHour() +"\",\n" +
                "\t\"rute_points\": [\n";

        Iterator<LatLng> iterator = this.rute.iterator();
        while(iterator.hasNext()){

            LatLng point = iterator.next();

            if(iterator.hasNext()) {

                json.concat("\t\t{\"latitude\": \"" + point.latitude + "\", \"longitude\": \"" +
                        point.longitude + "\"},\n");
            } else{

                json.concat("\t\t{\"latitude\": \"" + point.latitude + "\", \"longitude\": \""+
                        point.longitude + "\"}\n");
            }
        }

        json.concat("\t]\t\n" +
                "}");

        return json;
    }

    @Override
    protected void onPostExecute(Integer result) {

        switch (result){
            case 200:
                //send ok
                break;
            case 409:
                //already registered
                break;
            default:
                //error
                break;
        }
    }
}
