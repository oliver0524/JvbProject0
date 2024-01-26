package org.example.View;

import org.example.Exception.CLIException;
import org.example.Exception.HotelInfoException;
import org.example.Model.HotelInfo;
import org.example.Service.HotelService;

import java.util.List;
import java.util.Scanner;

public class CLIParser {

    // HotelService scoped for the entire class (address is established for the HotelService)
    HotelService hotelService;


    public CLIParser() {
        //constructor
        hotelService = new HotelService();
    }

    /** Check if a valid command (out of the presented choices) is entered in the console
     * If not, throw the CLI Exception  */
        public String parseCommandReturnOutput(String command) throws CLIException, HotelInfoException {
        if (command.equals("add")) {
            return addAction();
        } else if (command.equals("view")) {
            return viewAction();
        } else if (command.equals("search")) {
            return searchAction();
        } else if (command.equals("delete")) {
            return deleteAction();
        } else if (command.equals("exit")) {
            System.exit(0);
            return "exit";
        } else {
            throw new CLIException("not a valid command");
        }
    }

    public String addAction() throws HotelInfoException {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Input a hotel name");
        String hotelName = sc.nextLine();
        System.out.println("* Input hotel features");
        String hotelFeatures = sc.nextLine();
        System.out.println("* Input a price per night (must be a positive number)");
//        the text version of the price
        String priceInputString = sc.nextLine();
//        convert the text version of the price into a numeric (double) variable
        double hotelPrice = Double.parseDouble(priceInputString);

        hotelService.addHotel(hotelName, hotelFeatures, hotelPrice);
        return "+++ Hotel info for " + hotelName + " has been added +++";
    }

    // View all objects in the Hotel array
    public String viewAction() {
        List<HotelInfo> hotelinfo = hotelService.getAllHotels();
        return "<<< Here are your hotel entries: " + hotelinfo.toString();
    }

    // Search for a particular hotel in the Hotel array
    public String searchAction() {
        Scanner sc = new Scanner(System.in);
        String hotelName = sc.nextLine();
        HotelInfo matchingHotel = hotelService.getHotelByName(hotelName);

        if (matchingHotel == null) {
            return "There was no matching hotel found.";
        } else {
            return "@@@ Here is the matching hotel: " + matchingHotel.toString();
        }

    }

    // Delete a particular hotel in the Hotel array
    public String deleteAction() {
        Scanner sc = new Scanner(System.in);
        List<HotelInfo> hotelinfo = hotelService.getAllHotels();
        System.out.println("Enter hotel name from this list that you would like to remove? " + hotelinfo.toString());
        String hotelName = sc.nextLine();

        int index = hotelService.deleteHotelByName(hotelName);

        if (index != -1) {
            System.out.println("Here is the updated list of hotels: " + hotelinfo.toString());
            return ("!!! Hotel " + hotelName + " was removed successfully !!!");
        } else {
            return "There was no matching hotel found";
        }
    }
}

