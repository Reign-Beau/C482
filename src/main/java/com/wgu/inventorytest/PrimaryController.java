package com.wgu.inventorytest;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reign
 */
public class PrimaryController implements Initializable {

    //Main screen object entries
    private static Part modPart;
    private static Product modProduct;
    private static int modPartInt;
    private static int modProductInt;
    
    //Main FXML
    @FXML private Label lblMain;
    @FXML private Label lblParts;
    @FXML private Label lblProducts;
    @FXML private TextField textfieldSearchBox1;
    @FXML private TextField textfieldSearchBox2;
    @FXML private Button btnAddParts;
    @FXML private Button btnModifyParts;
    @FXML private Button btnDeleteParts;
    @FXML private Button btnAddProducts;
    @FXML private Button btnModifyProducts;
    @FXML private Button btnDeleteProducts;
    @FXML private Button btnExit;

    //tableview initialize
        //Parts tableview population
    @FXML private TableView<Part> tableviewParts;
    @FXML private TableColumn<Part, Integer> columnPartsID;
    @FXML private TableColumn<Part, String> columnPartsName;
    @FXML private TableColumn<Part, Integer> columnPartsStock;
    @FXML private TableColumn<Part, Double> columnPartsPrice;
    
    
    //Products tableview population
    @FXML private TableView<Product> tableviewProducts;
    @FXML private TableColumn<Product, Integer> columnProductsID;
    @FXML private TableColumn<Product, String> columnProductsName;
    @FXML private TableColumn<Product, Integer> columnProductsStock;
    @FXML private TableColumn<Product, Double> columnProductsPrice;
    
    //getters
    public static Part getModPart() {
    return modPart;
    }

    public static Product getModProduct() {
    return modProduct;
    }
    
    
    //Alert switch cases
private void displayAlert(int alertType) {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alertError = new Alert(Alert.AlertType.ERROR);

    switch (alertType) {
        case 1:
            alert.setTitle("Warning");
            alert.setHeaderText("Can't find part");
            alert.showAndWait();
            break;
        case 2:
            alert.setTitle("Warning");
            alert.setHeaderText("Can't find product");
            alert.showAndWait();
            break;
        case 3:
            alertError.setTitle("Error");
            alertError.setHeaderText("Part must be selected");
            alertError.showAndWait();
            break;
        case 4:
            alertError.setTitle("Error");
            alertError.setHeaderText("Product must be selected");
            alertError.showAndWait();
            break;
        case 5:
            alertError.setTitle("Error");
            alertError.setHeaderText("Parts Associated");
            alertError.setContentText("You must remove all parts before deleting");
            alertError.showAndWait();
            break;
        }
}
    /**
     *
     * @param event
     * @throws IOException
     */


    //main screen button on-click functions
    
    //Scene change - addPart onclick
    @FXML public void addPartsButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        Parent addPartParent = loader.load();
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    
    @FXML public void modifyPartsButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        Parent addPartParent = loader.load();
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    
    
    //deletes part selected with error and confirmation
    @FXML public void deletePartsButtonPushed() {
        Part selectedPart = tableviewParts.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }  
    };
    
    
    
    @FXML public void addProductsButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        //gets Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    
    @FXML public void modifyProductsButtonPushed(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        //gets Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    };
    
    @FXML public void deleteProductsButtonPushed() {
        Product selectedProduct = tableviewProducts.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    displayAlert(5);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    };
    
    //exit program
    @FXML public void exitButtonPushed() {
        System.exit(0);
    };

    
    //search features
     @FXML void partSearchBtnAction(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getPartArr();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = textfieldSearchBox1.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        tableviewParts.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayAlert(1);
        }
    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (textfieldSearchBox1.getText().isEmpty()) {
            tableviewParts.setItems(Inventory.getPartArr());
        }

    }

    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Set parts table instance
        columnPartsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPartsStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnPartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableviewParts.setItems(Inventory.getPartArr());
        
        //Set products table instance
        columnProductsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProductsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnProductsStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnProductsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableviewProducts.setItems(Inventory.getProductArr());
    }    
}
