package org.example.View;

import org.example.Exception.CLIException;
import org.example.Exception.HotelInfoException;
import org.example.Model.HotelInfo;
import org.example.Service.HotelService;

import java.util.List;
import java.util.Scanner;

public class CLIParser {

    /** HotelService scoped for the entire class (address is established for the HotelService)
     *  Methods from the HotelService class would be called here */
    HotelService hotelService;

    public CLIParser() {
        //constructor
        hotelService = new HotelService();
    }

    /** Check if a valid command (out of the presented choices) is entered in the console
     * If not, throw the CLI Exception and HotelInfoException when appropriate */
        public String parseCommandReturnOutput(String command) throws CLIException, HotelInfoException {
        if (command.equals("A")) {
            return addAction();
        } else if (command.equals("V")) {
            return viewAction();
        } else if (command.equals("SN")) {
            return searchActionByName();
        } else if (command.equals("SF")) {
            return searchActionByFeature();
        } else if (command.equals("D")) {
            return deleteAction();
        } else if (command.equals("E")) {
            System.exit(0);
            return "exit";
        } else {
            throw new CLIException("not a valid command");
        }
    }

    /** Handles console interactions for the 'add' action. Calls the addHotel method
     * from the HotelService class. Also calls the toString method from the HotelInfo class.
     * This method accepts variables for a HotelInfo object from the console and throws exceptions
     * when input values do not pass validations */
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
        return "+++ Info for the " + hotelName + " hotel has been added +++";
    }

    /** Handles console interactions for the 'view' action. Calls the getAllHotels method
     * from the HotelService class. Also class the toString method from the HotelInfo class  */
    public String viewAction() {
        List<HotelInfo> hotelinfo = hotelService.getAllHotels();
        return "<<< Here are your hotel entries: " + hotelinfo.toString();
    }

    /** Handles console interactions for the 'search by hotel name' action. Calls the getHotelByName method
     // from the HotelService class. */
    // Search for a particular hotel in the Hotel array
    public String searchActionByName() {
        Scanner sc = new Scanner(System.in);
        String hotelName = sc.nextLine();
        HotelInfo matchingHotel = hotelService.getHotelByName(hotelName);

        if (matchingHotel == null) {
            return "There was no matching hotel found.";
        } else {
            return "@@@ Here is the matching hotel: " + matchingHotel.toString();
        }
    }

    /** Handles console interactions for the 'search by feature' action. Calls the getHotelByFeatures method
     // from the HotelService class. */
    // Search for a particular hotel in the Hotel array
    public String searchActionByFeature() throws HotelInfoException {
        Scanner sc = new Scanner(System.in);
        String hotelFeature = sc.nextLine();
        List<HotelInfo> matchingHotels = hotelService.getHotelByFeatures(hotelFeature);

        if (matchingHotels == null || matchingHotels.isEmpty()) {
            return "There was no matching hotel feature found";
        } else {
            StringBuilder result = new StringBuilder();
            result.append ("@@@ Here is a list of hotels with the "+hotelFeature+ " feature: " + matchingHotels.toString());
            return result.toString();
        }
    }

    /** Handles console interactions for the 'delete' action. Calls the getAllHotels method
    // from the HotelService class. */
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

