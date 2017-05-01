package main.java.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.DbTest;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by Mallu on 26-04-2017.
 */
public class GarmentDB {

    public static ObservableList<Garment> showGarments () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Garment";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsGars = DbTest.dbExecuteQuery(selectStmt);

            //Send ResultSet to the garments method and get garment object
            ObservableList<Garment> garList = getGarmentList(rsGars);

            //Return garments object
            return garList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from garments operation
    private static ObservableList<Garment> getGarmentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of garment objects
        ObservableList<Garment> garList = FXCollections.observableArrayList();

        while (rs.next()) {
            Garment garment = new Garment();
            garment.setRtfid(rs.getInt("RFID_TAG_ID"));

            garment.setMaxWashTemp(rs.getInt("MAX_WASH_TEMP"));

            garment.setColorbleedResistance(rs.getBoolean("COLOR_BLEED_RESISTANCE"));

            garment.setWeight(rs.getFloat("WEIGHT"));

            garment.setSpinningLimit(rs.getInt("SPINNING_LIMIT"));

            garment.setYarnTwist(rs.getFloat("YARN_TWIST"));


            //Add garment to the ObservableList
            garList.add(garment);
        }
        //return garlist (ObservableList of garments)
        return garList;
    }
}
