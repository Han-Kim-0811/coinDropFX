module com.example.coindropfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coindropfx to javafx.fxml;
    exports com.example.coindropfx;
}