package org.example.Model;

/** Class HotelInfo */
public class HotelInfo {
    private String name;
    private String features;
    private double price;

    // Constructor for the HotelInfo class
    public HotelInfo(String name, String features, double price) {
        this.name = name;
        this.features = features;
        this.price = price;
    }

    /** Setters and getters for the HotelInfo class variables. No usage annotations are ok */
    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String Features) {
        this.features = features;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
       this.price = price;
    }

    /** This code is used to convert HotelInfo objects into displayable format */
    @Override
    public String toString() {
        return "\nHotel " + name + " info: " +
                " features='" + features + "' |" +
                " price per night=" + price;
    }
}

