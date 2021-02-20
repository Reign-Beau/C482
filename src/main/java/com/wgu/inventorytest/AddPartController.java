package com.wgu.inventorytest;

import java.io.IOException;
import javafx.fxml.FXML;

public class AddPartController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}