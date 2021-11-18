/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendingmachine.service;

import com.vendingmachine.dao.InsufficientFundsException;
import com.vendingmachine.dao.NoItemInventoryException;
import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dto.ChangeMaker;
import com.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blaine
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    VendingMachineDao dao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao vmDao) {
        this.dao = vmDao;
    }
    
    @Override
    public void initalizeDatabase() {
        dao.initializeDatabase();
    }
    
    @Override
    public void saveAndQuit() {
        dao.saveAndQuit();
    }
    
    @Override
    public Snack addSnack(String name, Snack snack) {
        return dao.addSnack(name, snack);
    }
    
    @Override
    public List<Snack> getAllSnacks() {
        return dao.getAllSnacks();
    }
    
    @Override
    public BigDecimal addMoney(BigDecimal money) {
        return dao.addMoney(money);
    }
    
    @Override
    public String checkFunds(String name) throws InsufficientFundsException{
        return dao.checkFunds(name);
    }
    
    @Override
    public ChangeMaker buySnack(String snackName) throws NoItemInventoryException{
        return dao.buySnackAndDecrement(snackName);
    }
    
    
}
