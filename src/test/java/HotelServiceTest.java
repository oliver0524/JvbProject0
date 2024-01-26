import org.example.Exception.CLIException;
import org.example.Exception.HotelInfoException;
import org.example.Main;
import org.example.Model.HotelInfo;
import org.example.Service.HotelService;
import org.example.View.CLIParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HotelServiceTest {

    HotelService hotelService;

    /**
     * Instantiate an object before each test
     */
    @Before
    public void setUp() {
        hotelService = new HotelService();
    }

    /** When the hotel service is first created, it should be empty.*/
    @Test
    public void recipeServiceEmptyAtStart() {
        List<HotelInfo> hotelinfo = hotelService.getAllHotels();
        Assert.assertTrue(hotelinfo.size() == 0);
    }

    /** Test if the "happy path" is working */
    @Test
    public void hotelServiceAddHotel() {
//        arrange
        String testName = "name";
        String testFeatures = "features";
        Double testPrice = 1.0;
//        act
        try {
            // if the addHotel method DOES throw an exception, the Assert.fail method is called
            hotelService.addHotel(testName, testFeatures, testPrice);
        } catch (HotelInfoException x) {
            x.printStackTrace();
            Assert.fail("Incorrectly thrown exception");
        }
    }

    /** When price 0 or less is provided or name or features are empty, the Hotel Info Exception should be thrown.*/
    @Test
    public void addHotelInvalidPrice() {
        String name = "hotel";
        String features = "features";
        double price = 0.0;
        try {
            // if the addHotel method DOES NOT throw an exception, the Assert.fail method is called
            hotelService.addHotel(name, features, price);
            Assert.fail("The Exception is not thrown when price is 0 or less");
        } catch (HotelInfoException e) {
            // Log the exception details
            Main.log.info("Caught HotelInfoException: " + e.getMessage());
        }
    }

    /** When hotel name is empty, the Hotel Info Exception should be thrown.*/
    @Test
    public void addHotelInvalidName() {
        String name = "";
        String features = "features";
        double price = 1.0;
        try {
            // if the addHotel method DOES NOT throw an exception, the Assert.fail method is called
            hotelService.addHotel(name, features, price);
            Assert.fail("The Exception is not thrown when hotel name is blank");
        } catch (HotelInfoException e) {
            // Log the exception details
            Main.log.info("Caught HotelInfoException: " + e.getMessage());
        }
    }

    /** When hotel features are empty, the Hotel Info Exception should be thrown.*/
    @Test
    public void addHotelInvalidFeatures() {
        String name = "name";
        String features = "";
        double price = 2.0;
        try {
            // if the addHotel method DOES NOT throw an exception, the Assert.fail method is called
            hotelService.addHotel(name, features, price);
            Assert.fail("The Exception is not thrown when hotel features are empty");
        } catch (HotelInfoException e) {
            // Log the exception details
            Main.log.info("Caught HotelInfoException: " + e.getMessage());
        }
    }

        /** When invalid action is supplied, the CLI Exception should be thrown.*/
        @Test
        public void invalidAction() {
            String action = "garbage";
            CLIParser cliParser = new CLIParser();
            try {
                // if the main method DOES NOT throw an exception, the Assert.fail method is called
                cliParser.parseCommandReturnOutput(action);
                Assert.fail("The Exception is not thrown when invalid action is supplied");
            } catch (CLIException | HotelInfoException e2) {
                // Log the exception details
                Main.log.info("Caught CLIException: " + e2.getMessage());
            }
        }
    }
