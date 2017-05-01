/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.view;

import main.java.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author YannL
 */
public class ConsoleViewController {
    
    // Reference to the main application.
    private MainController mainController;
    
    @FXML 
    VBox console;
 
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ConsoleViewController() {

    }
    
    /**
     * Is called by the main main.src.controller to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainController mainController) {
        this.mainController = mainController;
    }
}
