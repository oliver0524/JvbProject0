package org.example.Exception;

/** Exception is thrown when invalid values for Hotel Info variables are entered in CLI */

public class ProductInfoException extends Exception {
    public ProductInfoException(String message){
        super(message);
    }
}
