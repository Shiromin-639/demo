package org.example.demo3;

import javafx.stage.Stage;
import org.example.demo3.views.ViewFactory;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/FXML/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMenuWindow();

    }

    public static void main(String[] args) {
        launch();
        /*List<Card> selectedCards = Arrays.asList(
                new Card(10, "hearts"),
                new Card(10, "diamonds")
        );

        List<Card> lastPlayedCards = Arrays.asList(
                new Card(9, "clubs"),
                new Card(9, "spades")
        );
        List<Card> lastPlayedCards = new ArrayList<>();

        if (TienlenGameLogic1.canBeat(selectedCards, lastPlayedCards)) {
            System.out.println("chan duoc");
        } else {
            System.out.println("khong chan duoc");
        }*/
    }
}