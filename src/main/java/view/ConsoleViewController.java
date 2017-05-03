/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.view;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.java.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import main.java.models.Garment;
import main.java.models.Logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
     * @param mainController
     */
    public void setMainApp(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Simply display all the selected garments in the console
     * @param garments
     */
    public void displayGarmentsSelected(Set<Garment> garments) {
        StringBuilder txt = new StringBuilder();

        txt.append("------------------------\n");
        txt.append("    Selected garments   \n");
        txt.append("------------------------\n");
        for (Garment g : garments) {
            txt.append(g.getRtfid().get() + "\n");
        }

        displayText(txt.toString(), true);

        //System.err.println(txt.toString());
    }

    public void displayAnalysis(Logic logic) {
        StringBuilder txt = new StringBuilder();

        txt.append("------------------------\n");
        txt.append("        Results         \n");
        txt.append("------------------------\n");
        HashMap<String, Boolean> warnings = logic.getWarnings();
        if(!warnings.containsValue(true)) {
            txt.append("OK - No warning");
        } else {
            //TODO
            txt.append("Contains warnings:\n");
            for (Map.Entry<String, Boolean> entry : warnings.entrySet()) {
                if(entry.getValue()) {
                    txt.append("- " + entry.getKey() + "\n");
                }
            }
            txt.append("\n");
            txt.append("-- Recommanded selection of garments for this machine's settings --\n");
            for(Garment g : logic.getFinalGarments()) {
                txt.append(g.getRtfid().getValue().toString() + "\n");
            }
            txt.append("\n");
            txt.append("-- Recommanded machine's settings for this selection of garments --\n");
            txt.append(logic.getProposition().toString());
        }
        displayText(txt.toString(), false);
    }


    private void displayText(String txt, boolean clearConsoleBefore) {
        if (clearConsoleBefore)
            console.getChildren().clear();

        Text text = new Text(txt);
        text.setFill(Color.WHITE);
        console.getChildren().add(text);
    }
}
