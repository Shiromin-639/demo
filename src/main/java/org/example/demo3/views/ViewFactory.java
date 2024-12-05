package org.example.demo3.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo3.TienlenController1;

public class ViewFactory {
    public ViewFactory() {}

    public void showMenuWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/main-view.fxml"));
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

    public void showTienlenWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/tienlen-view.fxml"));
        TienlenController1 tienlenController1 = new TienlenController1();
        loader.setController(tienlenController1);
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
