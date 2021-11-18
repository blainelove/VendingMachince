/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vendingmachine.dao;

import com.vendingmachine.dto.ChangeMaker;
import com.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Blaine
 */
public interface VendingMachineDao {

    Snack addSnack(String name, Snack snack);
    String checkFunds(String name)
            throws InsufficientFundsException;
    ChangeMaker buySnackAndDecrement(String name)
            throws NoItemInventoryException;
    
    List<Snack> getAllSnacks();
    
    public BigDecimal addMoney(BigDecimal money);
    public BigDecimal getMoney();
    public void initializeDatabase();
    public void saveAndQuit();

}
   

