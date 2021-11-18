/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vendingmachine;

import com.vendingmachine.controller.VendingMachineController;
import com.vendingmachine.dao.VendingMachineDao;
import com.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.vendingmachine.ui.UserIO;
import com.vendingmachine.ui.UserIOConsoleImpl;
import com.vendingmachine.ui.VendingMachineView;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author Blaine
 */
public class App {
      public static void main(String[] args) {
          UserIO vmIo = new UserIOConsoleImpl();
          VendingMachineView vmView = new VendingMachineView(vmIo);
          VendingMachineDao vmDao = new VendingMachineDaoFileImpl();
          VendingMachineServiceLayerImpl vmService = new VendingMachineServiceLayerImpl(vmDao);
          VendingMachineController controller = new VendingMachineController(vmService, vmView, vmIo);
          controller.run();
    }   
}