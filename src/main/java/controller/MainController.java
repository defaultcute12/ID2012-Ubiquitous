/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import main.java.view.MachineInfoController;
import javafx.fxml.FXML;
import main.java.models.WashingMachine;
import main.java.view.ConsoleViewController;
import main.java.view.GarmentListController;

/**
 *
 * @author YannL
 */
public class MainController {
    @FXML
    MachineInfoController machineInfoController;
    @FXML
    GarmentListController garmentListController;
    @FXML
    ConsoleViewController consoleViewController;

    @FXML
    public void initialize() {
        // Give a reference on the mainController to children controllers
        // A way to communicate bewteen each others
        machineInfoController.setMainApp(this);
        garmentListController.setMainApp(this);
        consoleViewController.setMainApp(this);

        // Hard coded to test the implementation
        machineInfoController.setMachine(new WashingMachine(90, 60, 2, 1000, 15, true));
    }

    public MachineInfoController getMachineInfoController() {
        return machineInfoController;
    }

    public GarmentListController getGarmentListController() {
        return garmentListController;
    }

    public ConsoleViewController getConsoleViewController() {
        return consoleViewController;
    }

    /**
     * FUNCTIONS
     */


}
