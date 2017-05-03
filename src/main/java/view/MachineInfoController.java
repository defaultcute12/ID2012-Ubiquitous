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
        this.maxLoadLabel.textProperty().bind(this.machine.getMaxLoad().asString());

        this.timeLabel.textProperty().bindBidirectional(this.machine.getTime(),
                new NumberStringConverter());

        this.timeLabel.textProperty().bind(Bindings.format("%.2f",
                timeSlider.valueProperty()));

        this.machine.getTemperature().bind(this.temperature.getSelectionModel().selectedItemProperty());
        this.temperature.valueProperty().bindBidirectional(this.machine.getTemperature().asObject());
        this.machine.getCycleSpinIntensity().bind(this.spinCycleIntensity.getSelectionModel().selectedItemProperty());
        this.spinCycleIntensity.valueProperty().bindBidirectional(this.machine.getCycleSpinIntensity().asObject());
        //temp values
        temperature.getItems().addAll(10,25,30,35,40,50);
        temperature.setValue(25);
        //default
        spinCycleIntensity.setValue(56);
        //spinvalues
        spinCycleIntensity.getItems().addAll(32,45,56,76,87);

        this.centrifugingButton.selectedProperty().bindBidirectional(machine.getCentrifuging());
    }

    public WashingMachine getMachine() {
        return this.machine;
    }
}
