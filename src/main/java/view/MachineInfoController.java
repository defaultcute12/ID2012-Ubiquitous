/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.view;

import main.java.controller.MainController;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.util.converter.NumberStringConverter;
import main.java.models.WashingMachine;

public class MachineInfoController {
    // Reference to the main main.src.controller.
    private MainController mainController;

    // Reference to the washing machine
    private WashingMachine machine;

    @FXML
    private Label brandNameLabel;
    @FXML
    private Label maxLoadLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Slider timeSlider;
    @FXML
    private ChoiceBox<Integer> temperature;
    @FXML
    private ChoiceBox<Integer> spinCycleIntensity;
    @FXML
    private ToggleButton centrifugingButton;

        /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MachineInfoController() {
    }


    public void setMainApp(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMachine(WashingMachine machine) {
        this.machine = machine;

        // We bind the main.src.view's component with value from the object model
        // When model change, it's automatically udpated into the main.src.view
        this.maxLoadLabel.textProperty().bind(machine.getMaxLoad().asString());

        this.timeLabel.textProperty().bindBidirectional(machine.getTime(),
                new NumberStringConverter());

        this.timeLabel.textProperty().bind(Bindings.format("%.2f",
                timeSlider.valueProperty()));

        this.temperature.valueProperty().bindBidirectional(machine.getTemperature().asObject());
        this.spinCycleIntensity.valueProperty().bindBidirectional(machine.getCycleSpinIntensity().asObject());
        //temp values
        temperature.getItems().addAll(25,30,40,50,35,10);
        temperature.setValue(25);
        //default
        spinCycleIntensity.setValue(56);



        //spinvalues
        spinCycleIntensity.getItems().addAll(56,45,33,76,87,32);
        this.centrifugingButton.selectedProperty().bindBidirectional(machine.getCentrifuging());
    }
}
