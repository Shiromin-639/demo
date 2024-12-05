package org.example.demo3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.example.demo3.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class TienlenController implements Initializable {

    @FXML
    private FlowPane currentPlayerHandFlowPane;
    @FXML
    private FlowPane selectedCardsFlowPane = new FlowPane();
    @FXML
    private FlowPane lastPlayedCardsFlowPane = new FlowPane();

    @FXML
    private Label countLabel;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Button play;

    @FXML
    private Button skip;

    @FXML
    private Button backToMenuButton;
    private TienlenGameLogic gameLogic;
    private ArrayList<Card> selectedCards = new ArrayList<>();
    private static int count;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        players.add(new Player("Player3"));
        players.add(new Player("Player4"));
        count = 0;
        this.gameLogic = new TienlenGameLogic(players);
        gameLogic.dealCards();
        currentPlayerLabel.setText("Current Player: " + gameLogic.getCurrentPlayer().getName());
        updateUI();
        //updateUI();
    }

    private void initializeFlowPane() {

        Player currentPlayer = gameLogic.getCurrentPlayer();
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            Card card = currentPlayer.getHand().get(i);

            ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    card.getPathName()
            ))));

            cardImageView.setFitHeight(145.2);
            cardImageView.setFitWidth(100);
            cardImageView.setUserData(i);

            cardImageView.setOnMouseClicked(mouseEvent -> {
                //manageSelectedCard((int) cardImageView.getUserData());
                countLabel.setText("count: " + Integer.toString(++count));
            });

            currentPlayerHandFlowPane.getChildren().add(cardImageView);

        }
    }

    @FXML
    private void onPlay() {
        gameLogic.getCurrentPlayer().getHand().removeAll(selectedCards);
        gameLogic.setLastPlayedCards(selectedCards);
        count = 0;
        gameLogic.setCurrentPlayerIndex((gameLogic.getCurrentPlayerIndex() + 1) % 4);
        updateUI();
        selectedCards.clear();
        selectedCardsFlowPane.getChildren().clear();
    }
    @FXML
    private void onSkip() {
        gameLogic.skipTurn();

    }
    private void manageSelectedCard() {

    }


    private void updateUI() {
        currentPlayerHandFlowPane.getChildren().clear();
        renderHand(gameLogic.getCurrentPlayer(), currentPlayerHandFlowPane);
        currentPlayerLabel.setText("Current Player: " + gameLogic.getCurrentPlayer().getName());
        renderCards(gameLogic.getLastPlayedCards(), lastPlayedCardsFlowPane);
        countLabel.setText("count: 0");
    }

    private void renderHand(Player currentPlayer, FlowPane currentPlayerHand) {

        for (Card card : currentPlayer.getHand()) {
            ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(card.getPathName())
            )));

            cardImageView.setFitWidth(100);
            cardImageView.setFitHeight(145.2);

            cardImageView.setOnMouseClicked(mouseEvent -> {

                //manageSelectedCard((int) cardImageView.getUserData());
                if (card.isSelected()) {
                    cardImageView.setTranslateY(0);
                    selectedCards.remove(card);
                    card.setSelected(false);
                    count--;
                } else {
                    selectedCards.add(card);
                    card.setSelected(true);
                    cardImageView.setTranslateY(-15);
                    count++;
                    //cardImageView.setStyle("-fx-effect: dropshadow(gaussian, yellow, 10, 0, 0, 0);");
                }
                countLabel.setText("count: " + Integer.toString(count));
                renderCards(selectedCards, selectedCardsFlowPane);
            });
            currentPlayerHand.getChildren().add(cardImageView);
        }
    }

    private void renderCards(ArrayList<Card> cards, FlowPane cardsFlowPane) {
        if(cards == null) return;
        cardsFlowPane.getChildren().clear();
        for (Card card : cards) {
            ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(card.getPathName())
            )));
            cardImageView.setFitWidth(50);
            cardImageView.setFitHeight(72.6);

            cardsFlowPane.getChildren().add(cardImageView);
        }
    }


    @FXML
    public void onBackToMenuButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        stage.setScene(scene);
    }
}
