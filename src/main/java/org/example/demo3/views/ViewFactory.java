package org.example.demo3.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.demo3.controllers.TienlenController1;

import java.io.IOException;

public class ViewFactory {

    private AnchorPane tienlenMenuView;
    private AnchorPane blackjackMenuView;

    public ViewFactory() {}

    public AnchorPane getTienlenMenuView() throws IOException {
        if (tienlenMenuView == null) {
            tienlenMenuView = new FXMLLoader(getClass().getResource("/FXML/tienlenMenu-view.fxml")).load();
        }
        return tienlenMenuView;
    }
    public AnchorPane getBlackjackMenuView() throws IOException {
        if (blackjackMenuView == null) {
            blackjackMenuView = new FXMLLoader(getClass().getResource("/FXML/blackjackMenu-view.fxml")).load();
        }
        return blackjackMenuView;
    }
    public void showMenuWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/main-view.fxml"));
        createStage(loader);
    }

    public void showTienlenWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/tienlen-view2.fxml"));
        createStage(loader);
    }

    public void showBlackjackWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/blackjack-view.fxml"));
        createStage(loader);
    }
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Playing Card Game");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
