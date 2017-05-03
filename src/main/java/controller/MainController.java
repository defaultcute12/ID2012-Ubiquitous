/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import main.java.models.Garment;
import main.java.models.Logic;
import main.java.view.MachineInfoController;
import javafx.fxml.FXML;
import main.java.models.WashingMachine;
import main.java.view.ConsoleViewController;
import main.java.view.GarmentListController;

import java.util.Set;

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
    private Logic logic;

    @FXML
    public void initialize() {
        // Give a reference on the mainController to children controllers
        // A way to communicate bewteen each others
        machineInfoController.setMainApp(this);
        garmentListController.setMainApp(this);
        consoleViewController.setMainApp(this);

        // Hard coded to test the implementation
        machineInfoController.setMachine(new WashingMachine(90, 60, 1000, 15, true));
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

    public void startWashing(Set<Garment> garments) {
        logic = new Logic(machineInfoController.getMachine(), garments);
        logic.process();
        consoleViewController.displayAnalysis(logic);
    }


    public void displayGarmentsSelectedInConsole(Set<Garment> garments) {
        consoleViewController.displayGarmentsSelected(garments);
    }
}
