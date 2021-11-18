/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendingmachine.ui;

import com.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Blaine
 */
public class VendingMachineView {
    
    UserIO io;

    public VendingMachineView(UserIO vmIo) {
        this.io = vmIo;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Snacks and Purchase");
        io.print("2. Create New Snack");
        io.print("3. Add money");
        io.print("4. Save and Quit");
        return io.readInt("Please select from the above choices.", 1, 4);
    }
    
    public Snack getNewSnackInfo() {
        String Name = io.readString("Please enter Name");
        Integer quantity = io.readInt("Please enter Quantity");
        BigDecimal price = io.readBigDecimal("Please enter Price");
        Snack currentSnack = new Snack(Name);
        currentSnack.setName(Name);
        currentSnack.setQuantity(quantity);
        currentSnack.setPrice(price);
        return currentSnack;
    }
    
    public BigDecimal getMoneyInfo() {
        BigDecimal money = io.readBigDecimal("Please enter money amount");
        return money;
    }
    
    public String displaySnackList(List<Snack> snackList) {
        int zero = 0;
        
        List<Snack> greaterThanZero = snackList.stream()
                                    .filter((s) -> s.getQuantity() > zero)
                                    .collect(Collectors.toList());
        greaterThanZero.stream()
                .forEach((s) -> {
                    String snackInfo = String.format("Name: %s, quantity: %s, cost: $%s",
                      s.getName(),
                      s.getQuantity(),
                      s.getPrice());
                    io.print(snackInfo);
                });
        
//        for (Snack currentSnack : snackList) {
//            if (currentSnack.getQuantity() > zero){
//                    String snackInfo = String.format("Name: %s, quantity: %s, cost: $%s",
//                      currentSnack.getName(),
//                      currentSnack.getQuantity(),
//                      currentSnack.getPrice());
//                    io.print(snackInfo);
//                    }
//    }
    String chosenSnack = io.readString("Please enter snack name to purchase");
    return chosenSnack;
    }
}

    
    


