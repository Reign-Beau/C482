package com.wgu.inventorytest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reign
 */
public class ModifyProductController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    //Objects
    @FXML private Button btnCancel;
    
    //Object functions
    @FXML private void cancelButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("primary.fxml"));
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
