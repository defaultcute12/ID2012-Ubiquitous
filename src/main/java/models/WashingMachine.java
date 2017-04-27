package main.java.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by ralambom on 21/04/17.
 */
public class WashingMachine {

    private IntegerProperty temperature;
    private IntegerProperty time;
    private IntegerProperty multipleWashCycles;
    private IntegerProperty cycleSpinIntensity;
    private IntegerProperty maxLoad;
    private BooleanProperty centrifuging;

    public WashingMachine(int temperature,
                          int time,
                          int multipleWashCycles,
                          int cycleSpinIntensity,
                          int maxLoad,
                          boolean cenProperty) {
        this.temperature = new SimpleIntegerProperty(temperature);
        this.time = new SimpleIntegerProperty(time);
        this.multipleWashCycles = new SimpleIntegerProperty(multipleWashCycles);
        this.cycleSpinIntensity = new SimpleIntegerProperty(cycleSpinIntensity);
        this.maxLoad = new SimpleIntegerProperty(maxLoad);
        this.centrifuging = new SimpleBooleanProperty(cenProperty);
    }

    /**
     * Default constructor.
     */
    public WashingMachine() {

    }

    public IntegerProperty getTemperature() {
        return temperature;
    }

    public void setTemperature(IntegerProperty temperature) {
        this.temperature = temperature;
    }

    public IntegerProperty getTime() {
        return time;
    }

    public void setTime(IntegerProperty time) {
        this.time = time;
    }

    public IntegerProperty getMultipleWashCycles() {
        return multipleWashCycles;
    }

    public void setMultipleWashCycles(IntegerProperty multipleWashCycles) {
        this.multipleWashCycles = multipleWashCycles;
    }

    public IntegerProperty getCycleSpinIntensity() {
        return cycleSpinIntensity;
    }

    public void setCycleSpinIntensity(IntegerProperty cycleSpinIntensity) {
        this.cycleSpinIntensity = cycleSpinIntensity;
    }

    public IntegerProperty getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(IntegerProperty maxLoad) {
        this.maxLoad = maxLoad;
    }

    public BooleanProperty getCentrifuging() {
        return centrifuging;
    }

    public void setCentrifuging(BooleanProperty centrifuging) {
        this.centrifuging = centrifuging;
    }
}
