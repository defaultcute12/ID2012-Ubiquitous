/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import main.java.controller.MainController;
import main.java.models.Garment;
import main.java.models.GarmentDB;

import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Mallu
 */
public class GarmentListController {

    // Reference to the main main.src.controller.
    private MainController mainController;
    @FXML
    private TableView<Garment> garment;

    @FXML
    private TableColumn<Garment, Integer> RFID;
    @FXML
    private TableColumn<Garment, Integer>  MaxWashTemp;
    @FXML
    private TableColumn<Garment, String> ColorBleedResist;
    @FXML
    private TableColumn<Garment, Float> Weight;
    @FXML
    private TableColumn<Garment, Integer> SpinningLimit;
    @FXML
    private TableColumn<Garment, Float> YarnTwist;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public GarmentListController() {
    }

    @FXML
    //Initializer
    private void initialize () {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the garments objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
        RFID.setCellValueFactory(cellData -> cellData.getValue().rfidProperty().asObject());
        MaxWashTemp.setCellValueFactory(cellData -> cellData.getValue().maxWashTempProperty().asObject());
        //ColorBleedResist.setCellValueFactory(cellData -> cellData.getValue().colorbleedResistanceProperty());
        ColorBleedResist.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Garment, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Garment, String> param) {
                if (param.getValue().colorbleedResistanceProperty().getValue())
                    return new SimpleStringProperty("Yes");
                else
                    return new SimpleStringProperty("No");
            }
        });
        Weight.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        SpinningLimit.setCellValueFactory(cellData -> cellData.getValue().spinningLimitProperty().asObject());
        YarnTwist.setCellValueFactory(cellData -> cellData.getValue().yarnTwistProperty().asObject());


        // Fetch and display garments from the DB
        try {
            showGarments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Populate garment
    @FXML
    private void populateGarment (Garment gar) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Garment> garData = FXCollections.observableArrayList();
        //Add garment to the ObservableList
        garData.add(gar);
        //Set items to the garmentstable
        garment.setItems(garData);
    }

    @FXML
    private void populateGarments (ObservableList<Garment> garData) throws ClassNotFoundException {
        //Set items to the garmentstable
        garment.setItems(garData);
    }
    

    public void setMainApp(MainController mainController) {
        this.mainController = mainController;
    }

    public void showGarments() throws SQLException {
        try {
            //Get all garments information
            ObservableList<Garment> garData = GarmentDB.showGarments();
            //Populate garments on TableView
            populateGarments(garData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting garments information from DB.\n" + e);
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
