package org.example.Service;

import org.example.Exception.ProductInfoException;
import org.example.Main;
import org.example.Model.ProductInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Class ProductService handles the application functionality for Products
 * Methods here have functionality for the following actions: add, view, search, delete, exit
 * There are being called from CLIParser */
public class ProductService {

    /** Dependency Injection */
    SellerService sellerService;
    List<ProductInfo> ProductinfoList;

    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        ProductinfoList = new ArrayList<>();
    }

    public List<ProductInfo> getProductinfo() {
        return ProductinfoList;
    }

    /** This method: handles the Product addition and throws the ProductInfoException at the end if
     * at least one variable did not pass the validation test  */
    public ProductInfo addProduct(ProductInfo p) throws ProductInfoException {

        if (p.getName() == null || p.getSellername() == null) {
            throw new ProductInfoException("Product name and Seller name cannot be blank");
        }
        long id = (long) (Math.random() * Long.MAX_VALUE);
        p.setId(id);
        Main.log.info("ADD: Attempting to add a Product: "+id+"| "+p.getName()+" |"+ p.getPrice());
        ProductinfoList.add(p);
        return p;
    }

    public ProductInfo getProductById(Long id){
        for(int i = 0; i < ProductinfoList.size(); i++){
            ProductInfo currentProduct = ProductinfoList.get(i);
            if(currentProduct.getId() == id){
                return currentProduct;
            }
        }
        return null;
    }

    /** This method handles the 'view' action and displays all ProductInfo objects from
     * the Productinfo list */
    public List<ProductInfo> getAllProducts(){
        Main.log.info("VIEW: List of all Products in the collection: "+ProductinfoList);
        return ProductinfoList;
    }



    /**  This method handles the 'search' action by a Product name. The loop iterates
     * through each array entry until the match is found.
     * If the match is found, return the Product name   */
    public ProductInfo getProductByName(String name) {

        for (int i = 0; i < ProductinfoList.size(); i++) {
            ProductInfo currentProduct = ProductinfoList.get(i);
            if (currentProduct.getName().equals(name)) {
                Main.log.info("SEARCH: Successful search for the Product: " + name);
                return currentProduct;
            }
        }
        Main.log.warn("SEARCH: Unsuccessful search for the Product: " + name);
        return null ;
    }

    /**  This method handles the 'search' action by a Product feature. The loop iterates
     * through each array entry until the match is found.
     * Store all the Products with the matched feature in an array (type ArrayList)
     * If the match is found, return the array (Products) that have the Searched feature   */
//    public List<ProductInfo> getProductByFeatures(String feature) throws ProductInfoException {
//        // Check if the feature is a single word
//        if (feature.contains(" ")) {
//            Main.log.warn("SEARCH: Invalid input. Feature should be a single word: "+feature);
//            throw new ProductInfoException("Feature should be a single word");
//        }
//
//        List<ProductInfo> matchingProducts = new ArrayList<>();
//
//        for(int i = 0; i < ProductinfoList.size(); i++){
//            ProductInfo currentFeature = ProductinfoList.get(i);
//            String[] featuresArray = currentFeature.getFeatures().split("[, ]");
//
//            if(Arrays.asList(featuresArray).contains(feature)){
//                Main.log.info("SEARCH: Successful search for the feature: "+feature);
//                matchingProducts.add(currentFeature);
//            } else {
//                Main.log.warn("SEARCH: Unsuccessful search for the feature: "+feature);
//            }
//        }
//        return matchingProducts;
//    }

    /** This method handles the 'update' action. Iterate through each array entry
     * until the match is found. If the match is found , update the Product entry.
     * if not found, return -1 to the CLIParser */
    public ProductInfo updateProduct(String productname, double productprice) {

        // for each name in the ProductInfo collection compare the name with the productname parm
        for (ProductInfo name : ProductinfoList) {
            if (name.getName().equals(productname)) {
                // Update the product information
                name.setPrice(productprice);
                Main.log.info("UPDATE: product information updated for " + productname);
                return (ProductInfo) ProductinfoList;
            }
        }
        // Unsuccessful update
        return null;  // Return null if a product is not found or not updated
    }

    /** This method handles the 'delete' action. Iterate through each array entry
     * until the match is found. If the match is found , remove the Product entry.
     * if not found, return -1 to the CLIParser */
    public int deleteProductByName(String name){

        for(int i = 0; i < ProductinfoList.size(); i++){
            ProductInfo currentProduct = ProductinfoList.get(i);
            if(currentProduct.getName().equals(name)){
                ProductinfoList.remove(i);
                Main.log.info("DELETE: Successful delete for Product: "+name);
                return i;
            } else {
                Main.log.warn("DELETE: Unsuccessful delete for Product: "+name);
            }
        }
        return -1;
    }
}

