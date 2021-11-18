/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.vendingmachine.dao;

import com.vendingmachine.dto.ChangeMaker;
import com.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Blaine
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        testDao = new VendingMachineDaoFileImpl();
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddSnack() {
        //Arrange - setting up test data
        String name = "TestSanck";
        Snack testSnack = new Snack(name);
        testSnack.setQuantity(2);
        testSnack.setPrice(new BigDecimal(1.00));
        
        //Act - call the function your testing with test data
        testDao.addSnack(name, testSnack);
        
        //Assert - check results are as expected
        assertTrue(testDao.getAllSnacks().contains(testSnack));
    }
    
    @Test
    public void testAddMoney() {
        //Test user balance starts at 0
        assertEquals(new BigDecimal(0), testDao.getMoney());
        
        //Test once user adds money user balances updates
        BigDecimal testMoney = new BigDecimal(2.00).setScale(2, RoundingMode.DOWN);
        testDao.addMoney(testMoney);
        assertEquals(testMoney, testDao.getMoney());
    }
    
    @Test
    public void testCheckFunds() throws InsufficientFundsException{
        //Create snack object
        String name = "TestSnack";
        Snack testSnack = new Snack(name);
        testSnack.setQuantity(2);
        testSnack.setPrice(new BigDecimal(1.00));
        testDao.addSnack(name, testSnack);
        //Add user balance
        BigDecimal testMoney = new BigDecimal(2.00);
        testDao.addMoney(testMoney);
        
        //Test use case where user has enough funds.
        assertEquals((""), testDao.checkFunds(name));
        
        //Test use case where user does not have enough funds and InsufficientFundsException thrown
        InsufficientFundsException thrown = assertThrows(InsufficientFundsException.class, () -> {
            BigDecimal testMoney2 = new BigDecimal(.01);
            testDao.addMoney(testMoney2);
            testDao.checkFunds(name);
        });
        assertEquals(("Insufficient funds. TestSnack costs 1. You entered 0.01."), thrown.getMessage());  
    }
    
    @Test
    public void testBuySnackAndDecrement() throws NoItemInventoryException{
        //Create snack object
        String name = "TestSanck";
        Snack testSnack = new Snack(name);
        testSnack.setQuantity(2);
        testSnack.setPrice(new BigDecimal(1.00));
        testDao.addSnack(name, testSnack);
        //Add user balance
        BigDecimal testMoney = new BigDecimal(1.41);
        testDao.addMoney(testMoney);
        
        //Now action - run the function
        ChangeMaker change = testDao.buySnackAndDecrement(name);
        BigDecimal expected = new BigDecimal(1);
        //Assert change is as expected
        assertEquals(expected, change.getQuarters());
        assertEquals(expected, change.getDimes());
        assertEquals(expected, change.getNickels());
       
    }
}
