package main.java.models;

import javafx.beans.property.FloatProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by ralambom on 21/04/17.
 */
public class Logic {

    private WashingMachine machine;
    private WashingMachine proposition;
    private ArrayList<Garment> garments;
    private ArrayList<Garment> finalGarments;
    private HashMap<String, Boolean> warnings;

    public Logic(WashingMachine machine, Set<Garment> garments) {
        this.machine = new WashingMachine(machine);
        this.proposition = new WashingMachine(machine);
        this.garments = new ArrayList<Garment>();
        for(Garment g : garments) {
            this.garments.add(new Garment(g));
        }
        this.finalGarments = new ArrayList<Garment>();
        for(Garment g : garments) {
            this.finalGarments.add(new Garment(g));
        }
        this.warnings = new HashMap<String, Boolean>();
        this.warnings.put("temperature", false);
        this.warnings.put("spin", false);
        this.warnings.put("weight", false);
        this.warnings.put("time", false);
        this.warnings.put("centrifuging", false);
        this.warnings.put("cycles", false);
    }

    public HashMap<String, Boolean> getWarnings() {
        return warnings;
    }

    public void process() {
        float totalLoad = 0;

        for(Garment g : garments) {

            totalLoad += g.getWeight().get();

            //logic for temperature
            if(g.getMaxWashTemp().get() < machine.getTemperature().get()) {
                warnings.put("temperature", true);
                finalGarments.remove(g);
                proposition.setTemperature(g.getMaxWashTemp());
            }

            //colorresist
            if(g.getColorbleedResistance().get().equals("Low")) {
                if(machine.getTemperature().get() > 40) {
                    warnings.put("temperature", true);
                    finalGarments.remove(g);
                    proposition.getTemperature().setValue(40);
                }
                if(machine.getTime().get() > 60) {
                    warnings.put("time", true);
                    finalGarments.remove(g);
                    proposition.getTemperature().setValue(60);
                }
            } else if(g.getColorbleedResistance().get().equals("Medium")) {
                if (machine.getTemperature().get() > 60) {
                    warnings.put("temperature", true);
                    finalGarments.remove(g);
                    proposition.getTemperature().setValue(60);
                }
                if (machine.getTime().get() > 80) {
                    warnings.put("time", true);
                    finalGarments.remove(g);
                    proposition.getTemperature().setValue(80);
                }
            }

            //Spin limit
            if(g.getSpinningLimit().get() < machine.getCycleSpinIntensity().get()) {
                warnings.put("spin", true);
                finalGarments.remove(g);
                proposition.setCycleSpinIntensity(g.getSpinningLimit());
            }

            //Yarn twist
            if(g.getYarnTwist().get() < 50) {
                if(machine.getCentrifuging().get()) {
                    warnings.put("centrifuging", true);
                    finalGarments.remove(g);
                    proposition.getCentrifuging().setValue(false);
                }
                if(g.getYarnTwist().get() < 30 && machine.getMultipleWashCycles().get() > 4) {
                    warnings.put("cycles", true);
                    finalGarments.remove(g);
                    proposition.getMultipleWashCycles().setValue(3);
                } else if(g.getYarnTwist().get() < 20 && machine.getMultipleWashCycles().get() > 3) {
                    warnings.put("cycles", true);
                    finalGarments.remove(g);
                    proposition.getMultipleWashCycles().setValue(2);
                }
                else if(g.getYarnTwist().get() < 10 && machine.getMultipleWashCycles().get() > 2) {
                    warnings.put("cycles", true);
                    finalGarments.remove(g);
                    proposition.getMultipleWashCycles().setValue(1);
                } else if(machine.getMultipleWashCycles().get() > 5) {
                    warnings.put("cycles", true);
                    finalGarments.remove(g);
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

