/**
* Product class
 */
package com.wgu.inventorytest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @Reign
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

/**
     * constructors
     */  
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    
    /**
     * getters and setters
     */
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    //Error checking and price totalling
    public String checkProductError(int id, String name, double price, int stock, int min, int max, String errorMessage, ObservableList<Part> parts){
        double partTotal = 0.0;
        for (int i = 0; i < parts.size(); i++) {
            partTotal += parts.get(i).getPrice();
        }
        
        if (partTotal > price) {
            errorMessage = errorMessage + "Price must be greater than the total price of all parts. ";
        }
        if (name == null || name.trim().length() <= 0) {
            errorMessage = errorMessage + "The name field cannot be empty or blank. ";
        }
        if (max < min) {
            errorMessage = errorMessage + "The max cannot be less than the Min. ";
        }
        if (stock < 1) {
            errorMessage = errorMessage + "Stock must be at least 1. ";
        }
        if (price < 0) {
            errorMessage = errorMessage + "Price must be greater than $0. ";
        }
        if (stock < min) {
            errorMessage = errorMessage + "Stock must be greater than the Min number. ";
        }
        if (stock > max) {
            errorMessage = errorMessage + "Stock must be less than the Max number. ";
        }
        return errorMessage;
    }
}