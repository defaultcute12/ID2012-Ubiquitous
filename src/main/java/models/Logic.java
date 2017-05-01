package main.java.models;

import javafx.beans.property.FloatProperty;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ralambom on 21/04/17.
 */
public class Logic {

    private WashingMachine machine;
    private WashingMachine proposition;
    private ArrayList<Garment> garments;
    private HashMap<String, Boolean> warnings;

    public Logic(WashingMachine machine, ArrayList<Garment> garments) {
        this.machine = machine;
        this.proposition = machine;
        this.garments = garments;
        this.warnings = new HashMap<String, Boolean>();
        this.warnings.put("temperature", false);
        this.warnings.put("spin", false);
        this.warnings.put("weight", false);
        this.warnings.put("time", false);
        this.warnings.put("centrifuging", false);
        this.warnings.put("cycles", false);
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public ArrayList<Garment> getGarments() {
        return garments;
    }

    public void process() {
        float totalLoad = 0;

        for(Garment g : garments) {

            totalLoad += g.getWeight().get();

            //logic for temperature
            if(g.getMaxWashTemp().get() < machine.getTemperature().get()) {
                warnings.put("temperature", true);
                garments.remove(g);
                proposition.setTemperature(g.getMaxWashTemp());
            }

            //colorresist
            if(g.getColorbleedResistance().get() == true) {
                if(machine.getTemperature().get() > 40) {
                    warnings.put("temperature", true);
                    garments.remove(g);
                    proposition.getTemperature().setValue(40);
                }
                if(machine.getTime().get() > 60) {
                    warnings.put("time", true);
                    garments.remove(g);
                    proposition.getTemperature().setValue(60);
                }
            }

            //Spin limit
            if(g.getSpinningLimit().get() < machine.getCycleSpinIntensity().get()) {
                warnings.put("spin", true);
                garments.remove(g);
                proposition.setCycleSpinIntensity(g.getSpinningLimit());
            }

            //Yarn twist
            if(g.getYarnTwist().get() < 50) {
                if(machine.getCentrifuging().get()) {
                    warnings.put("centrifuging", true);
                    proposition.getCentrifuging().setValue(false);
                }
                if(g.getYarnTwist().get() < 30 && machine.getMultipleWashCycles().get() > 4) {
                    warnings.put("cycles", true);
                    proposition.getMultipleWashCycles().setValue(3);
                } else if(g.getYarnTwist().get() < 20 && machine.getMultipleWashCycles().get() > 3) {
                    warnings.put("cycles", true);
                    proposition.getMultipleWashCycles().setValue(2);
                }
                else if(g.getYarnTwist().get() < 10 && machine.getMultipleWashCycles().get() > 2) {
                    warnings.put("cycles", true);
                    proposition.getMultipleWashCycles().setValue(1);
                } else if(machine.getMultipleWashCycles().get() > 5) {
                    warnings.put("cycles", true);
                    proposition.getMultipleWashCycles().setValue(4);
                }
            }

        }

        //weight
        if(machine.getMaxLoad().get() < totalLoad) {
            glutonWeight();
            warnings.put("weight", true);
        }
    }

    protected void glutonWeight() {
        ArrayList<Garment> gList = new ArrayList<Garment>();
        float totalLoad = 0;
        Garment gTmp = new Garment();
        float wTmp = 0;
        while(totalLoad < machine.getMaxLoad().get()) {
            for(Garment g : garments) {
                if(wTmp < g.getWeight().get()) {
                    wTmp = g.getWeight().get();
                    gTmp = g;
                }
            }
            gList.add(gTmp);
            totalLoad += wTmp;
        }
        garments.clear();
        garments.addAll(gList);
    }

}

