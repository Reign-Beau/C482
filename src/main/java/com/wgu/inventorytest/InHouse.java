/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgu.inventorytest;

/**
 *
 * @author Reign
 */

//In house part concrete class
public class InHouse extends Part {
    
    private double machineId; //changed to double to allow for lossless randomization
    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }
    
    //Randomize machineId between 100 and 200, no repeating
    public void setMachineId() {
        int min = 100;
        int max = 200;
        machineId = Math.random() * (max - min +1) + min;
    };
    
    public double getMachineId() {
        return this.machineId;
    }
}
