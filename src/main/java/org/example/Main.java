package org.example;

import org.example.Exception.CLIException;
import org.example.Exception.HotelInfoException;
import org.example.View.CLIParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/** The main class.  Only basic functionality of the application is here */
public class Main {

    public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){

        // Call the CLI Parser method to parse input values from the console
        Scanner sc = new Scanner(System.in);
        CLIParser cliParser = new CLIParser();

        // Read the input values; throw an exception if the action values are not in the list of acceptable values
        while(true){
            System.out.println("Enter your next action: add | view | search | delete | exit");
            String input = sc.nextLine();
            try {
//                Throw exceptions for unexpected behavior: bad action values, unacceptable values.
//                If the exception is thrown during the execution of the code within 'try', then
//                the code will switch over to running the matching 'catch' block.
//                printStackTrace will produce the messages in red; the println one would be in white
                String output = cliParser.parseCommandReturnOutput(input);
                System.out.println(output);
            }catch(CLIException exception){
                System.out.println("Please only use the acceptable action values");
                exception.printStackTrace();
            }catch(HotelInfoException exception){
                System.out.println("Bad input for the hotel info");
                exception.printStackTrace();
            }catch(NumberFormatException exception){
                System.out.println("This hotel price is not in the numeric format");
                exception.printStackTrace();
            }

        }
    }
}
