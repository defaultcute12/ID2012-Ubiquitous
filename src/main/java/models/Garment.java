package main.java.models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.*;

/**
 * Created by Mallu on 27/04/17.
 */
public class Garment {

    private IntegerProperty rtfid;
    private IntegerProperty maxWashTemp;
    private StringProperty colorbleedResistance;
    private FloatProperty weight;
    private IntegerProperty spinningLimit;
    private FloatProperty yarnTwist;


    //Modified to communicate to the db and fx
    public Garment() {
        this.rtfid = new SimpleIntegerProperty();
        this.maxWashTemp = new SimpleIntegerProperty();
        this.colorbleedResistance = new SimpleStringProperty();
        this.weight = new SimpleFloatProperty();
        this.spinningLimit = new SimpleIntegerProperty();
        this.yarnTwist = new SimpleFloatProperty();

    }

    public Garment(Garment g) {
        this.rtfid = new SimpleIntegerProperty();
        this.maxWashTemp = new SimpleIntegerProperty();
        this.colorbleedResistance = new SimpleStringProperty();
        this.weight = new SimpleFloatProperty();
        this.spinningLimit = new SimpleIntegerProperty();
        this.yarnTwist = new SimpleFloatProperty();
        this.rtfid.set(g.getRtfid().get());
        this.maxWashTemp.set(g.getMaxWashTemp().get());
        this.colorbleedResistance.setValue(g.colorbleedResistance.getValue());
        this.weight.set(g.getWeight().get());
        this.spinningLimit.set(g.getSpinningLimit().get());
        this.yarnTwist.set(g.getYarnTwist().get());
    }

    //Getter setter and properties
    //RFID
    public IntegerProperty getRtfid() {
        return rtfid;
    }

    public void setRtfid(int rtfid) {
        this.rtfid.set(rtfid);
    }

    public IntegerProperty rfidProperty(){
        return rtfid;
    }

    //MaxwashTemp
    public IntegerProperty getMaxWashTemp() {
        return maxWashTemp;
    }

    public void setMaxWashTemp(int maxWashTemp) {
        this.maxWashTemp.set(maxWashTemp);
    }

    public IntegerProperty maxWashTempProperty(){
        return maxWashTemp;
    }

    //colorresistance
    public StringProperty getColorbleedResistance() {
        return colorbleedResistance;
    }

    public void setColorbleedResistance(String colorbleedResistance) {
        this.colorbleedResistance.set(colorbleedResistance);
    }

    public StringProperty colorbleedResistanceProperty(){
        return colorbleedResistance;
    }

    //weight
    public FloatProperty getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight.set(weight);
    }

    public FloatProperty weightProperty(){
        return weight;
    }

    //spin
    public IntegerProperty getSpinningLimit() {
        return spinningLimit;
    }

    public void setSpinningLimit(int spinningLimit) {
        this.spinningLimit.set(spinningLimit);
    }

    public IntegerProperty spinningLimitProperty(){
        return spinningLimit;
    }

    //yarn
    public FloatProperty getYarnTwist() {
        return yarnTwist;
    }

    public void setYarnTwist(float yarnTwist) {
        this.yarnTwist.set(yarnTwist);
    }

    public FloatProperty yarnTwistProperty(){
        return yarnTwist;
    }

}
