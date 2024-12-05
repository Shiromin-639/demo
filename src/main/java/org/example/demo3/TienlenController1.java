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

public class TienlenController1 implements Initializable {

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

    private TienlenGameLogic1 gameLogic;
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
        this.gameLogic = new TienlenGameLogic1(players);
        gameLogic.dealCards();
        updateUI();
    }

    @FXML
    private void onPlay() {
        /*gameLogic.playTurn(selectedCards);
        updateUI();
        selectedCards.clear();
        selectedCardsFlowPane.getChildren().clear();*/

        if (selectedCards.isEmpty()) return;
        boolean success = gameLogic.playTurn(selectedCards);
        if (success) {
            updateUI();
            selectedCards.clear();
            selectedCardsFlowPane.getChildren().clear();
        }
    }
    @FXML
    private void onSkip() {
        if (gameLogic.getGameState().isNewRound()) return;
        gameLogic.skipTurn();
        selectedCards.clear();
        selectedCardsFlowPane.getChildren().clear();
        updateUI();
    }
    @FXML
    public void onBackToMenuButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        stage.setScene(scene);
    }

    private void updateUI() {
        renderHand(gameLogic.getCurrentPlayer(), currentPlayerHandFlowPane);
        renderLastPlayedCards();
        renderGameState();
    }

    private void renderLastPlayedCards() {
        renderCards(gameLogic.getLastPlayedCards(), lastPlayedCardsFlowPane);
    }
    private void renderSelectedCards() {
        renderCards(selectedCards, selectedCardsFlowPane);
    }
    private void renderHand(Player currentPlayer, FlowPane currentPlayerHand) {
        if (currentPlayer.getHand().isEmpty())
            return;
        currentPlayerHand.getChildren().clear();
        for (Card card : currentPlayer.getHand()) {
            ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(card.getPathName())
            )));

            cardImageView.setFitWidth(100);
            cardImageView.setFitHeight(145.2);

            cardImageView.setOnMouseClicked(mouseEvent -> {
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
                renderSelectedCards();
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
    private void renderGameState() {
        currentPlayerLabel.setText("Current Player: " + gameLogic.getCurrentPlayer().getName());
        countLabel.setText("count: " + Integer.toString(count));
    }


}
