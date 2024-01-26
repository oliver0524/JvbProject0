package org.example.Service;

import org.example.Exception.HotelInfoException;
import org.example.Main;
import org.example.Model.HotelInfo;

import java.util.ArrayList;
import java.util.List;

public class HotelService {
    List<HotelInfo> hotelinfo;
    public HotelService(){
        hotelinfo = new ArrayList<>();
    }

    /** Methods here have functionality for all the actions: add, view, search, delete
     * There are being called from CLIParser */
    public void addHotel(String name, String features, double price) throws HotelInfoException {
        Main.log.info("Attempting to add a hotel." + name +", " +features +", " + price);

        if(price <= 0.0){
            Main.log.warn("Price cannnot be 0 or less: "+price);
            throw new HotelInfoException("Invalid price: 0 or less");
        }else if (name.length()<1){
            Main.log.warn("Hotel name is missing: "+name);
            throw new HotelInfoException("Hotel name is blank");
        }else if(features.isEmpty()){
            Main.log.warn("No features are provided: "+features);
            throw new HotelInfoException("No features provided");
        }
        HotelInfo h = new HotelInfo(name, features, price);
        hotelinfo.add(h);
    }
    public List<HotelInfo> getAllHotels(){
        Main.log.info("List of all hotels in the collection: "+hotelinfo);
        return hotelinfo;
    }

    public HotelInfo getHotelByName(String name){
/**     Iterate through each array entry until the match is found
        if the match is found , return the hotel name   */

        for(int i = 0; i < hotelinfo.size(); i++){
            HotelInfo currentHotel = hotelinfo.get(i);
            if(currentHotel.getName().equals(name)){
                return currentHotel;
            }
        }
        return null;
    }

    public int deleteHotelByName(String name){
/**     Iterate through each array entry until the match is found
        if the match is found , remove the hotel entry   */

        for(int i = 0; i < hotelinfo.size(); i++){
            HotelInfo currentHotel = hotelinfo.get(i);
            if(currentHotel.getName().equals(name)){
                hotelinfo.remove(i);
                return i;
            }
        }
        // if not found, return -1
        return -1;
    }
}

