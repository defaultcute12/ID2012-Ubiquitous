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
    private FloatProperty time;
    private IntegerProperty cycleSpinIntensity;
    private FloatProperty maxLoad;
    private BooleanProperty centrifuging;

    public WashingMachine(int temperature,
                          float time,
                          int cycleSpinIntensity,
                          float maxLoad,
                          boolean cenProperty) {
        this.temperature = new SimpleIntegerProperty(temperature);
        this.time = new SimpleFloatProperty(time);
        this.cycleSpinIntensity = new SimpleIntegerProperty(cycleSpinIntensity);
        this.maxLoad = new SimpleFloatProperty(maxLoad);
        this.centrifuging = new SimpleBooleanProperty(cenProperty);
    }

    public WashingMachine(WashingMachine machine) {
        this.temperature = machine.getTemperature();
        this.time = machine.getTime();
        this.cycleSpinIntensity = machine.getCycleSpinIntensity();
        this.maxLoad = machine.getMaxLoad();
        this.centrifuging = machine.getCentrifuging();
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

    public FloatProperty getTime() {
        return time;
    }

    public void setTime(FloatProperty time) {
        this.time = time;
    }

    public IntegerProperty getCycleSpinIntensity() {
        return cycleSpinIntensity;
    }

    public void setCycleSpinIntensity(IntegerProperty cycleSpinIntensity) {
        this.cycleSpinIntensity = cycleSpinIntensity;
    }

    public FloatProperty getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(FloatProperty maxLoad) {
        this.maxLoad = maxLoad;
    }

    public BooleanProperty getCentrifuging() {
        return centrifuging;
    }

    public void setCentrifuging(BooleanProperty centrifuging) {
        this.centrifuging = centrifuging;
    }

    public String toString() {
        String string = "";

        string = "temperature: " + temperature.getValue().toString() + "\n"
                + "time: " + time.getValue().toString() + "\n"
                + "intensity of spin: " + cycleSpinIntensity.getValue().toString() + "\n"
                + "centrifuging: " + centrifuging.getValue().toString() + "\n";

        return string;
    }
}
