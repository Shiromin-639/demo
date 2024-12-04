package org.example.demo3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button switchToBlackjack;
    @FXML
    private Button switchToTienlen;

    private void initializeLabel() {
        label.setText("Chọn chế độ chơi:");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeLabel();
    }

    @FXML
    public void onSwitchToBlackjackClicked() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("blackjack-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) switchToBlackjack.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void onSwitchToTienlenClicked() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tienlen-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) switchToTienlen.getScene().getWindow();
        stage.setScene(scene);
    }
}
