package org.example.Service;

import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.*;


public class SellerService {

    /** Class SellerService handles the application functionality for Sellers.
     * Methods here have functionality for the following actions: add, view all */
    LinkedHashSet<Seller> sellerSet;

    public SellerService() {
        this.sellerSet = new LinkedHashSet<>();
    }


        /** This method: handles the Seller addition and throws the SellerException at the end if
         * at least one variable did not pass the validation test  */
        public void addSeller (Seller s) throws SellerException {
            Main.log.info("ADD: Attempting to add a Seller." + s);

            if (s.getSellerName() == null) {
                Main.log.warn("ADD: Seller name is missing: " + s.getSellerName());
                throw new SellerException("Seller name is blank");
            }
            sellerSet.add(s);
        }
        /** This method handles the 'view' action and displays all Seller objects from the Seller Set */
        public LinkedHashSet<Seller> getAllSellers () {
            Main.log.info("VIEW: List of all Sellers in the collection: " + sellerSet);
            return sellerSet;
        }
    }


