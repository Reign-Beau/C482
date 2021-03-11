package com.wgu.inventorytest;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
        
        
public class ModifyPartController implements Initializable {

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
    private Part selectedPart;   
 
    //Alerts
private void displayAlert(int alertType) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    
    switch (alertType) {
        case 1:
            alert.setTitle("Error"); 
            alert.setHeaderText("Error Modifying Product");
            alert.setContentText("Form cannot contain blank fields or invalid values.");
            alert.showAndWait();
            break;
        case 2:
            alertInfo.setTitle("Information");
            alertInfo.setHeaderText("Can't find part");
            alertInfo.showAndWait();
            break;
        case 3:
            alert.setTitle("Error");
            alert.setHeaderText("Invalid value for Min");
            alert.setContentText("Min must be a number greater than 0 and less than Max.");
            alert.showAndWait();
            break;
        case 4:
            alert.setTitle("Error");
            alert.setHeaderText("Invalid value for Inventory");
            alert.setContentText("Inventory must be a number equal to or between Min and Max");
            alert.showAndWait();
            break;
        case 5:
            alert.setTitle("Error");
            alert.setHeaderText("Part must be selected");
            alert.showAndWait();
            break;
        case 7:
            alert.setTitle("Error");
            alert.setHeaderText("No Name Entered");
            alert.setContentText("You must enter a name");
            alert.showAndWait();
            break;
    }
};
    
    
    //set changing label
    @FXML void radioInHouseAction(ActionEvent event) {
        textfieldChangingID.setText("Machine ID");
    }
    
    @FXML void radioOutsourcedAction(ActionEvent event) {
    textfieldChangingID.setText("Machine ID");
    };
    
    //min and inventory validity check
    private boolean minCheck(int min, int max) {
    boolean isValid = true;
    if (min <= 0 || min >= max) {
        isValid = false;
        displayAlert(3);
    }
    return isValid;
    }
    
    
    private boolean inventoryCheck(int min, int max, int stock) {
        boolean isValid = true;
        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }
        return isValid;
    }
    
    //Cancel button
    @FXML public void cancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Cancel and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Primary.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();;
        }
    };
    
    //save button checks error, updates, and reverts to main
@FXML void saveButttonPushed(ActionEvent event) throws IOException {
    try {
        int id = selectedPart.getId();
        String name = textfieldPartName.getText();
        Double price = Double.parseDouble(textfieldPartPrice.getText());
        int stock = Integer.parseInt(textfieldPartStock.getText());
        int min = Integer.parseInt(textfieldPartMin.getText());
        int max = Integer.parseInt(textfieldPartMax.getText());
        int machineId;
        boolean partAddSuccessful = false;
        String companyName;

        if (minCheck(min, max) && inventoryCheck(min, max, stock)) {
            if (radioInHouse.isSelected()) {
                try {
                    machineId = Integer.parseInt(textfieldPartID.getText());
                    InHouse newInHousePart = new InHouse(id, name, price, stock, min, max);
                    Inventory.addPart(newInHousePart);
                    partAddSuccessful = true;
                } catch (Exception e) {
                    displayAlert(2);
                }
            }

            if (radioOutsourced.isSelected()) {
                companyName = textfieldPartID.getText();
                Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                        companyName);
                Inventory.addPart(newOutsourcedPart);
                partAddSuccessful = true;
            }

            if (partAddSuccessful) {
                Inventory.deletePart(selectedPart);
                cancelButtonPushed(event);
            }
        }
    } catch(Exception e) {
        displayAlert(1);
    }}
        
        //initializing text fields with parts
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        selectedPart = PrimaryController.getModPart();

        if (selectedPart instanceof InHouse) {
            radioInHouse.setSelected(true);
            lblPartID.setText("Machine ID");
            textfieldChangingID.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof Outsourced){
            radioOutsourced.setSelected(true);
            lblPartID.setText("Company Name");
            textfieldChangingID.setText(((Outsourced) selectedPart).getCompanyName());
        }


        textfieldPartID.setText(String.valueOf(selectedPart.getId()));
        textfieldPartName.setText(selectedPart.getName());
        textfieldPartStock.setText(String.valueOf(selectedPart.getStock()));
        textfieldPartPrice.setText(String.valueOf(selectedPart.getPrice()));
        textfieldPartMax.setText(String.valueOf(selectedPart.getMax()));
        textfieldPartMin.setText(String.valueOf(selectedPart.getMin()));
    }

 }