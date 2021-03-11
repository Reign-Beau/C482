package com.wgu.inventorytest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reign
 */
public class AddPartController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML private Label lblMain;
    @FXML private Label lblPartID;
    @FXML private Label lblPartName;
    @FXML private Label lblPartStock;
    @FXML private Label lblPartPrice;
    @FXML private Label lblPartMin;
    @FXML private Label lblPartMax;
    @FXML private Label lblChangingID;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private RadioButton radioInHouse;
    @FXML private RadioButton radioOutsourced;
    @FXML private TextField textfieldPartID;
    @FXML private TextField textfieldPartName;
    @FXML private TextField textfieldPartStock;
    @FXML private TextField textfieldPartPrice;
    @FXML private TextField textfieldPartMax;
    @FXML private TextField textfieldPartMin;
    @FXML private TextField textfieldChangingID;
    @FXML private String errorMessage = new String();
    @FXML private boolean isOutsourced;
    
    //Object functions
    @FXML private void cancelButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        //gets Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    
    // Field for parts tableview and initialization
    private ObservableList<InHouse> tableviewPartsItems;
    public void setTableviewPartsItems(ObservableList<InHouse> tableviewPartsItems) {
        this.tableviewPartsItems = tableviewPartsItems;
}
    
    /**
     * Method to create new In-House and Out-Sourced Parts and add it to tableviewParts
     * @param event 
     * @throws java.io.IOException 
     */
    
    //new in-house part parsing field input and switching back to primary.fxml upon confirmation
    public void saveButtonPushed(ActionEvent event) throws IOException {
            tableviewPartsItems.add(new InHouse(Integer.parseInt(textfieldPartID.getText()),
                                         textfieldPartName.getText(),
                                         Double.parseDouble(textfieldPartStock.getText()),
                                         Integer.parseInt(textfieldPartPrice.getText()),
                                         Integer.parseInt(textfieldPartMax.getText()),
                                         Integer.parseInt(textfieldPartMin.getText())
            ));
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent addPartParent = loader.load();
        Scene addPartScene = new Scene(addPartParent);
    
        //gets Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
