package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Exception.ProductInfoException;
import org.example.Exception.SellerException;
import org.example.Model.ProductInfo;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import static org.example.Service.ProductService.*;


public class ProductController {

    SellerService sellerService;
    ProductService productService;

    public ProductController(SellerService sellerService, ProductService productService){
        this.sellerService = sellerService;
        this.productService = productService;
    }

    /** Method to create & configure a Javalin api; start the service; define endpoints
     * With the Create method we are referencing the Javalin class directly */
    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("/health",
                context ->
                {
                    context.result("the server is UP");
                }
        );
        api.get("/seller", context -> {
            LinkedHashSet<Seller> sellerSet = sellerService.getAllSellers();
            context.json(sellerSet);
        });

        api.get("/product", context -> {
            ArrayList<ProductInfo> productList = (ArrayList<ProductInfo>) productService.getAllProducts();
            context.json(productList);
        });

        api.post("/seller", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Seller s = om.readValue(context.body(), Seller.class);
                sellerService.addSeller(s);
                context.status(201);
            }catch(JsonProcessingException | SellerException e){
                context.status(400);
            }
        });

        api.post("/product", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                ProductInfo p = om.readValue(context.body(), ProductInfo.class);
                ProductInfo newProduct = productService.addProduct(p);
                context.status(201);
                context.json(newProduct);
            }catch(JsonProcessingException e){
                context.status(400);
            }catch(ProductInfoException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });

        /** If product id is found, respond with status 200
         *  If product id is not found, respond with status 404 */

        api.get("product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            ProductInfo p = productService.getProductById(id);
            if(p == null){
                context.status(404);
            }else{
                context.json(p);
                context.status(200);
            }
        });

        return api;
    }

}

//    public static void getAllSellersHandler(Context context){
//        LinkedHashSet<String> sellerSet = getAllSellers();
//        context.json(sellerSet);
//    }
//    public static void postSellerHandler(Context context) throws SellerException {
//        ObjectMapper om = new ObjectMapper();
//        try{
//            Seller s = om.readValue(context.body(), Seller.class);
//            SellerService.addSeller(String.valueOf(s));
////            201 - resource created
//            context.status(201);
//        } catch (JsonProcessingException e) {
////            Jackson was unable to parse the JSON due to user error
//            context.status(400);
//        }
//    }
//}


//public class CLIParser {
//
//    /** ProductService scoped for the entire class (address is established for the ProductService)
//     *  Methods from the ProductService class would be called here */
//    org.example.Service.ProductService ProductService;
//
//    public CLIParser() {
//        //constructor
//        ProductService = new ProductService();
//    }
//
//    /** Check if a valid command (out of the presented choices) is entered in the console
//     * If not, throw the CLI Exception and ProductInfoException when appropriate */
//    public String parseCommandReturnOutput(String command) throws CLIException, ProductInfoException {
//        if (command.equals("A")) {
//            return addAction();
//        } else if (command.equals("V")) {
//            return viewAction();
//        } else if (command.equals("SN")) {
//            return searchActionByName();
//        } else if (command.equals("SF")) {
//            return searchActionByFeature();
//        } else if (command.equals("U")) {
//            return updateAction();
//        } else if (command.equals("D")) {
//            return deleteAction();
//        } else if (command.equals("E")) {
//            System.exit(0);
//            return "exit";
//        } else {
//            throw new CLIException("not a valid command");
//        }
//    }
//
//    /** Handles console interactions for the 'add' action. Calls the addProduct method
//     * from the ProductService class. Also calls the toString method from the ProductInfo class.
//     * This method accepts variables for a ProductInfo object from the console and throws exceptions
//     * when input values do not pass validations */
//    public String addAction() throws ProductInfoException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("* Input a Product name");
//        String ProductName = sc.nextLine();
//        System.out.println("* Input Product features");
//        String ProductFeatures = sc.nextLine();
//        System.out.println("* Input a price per item (must be a positive number)");
////        the text version of the price
//        String priceInputString = sc.nextLine();
////        convert the text version of the price into a numeric (double) variable
//        double ProductPrice = Double.parseDouble(priceInputString);
//
//        ProductService.addProduct(ProductName, ProductFeatures, ProductPrice);
//        return "+++ Info for the " + ProductName + " Product has been added +++";
//    }
//
//    /** Handles console interactions for the 'view' action. Calls the getAllProducts method
//     * from the ProductService class. Also class the toString method from the ProductInfo class  */
//    public String viewAction() {
//        List<ProductInfo> Productinfo = ProductService.getAllProducts();
//        return "<<< Here are your Product entries: " + Productinfo.toString();
//    }
//
//    /** Handles console interactions for the 'search by Product name' action. Calls the getProductByName method
//     // from the ProductService class. */
//    // Search for a particular Product in the Product array
//    public String searchActionByName() {
//        Scanner sc = new Scanner(System.in);
//        String ProductName = sc.nextLine();
//        ProductInfo matchingProduct = ProductService.getProductByName(ProductName);
//
//        if (matchingProduct == null) {
//            return "There was no matching Product found";
//        } else {
//            return "@@@ Here is the matching Product: " + matchingProduct.toString();
//        }
//    }
//
//    /** Handles console interactions for the 'search by feature' action. Calls the getProductByFeatures method
//     // from the ProductService class. */
//    // Search for a particular Product in the Product array
//    public String searchActionByFeature() throws ProductInfoException {
//        Scanner sc = new Scanner(System.in);
//        String ProductFeature = sc.nextLine();
//        List<ProductInfo> matchingProducts = ProductService.getProductByFeatures(ProductFeature);
//
//        if (matchingProducts == null || matchingProducts.isEmpty()) {
//            return "There was no matching Product feature found";
//        } else {
//            StringBuilder result = new StringBuilder();
//
//            result.append ("@@@ Here is a list of Products with the "+ProductFeature+ " feature: " + matchingProducts.toString());
//            return result.toString();
//        }
//    }
//
//    /** Handles console interactions for the 'update' action. Calls the getProductByFeatures method
//     * from the ProductService class. */
//    // Productinfo is an ArrayList containing product information
//    public String updateAction() {
//        Scanner sc = new Scanner(System.in);
//        List<ProductInfo> Productinfo = ProductService.getAllProducts();
//        System.out.println("Enter Product name from this list that you would like to update? " + Productinfo.toString());
//        String ProductName = sc.nextLine();
//        ProductInfo matchingProduct = ProductService.getProductByName(ProductName);
//
//        if (matchingProduct != null) {
//            System.out.println("* Input Product features");
//            String ProductFeatures = sc.nextLine();
//            System.out.println("* Input a price per item (must be a positive number)");
//            double ProductPrice = sc.nextDouble();
//
//            // Call the service to update the product with the provided input
//            ProductInfo updatedProduct = ProductService.updateProduct(ProductName, ProductFeatures, ProductPrice);
//
//            if (updatedProduct == null) {
//                return "There was no matching Product found";
//            }
//            return "%%% Here is the updated Product: " + updatedProduct.toString();
//        }
//    }
//
//    /** Handles console interactions for the 'delete' action. Calls the getAllProducts method
//     // from the ProductService class. */
//    public String deleteAction() {
//        Scanner sc = new Scanner(System.in);
//        List<ProductInfo> Productinfo = ProductService.getAllProducts();
//        System.out.println("Enter Product name from this list that you would like to remove? " + Productinfo.toString());
//        String ProductName = sc.nextLine();
//
//        int index = ProductService.deleteProductByName(ProductName);
//
//        if (index != -1) {
//            System.out.println("Here is the updated list of Products: " + Productinfo.toString());
//            return ("!!! Product " + ProductName + " was removed successfully !!!");
//        } else {
//            return "There was no matching Product found";
//        }
//    }
//}

