package org.example.Model;

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

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getfeatures() {
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

    @Override
    public String toString() {
        return "\nHotel " + name + " info: " +
                " features='" + features + "' |" +
                " price per night=" + price;
    }
}

