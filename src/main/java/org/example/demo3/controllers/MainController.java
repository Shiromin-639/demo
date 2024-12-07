package org.example.demo3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.demo3.models.Model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button blackjack_Button;
    @FXML
    private Button tienlen_Button;
    @FXML
    private BorderPane startMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tienlen_Button.setOnAction(actionEvent -> {
            try {
                startMenu.setCenter(Model.getInstance().getViewFactory().getTienlenMenuView());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        blackjack_Button.setOnAction(actionEvent -> {
            try {
                startMenu.setCenter(Model.getInstance().getViewFactory().getBlackjackMenuView());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }





}
