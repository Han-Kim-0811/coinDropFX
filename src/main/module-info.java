module com.example.coindropfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ui to javafx.fxml;
    exports ui;
}