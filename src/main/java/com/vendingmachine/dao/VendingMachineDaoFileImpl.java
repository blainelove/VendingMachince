/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendingmachine.dao;

import com.vendingmachine.dto.ChangeMaker;
import com.vendingmachine.dto.Snack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blaine
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private Map<String, Snack> snacks = new HashMap<>();
    public final String Vending_Delimiter = ":";
    public final String Vending_Inv = "Vending_Inv.txt";
    public BigDecimal userBalance = new BigDecimal(0);
    
    @Override
    public Snack addSnack(String name, Snack snack) {
        Snack newSnack = snacks.put(name, snack);
        return newSnack;
    }
    
    @Override
    public BigDecimal addMoney(BigDecimal money) {
        userBalance = money.setScale(2, RoundingMode.DOWN);
        return userBalance;
        }
    
    @Override
    public BigDecimal getMoney() {
        return userBalance;
        }
    
    @Override
    public String checkFunds(String name) throws InsufficientFundsException{
        String msg = "";
        Snack choiceSnack = snacks.get(name);
        BigDecimal price = choiceSnack.getPrice();
        if (userBalance.compareTo(price) < 0){
            msg = String.format("Insufficient funds. %s costs %s. You entered %s.", choiceSnack.getName(), price, userBalance);
            throw new InsufficientFundsException(msg); 
            };
        return msg;
        }
    
    @Override
    public ChangeMaker buySnackAndDecrement(String name) throws NoItemInventoryException {
        Snack choiceSnack = snacks.get(name);
        BigDecimal price = choiceSnack.getPrice();
        Integer inventory = choiceSnack.getQuantity();
        if (inventory <= 0){
            String msg = String.format("No inventory for item %s", choiceSnack.getName());
            throw new NoItemInventoryException(msg);
        };
        if (userBalance.compareTo(price) > 0){
            userBalance = userBalance.subtract(price);
            choiceSnack.setQuantity(choiceSnack.getQuantity() - 1);
            };
        BigDecimal pennies = userBalance.multiply(new BigDecimal(100));
        ChangeMaker changeMaker = new ChangeMaker(pennies);
        changeMaker.makeChange();
        return changeMaker;
        }
    
    @Override
    public List<Snack> getAllSnacks() {
        return new ArrayList<Snack>(snacks.values());
        }
    
    @Override
    public void initializeDatabase() {
        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(Vending_Inv)));   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String currentLine;
        Snack currentSnack;
        
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentSnack = unmarshallSnack(currentLine);
            snacks.put(currentSnack.getName(), currentSnack);
            }     
        }
    
    private Snack unmarshallSnack(String snackAsText) {
        String[] snackFields = snackAsText.split(Vending_Delimiter);
        Snack newSnack = new Snack(snackFields[0]);
        newSnack.setQuantity(Integer.valueOf(snackFields[1]));
        newSnack.setPrice(BigDecimal.valueOf(Double.parseDouble(snackFields[2])));
        return newSnack;
        }
        
    @Override
    public void saveAndQuit() {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(Vending_Inv));
            String textSnack;
            List<Snack> listOfSnacks = new ArrayList<Snack>(snacks.values());
        
            for (Snack currentSnack : listOfSnacks){
                textSnack = marshallSnack(currentSnack); 
                out.println(textSnack);
                out.flush();
                }
            out.close();
        } catch (IOException ex){
            Logger.getLogger(VendingMachineDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    
    private String marshallSnack(Snack aSnack){
        String writeSnack = aSnack.getName() + Vending_Delimiter;
        writeSnack += aSnack.getQuantity() + Vending_Delimiter;
        writeSnack += aSnack.getPrice();
        return writeSnack;
        }
    
}
