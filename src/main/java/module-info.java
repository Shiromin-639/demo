module org.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.smartcardio;
    requires java.desktop;


    opens org.example.demo3 to javafx.fxml;
    exports org.example.demo3;
    exports org.example.demo3.controllers;
    exports org.example.demo3.views;
    exports org.example.demo3.models;
    opens org.example.demo3.controllers to javafx.fxml;

}