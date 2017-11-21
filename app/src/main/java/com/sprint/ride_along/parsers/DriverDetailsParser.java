package com.sprint.ride_along.parsers;

import com.google.android.gms.maps.model.LatLng;
import com.sprint.ride_along.model.Driver;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge A. Cano
 */
public class DriverDetailsParser {
     
    private JSONParser parser = new JSONParser();
    
    private ArrayList rute;
    private Driver driver;
     
    public DriverDetailsParser(String driverDetails) throws ParseException{
        
        JSONObject details = (JSONObject) this.parser.parse(driverDetails);
        
        JSONObject driverModel = (JSONObject) this.parser.parse((String) details.get("driver"));
        this.driver = new DriversJsonParser().parseDriver(driverModel);
        
        this.rute = new RuteJsonParser().parseRute((String) details.get("rute"));
    }

    public ArrayList<LatLng> getRute() {
        return rute;
    }

    public Driver getDriver() {
        return driver;
    }
}
