package com.sprint.ride_along.parsers;

import com.sprint.ride_along.model.Driver;

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
public class DriversJsonParser {

    private JSONParser parser = new JSONParser();

    public ArrayList<Driver> parseDriversList(String driversList) throws ParseException {

        JSONArray driverList = (JSONArray) this.parser.parse(driversList);
        Iterator iterator = driverList.iterator();

        ArrayList<Driver> drivers = new ArrayList();
        while (iterator.hasNext()) {

            JSONObject driverModel = (JSONObject) iterator.next();
            drivers.add(parseDriver(driverModel));
        }
        
        return drivers;
    }

    protected Driver parseDriver(JSONObject driverModel) {
       
        int internalKey = (int) ((long) driverModel.get("pk"));

        JSONObject driverInfo = (JSONObject) driverModel.get("fields");
        String studentId = (String) driverInfo.get("student_id");
        String name = (String) driverInfo.get("name");
        String carBrand = (String) driverInfo.get("car_brand");
        String carColor = (String) driverInfo.get("car_color");
        String licensePlate = (String) driverInfo.get("license_plate");
        String phonenumber = (String) driverInfo.get("phonenumber");
        int capacity = (int) ((long) driverInfo.get("capacity"));
        String entryHour = ((String) driverInfo.get("entry_hour")).substring(0, 5);
        String exitHour = ((String) driverInfo.get("exit_hour")).substring(0, 5);

        return new Driver(
                internalKey,
                name,
                studentId,
                carBrand,
                carColor,
                licensePlate,
                phonenumber,
                capacity,
                entryHour,
                exitHour
        );
    }
}
