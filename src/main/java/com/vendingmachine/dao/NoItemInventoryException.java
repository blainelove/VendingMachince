package com.vendingmachine.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Blaine
 */
public class NoItemInventoryException extends Exception {
    
    NoItemInventoryException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }
    NoItemInventoryException(String errorMsg) {
        super(errorMsg);
    }
}
