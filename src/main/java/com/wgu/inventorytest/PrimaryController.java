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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reign
 */
public class PrimaryController implements Initializable {

    //Main screen object entries
    @FXML private Label lblMain;
    @FXML private Label lblParts;
    @FXML private Label lblProducts;
    @FXML private TableView<?> tblviewParts;
    @FXML private TableView<?> tblviewProducts;
    @FXML private TextField textfieldSearchBox1;
    @FXML private TextField textfieldSearchBox2;
    @FXML private Button btnAddParts;
    @FXML private Button btnModifyParts;
    @FXML private Button btnDeleteParts;
    @FXML private Button btnAddProducts;
    @FXML private Button btnModifyProducts;
    @FXML private Button btnDeleteProducts;
    @FXML private Button btnExit;
    

    
    //main screen button on-click functions
    
    //Scene change - addPart onclick

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML public void addPartsButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        //gets Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    public void modifyPartsButtonPushed() {
  
    };
    
    @FXML public void deletePartsButtonPushed() {
  
    };
    
    @FXML public void addProductsButtonPushed() {
  
    };
    
    @FXML public void modifyProductsButtonPushed() {
  
    };
    
    @FXML public void deleteProductsButtonPushed() {
  
    };
    
    @FXML public void exitButtonPushed() {
  
    };
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
