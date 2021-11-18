/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vendingmachine.service;

import com.vendingmachine.dao.InsufficientFundsException;
import com.vendingmachine.dao.NoItemInventoryException;
import com.vendingmachine.dto.ChangeMaker;
import com.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;



/**
 *
 * @author Blaine
 */
public interface VendingMachineServiceLayer {
    
    public Snack addSnack(String name, Snack snack);
    public List<Snack> getAllSnacks();
    public BigDecimal addMoney(BigDecimal money);
    public String checkFunds(String name)
            throws InsufficientFundsException;
    public ChangeMaker buySnack(String snackName)
            throws NoItemInventoryException;
    public void initalizeDatabase();
    public void saveAndQuit();
}
