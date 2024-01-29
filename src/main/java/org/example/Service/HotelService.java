package org.example.Service;

import org.example.Exception.HotelInfoException;
import org.example.Main;
import org.example.Model.HotelInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Class HotelService handles the application functionality
 * Methods here have functionality for all the actions: add, view, search, delete, exit
 * There are being called from CLIParser */
public class HotelService {
    List<HotelInfo> hotelinfo;
    public HotelService(){
        hotelinfo = new ArrayList<>();
    }

    /** This method: handles the hotel addition and throws the HotelInfoException at the end if
     * at least one variable did not pass the validation test  */
    public void addHotel(String name, String features, double price) throws HotelInfoException {
        Main.log.info("ADD: Attempting to add a hotel." + name +", " +features +", " + price);

        if(price <= 0.0){
            Main.log.warn("ADD: Price cannnot be 0 or less: "+price);
            throw new HotelInfoException("Invalid price: 0 or less");
        }else if (name.length()<1){
            Main.log.warn("ADD: Hotel name is missing: "+name);
            throw new HotelInfoException("Hotel name is blank");
        }else if(features.isEmpty()){
            Main.log.warn("ADD: No features are provided: "+features);
            throw new HotelInfoException("No features provided");
        }
        HotelInfo h = new HotelInfo(name, features, price);
        hotelinfo.add(h);
    }
    /** This method handles the 'view' action and displays all HotelInfo objects from
     * the hotelinfo list */
    public List<HotelInfo> getAllHotels(){
        Main.log.info("VIEW: List of all hotels in the collection: "+hotelinfo);
        return hotelinfo;
    }

    /**  This method handles the 'search' action by a hotel name. The loop iterates
     * through each array entry until the match is found.
     * If the match is found, return the hotel name   */
    public HotelInfo getHotelByName(String name){

        for(int i = 0; i < hotelinfo.size(); i++){
            HotelInfo currentHotel = hotelinfo.get(i);
            if(currentHotel.getName().equals(name)){
                Main.log.info("SEARCH: Successful search for a hotel: "+name);
                return currentHotel;
            } else {
                Main.log.warn("SEARCH: Unsuccessful search for a hotel: "+name);
            }
        }
        return null;
    }

    /**  This method handles the 'search' action by a hotel feature. The loop iterates
     * through each array entry until the match is found.
     * Store all the hotels with the matched feature in an array (type ArrayList)
     * If the match is found, return the array (hotels) that have the Searched feature   */
    public List<HotelInfo> getHotelByFeatures(String feature) throws HotelInfoException {
        // Check if the feature is a single word
        if (feature.contains(" ")) {
            Main.log.warn("SEARCH: Invalid input. Feature should be a single word: "+feature);
            throw new HotelInfoException("Feature should be a single word");
        }

        List<HotelInfo> matchingHotels = new ArrayList<>();

        for(int i = 0; i < hotelinfo.size(); i++){
            HotelInfo currentFeature = hotelinfo.get(i);
            String[] featuresArray = currentFeature.getFeatures().split("[, ]");

            if(Arrays.asList(featuresArray).contains(feature)){
                Main.log.info("SEARCH: Successful search for a feature: "+feature);
                matchingHotels.add(currentFeature);
            } else {
                Main.log.warn("SEARCH: Unsuccessful search for a feature: "+feature);
            }
        }
        return matchingHotels;
    }

    /** This method handles the 'delete' action. Iterate through each array entry
     * until the match is found. If the match is found , remove the hotel entry.
     * if not found, return -1 to the CLIParser */
    public int deleteHotelByName(String name){

        for(int i = 0; i < hotelinfo.size(); i++){
            HotelInfo currentHotel = hotelinfo.get(i);
            if(currentHotel.getName().equals(name)){
                hotelinfo.remove(i);
                Main.log.info("DELETE: Successful delete for hotel: "+name);
                return i;
            } else {
                Main.log.warn("DELETE: Unsuccessful delete for hotel: "+name);
            }
        }
        return -1;
    }
}

