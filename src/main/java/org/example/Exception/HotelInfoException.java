package org.example.Exception;

/**
 * Exception is thrown when invalid values for Hotel Info variables are entered in CLI:
 * negative numerics or empty values.
 */

public class HotelInfoException extends Exception {
    public HotelInfoException(String message){
        super(message);
    }
}
