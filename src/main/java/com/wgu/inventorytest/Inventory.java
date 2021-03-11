/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgu.inventorytest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Reign
 */
public class Inventory {
    
        /**
     * Returns an ObservableList of Part and Product objects
     * @return 
     */
    private static ObservableList<Part> partArr = FXCollections.observableArrayList();
    private static ObservableList<Product> productArr = FXCollections.observableArrayList();
//    public ObservableList<InHouse> getParts() {
//        ObservableList<InHouse> parts = FXCollections.observableArrayList();
//        parts.add(new InHouse(1,"Brakes",15.00,10,1,20));
//        parts.add(new InHouse(2,"Wheel",11.00,16,1,40));
//        parts.add(new InHouse(3,"Seat",15.00,10,1,20));
//        
//        return parts;
//    }
//    
//       //Returns ObservableList of Product objects
//        public ObservableList<Product> getProducts() {
//        ObservableList<Product> products = FXCollections.observableArrayList();
//        products.add(new Product(1,"Giant Bike",299.99,5,1,20));
//        products.add(new Product(2,"Tricycle",99.99,3,1,40));
//        
//        return products;
//    }
    //Count for indexing
       private static int partCount = 0;
       private static int productCount = 0;
    
       
    //Get arrs
    public static ObservableList<Part> getPartArr() {
        return partArr;
    }
    
    public static ObservableList<Product> getProductArr() {
        return productArr;
    }
    
    //Inventory list management functions
       //Add
    public static void addPart(Part newPart) {
        partArr.add(newPart);
    }
   
    public static void addProduct(Product newProduct) {
        productArr.add(newProduct);
    }
        //Delete
    public static void deletePart(Part part) {
        partArr.remove(part);
    }
    
    public static void deleteProduct(Product product) {
        productArr.remove(product);
    }
        //Update
    public static void updatePart(int index, Part part) {
        partArr.set(index, part);
    }
    
    public static void updateProduct(int index, Product product) {
        productArr.set(index, product);
    }

    //for indexing
    public static int getPartCount() {
        partCount++;
        return partCount;
    }
    
    public static int getProductCount() {
        productCount++;
        return productCount;
    }
     
    //search methods
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : partArr) {
            if (part.getName().equals(partName)) {
                partsFound.add(part);
            }
        }

        return partsFound;
    }
    public static Product lookupProduct(int productId) {
        Product productFound = null;

        for (Product product : productArr) {
            if (product.getId() == productId) {
                productFound = product;
            }
        }

        return productFound;
    }
}
