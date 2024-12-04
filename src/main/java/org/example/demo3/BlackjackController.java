package org.example.demo3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.demo3.model.BlackjackGameLogic;
import org.example.demo3.model.Card;
import org.example.demo3.model.Deck;
import org.example.demo3.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class BlackjackController implements Initializable {

    private BlackjackGameLogic gameLogic;
    private boolean isGameOver = false;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Button playAgain;
    @FXML
    private Button hitButton;
    @FXML
    private Button standButton;

    @FXML
    private Label playerScore;
    @FXML
    private Label dealerScore;

    @FXML
    private HBox playerCardsHBox;
    @FXML
    private HBox dealerCardsHBox;

    @FXML
    private void onPlayAgainButtonClicked() {
       playerCardsHBox.getChildren().clear();
       dealerCardsHBox.getChildren().clear();


    }

    @FXML
    public void onBackToMenuButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Long"));

        this.gameLogic = new BlackjackGameLogic(players);
        gameLogic.dealInitialCards();
        updateUI();
    }

    private void updateUI() {
        renderHand(gameLogic.getDealer().getHand(), dealerCardsHBox);
        dealerScore.setText("Dealer's Score: " + gameLogic.calculateScore(gameLogic.getDealer()));

        Player currentPlayer = gameLogic.getCurrentPlayer();
        renderHand(currentPlayer.getHand(), playerCardsHBox);
        playerScore.setText("Player's Score: " + gameLogic.calculateScore(currentPlayer));
    }

    private void renderHand(ArrayList<Card> hand, HBox cardHBox) {
        cardHBox.getChildren().clear();
        for (Card card : hand) {
            ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    card.getPathName()
            ))));
            cardImageView.setFitHeight(100);
            cardImageView.setFitWidth(75);
            cardHBox.getChildren().add(cardImageView);
        }
    }

    @FXML
    private void onHit() {
        if (isGameOver) return;
        Player currentPlayer = gameLogic.getCurrentPlayer();
        gameLogic.playerHit(currentPlayer);
        updateUI();

        if (gameLogic.isBust(currentPlayer)) {
            playerScore.setText("Bạn bị quắc! Điểm: " + gameLogic.calculateScore(currentPlayer));
        }
        disableActions();
    }

    @FXML
    private void onStand() {
        gameLogic.dealerTurn();
        updateUI();

        String result = gameLogic.checkResult();
        playerScore.setText(result);
        disableActions();
    }

    private void disableActions() {
        dealerCardsHBox.setDisable(true);
        playerCardsHBox.setDisable(true);
    }




}
