/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vendingmachine.dao;

/**
 *
 * @author Blaine
 */
public class InsufficientFundsException extends Exception {
    
    InsufficientFundsException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }
    InsufficientFundsException(String errorMsg) {
        super(errorMsg);
    }
}
