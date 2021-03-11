/*
 Outsourced part class
 */
package com.wgu.inventorytest;

/**
 *
 * @author Reign
 */
public class Outsourced extends Part {
    
    private String companyName;
    
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
    }
    
    public void setCompanyName() {
        companyName = this.companyName;
    }
    
    
    public String getCompanyName() {
        return this.companyName;
    }
}
