module com.wgu.inventorytest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.wgu.inventorytest to javafx.fxml;
    exports com.wgu.inventorytest;
}
