module com.example.vocabularytms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vocabularytms to javafx.fxml;
    exports com.example.vocabularytms;
}