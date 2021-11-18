/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Blaine
 */
public class ChangeMaker {
    private BigDecimal totalPennies;
    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickels;
    private BigDecimal pennies;
    
    public ChangeMaker(BigDecimal totalPennies) {
            this.totalPennies = totalPennies;
    }
    
    public BigDecimal getQuarters() {
        return quarters;
    }
    
    public void setQuarters(BigDecimal quarters) {
        this.quarters = quarters;
    }
    
    public BigDecimal getDimes() {
        return dimes;
    }
    
    public void setDimes(BigDecimal dimes) {
        this.dimes = dimes;
    }
    
    public BigDecimal getNickels() {
        return nickels;
    }
    
    public void setNickels(BigDecimal nickels) {
        this.nickels = nickels;
    }
    
    public BigDecimal getPennies() {
        return pennies;
    }
    
    public void setPennies(BigDecimal pennies) {
        this.pennies = pennies;
    }
    
    public void makeChange() {
        BigDecimal q = totalPennies.divide(new BigDecimal(25));
        q = q.setScale(0, RoundingMode.DOWN);
        setQuarters(q);
        totalPennies = totalPennies.subtract(q.multiply(new BigDecimal(25)));
        
        BigDecimal d = totalPennies.divide(new BigDecimal(10));
        d = d.setScale(0, RoundingMode.DOWN);
        setDimes(d);
        totalPennies = totalPennies.subtract(d.multiply(new BigDecimal(10)));
        
        BigDecimal n = totalPennies.divide(new BigDecimal(5));
        n = n.setScale(0, RoundingMode.DOWN);
        setNickels(n);
        totalPennies = totalPennies.subtract(n.multiply(new BigDecimal(5)));
        
        setPennies(totalPennies.setScale(0, RoundingMode.DOWN));
        
    }
    
}
