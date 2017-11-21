package com.sprint.ride_along.parsers;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
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
public class RuteJsonParser {
    
    private JSONParser parser = new JSONParser();
    
    public ArrayList<LatLng> parseRute(String rutePoints) throws ParseException{
        
        JSONArray points = (JSONArray) this.parser.parse(rutePoints);
        Iterator iterator = points.iterator();
        
        ArrayList<LatLng> rute = new ArrayList<>();
        while(iterator.hasNext()){
            JSONObject pointModel = (JSONObject) iterator.next();
            JSONObject point = (JSONObject) pointModel.get("fields");
            
            double latitude = Double.valueOf((String) point.get("latitude"));
            double logitude = Double.valueOf((String) point.get("longitude"));
            
            rute.add(new LatLng(latitude, logitude));
        }
        
        return rute;
    }
}
