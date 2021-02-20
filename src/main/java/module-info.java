module com.wgu.inventorytest {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.wgu.inventorytest to javafx.fxml;
    exports com.wgu.inventorytest;
}
