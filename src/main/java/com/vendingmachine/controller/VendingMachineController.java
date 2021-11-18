/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendingmachine.controller;

import com.vendingmachine.dao.InsufficientFundsException;
import com.vendingmachine.dao.NoItemInventoryException;
import com.vendingmachine.dto.ChangeMaker;
import com.vendingmachine.dto.Snack;
import com.vendingmachine.service.VendingMachineServiceLayer;
import com.vendingmachine.ui.UserIO;
import com.vendingmachine.ui.UserIOConsoleImpl;
import com.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;





/**
 *
 * @author Blaine
 */
public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    private UserIO io;

    public VendingMachineController (VendingMachineServiceLayer service, VendingMachineView view, UserIO io){
        this.service = service;
        this.view = view;
        this.io = io;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //this will load current database into program on start
        service.initalizeDatabase();
        
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listSnacksAndBuy();
                    break;
                case 2:
                    createSnack();
                    break;
                case 3:
                    addMoney();
                    break;
                case 4:
                    saveAndQuit();
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createSnack() {
        Snack newSnack = view.getNewSnackInfo();
        service.addSnack(newSnack.getName(), newSnack);  
    }
    
    private void listSnacksAndBuy() {
        boolean sufficientFunds = true;
        List<Snack> snackList = service.getAllSnacks();
        String chosenSnack = view.displaySnackList(snackList);
        try {
            String msg = service.checkFunds(chosenSnack);
        } catch (InsufficientFundsException e) {
            sufficientFunds = false;
            io.print(e.getMessage());
        }
        
        if (sufficientFunds){
            try {
                ChangeMaker change = service.buySnack(chosenSnack);
                BigDecimal quarters = change.getQuarters();
                BigDecimal dimes = change.getDimes();
                BigDecimal nickels = change.getNickels();
                BigDecimal pennies = change.getPennies();
                io.print(String.format("Your change is %s quarters, %s dimes, %s nickels, and %s pennies",
                        quarters, dimes, nickels, pennies)); 
            } catch (NoItemInventoryException e) {
                io.print(e.getMessage());
            }
            
        }
    }
    
    private void addMoney() {
        BigDecimal money = view.getMoneyInfo();
        BigDecimal balance = service.addMoney(money);
        io.print("Your balance is " + balance);
       
    }
   
    private void saveAndQuit() {     
        service.saveAndQuit();
    }

}

