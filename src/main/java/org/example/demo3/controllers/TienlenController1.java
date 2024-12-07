package org.example.demo3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.demo3.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TienlenController1 implements Initializable {

    @FXML
    private StackPane topStackPane;
    @FXML
    private StackPane leftStackPane;
    @FXML
    private StackPane rightStackPane;
    @FXML
    private StackPane botStackPane;
    @FXML
    private StackPane lastPlayedCardsStackPane;
    @FXML
    private BorderPane rootPane;

    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label gameStatusLabel;
    @FXML
    private Button play;
    @FXML
    private Button skip;
    @FXML
    private Button backToMenuButton;

    private TienlenGame gameLogic;
    private final ArrayList<Card> selectedCards = new ArrayList<>();
    private List<CardImage> cardImages = new ArrayList<>();
    private static int count;
    private final StackPane[] stackPanes = {rightStackPane, topStackPane, leftStackPane, botStackPane};
    public TienlenController1() {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //StackPane[] stackPanes = {rightStackPane, topStackPane, leftStackPane, botStackPane};
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));
        players.add(new Player("Player3"));
        players.add(new Player("Player4"));
        count = 0;
        this.gameLogic = new TienlenGame(players);
        gameLogic.dealCards();
        updateUI();
    }

    // xử lí gui khi click đánh bài
    @FXML
    private void onPlay() {
        ArrayList<Card> selectedCards = getSelectedCards();
        if (selectedCards.isEmpty()) {
            return;
        }
        boolean success = gameLogic.playTurn(selectedCards);
        if (success) {
            if (gameLogic.isGameOver()) {
                Player winner = gameLogic.getCurrentPlayer();
                handleGameOver(winner);
            }
            updateUI();
        } else {
            return;
        }
    }
    //  xử lí gui khi cleck bỏ lượt
    @FXML
    private void onSkip() {
        gameLogic.skipTurn();
        updateUI();
    }
    // nút trở về menu
    @FXML
    public void onBackToMenuButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) backToMenuButton.getScene().getWindow();
        stage.setScene(scene);
    }
    // cập nhật gui sau khi người chơi thực hiện 1 thac tác đánh hoặc bỏ lượt
    private void updateUI() {
        renderHand(gameLogic.getPlayers().get(0), rightStackPane);
        renderHand(gameLogic.getPlayers().get(1), topStackPane);
        renderHand(gameLogic.getPlayers().get(2), leftStackPane);
        renderHand(gameLogic.getPlayers().get(3), botStackPane);
        renderLastPlayedCards();
        //renderGameState();
        clearSelectedCard();
    }
    //xử lí khi game over
    private void handleGameOver(Player winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Please end game");
        alert.showAndWait();
    }
    //hiển thị bài vừa đánh(chưa ai chặn)
    private void renderLastPlayedCards() {
        renderCards(gameLogic.getLastPlayedCards(), lastPlayedCardsStackPane);
    }
    //hiển thị bài trên tay của người chơi hiện tại
    private void renderHand(Player player, StackPane playerStackPane) {
        if (player.getHand().isEmpty())
            return;
        playerStackPane.getChildren().clear();
        int i = 0;
        for (Card card : player.getHand()) {
            ImageView cardImageView;
            if (player == gameLogic.getCurrentPlayer())
                cardImageView = createCardImageView(card);
            else cardImageView = new ImageView(new Image(getClass().getResourceAsStream(
                    "/images/back_of_card.png"
            )));
            cardImageView.setFitWidth(80);
            cardImageView.setFitHeight(100);
            cardImageView.setTranslateX((i++)*20 - 10*player.getHand().size());
            //StackPane.setMargin(cardImageView, new javafx.geometry.Insets(0, 0, 0, (i++) * 30));
            playerStackPane.getChildren().add(cardImageView);
        }
    }

    private void renderCards(ArrayList<Card> cards, StackPane cardsStackPane) {
        if(cards == null) return;
        cardsStackPane.getChildren().clear();
        int i = 0;
        for (Card card : cards) {
            ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(card.getPathName())
            )));
            cardImageView.setFitWidth(80);
            cardImageView.setFitHeight(100);
            StackPane.setMargin(cardImageView, new javafx.geometry.Insets(0, 0, 0, (i++) * 30));
            cardsStackPane.getChildren().add(cardImageView);
        }
    }

    //hiển thị các thành phần biểu tượng, thông báo của game
    private void renderGameState() {
        currentPlayerLabel.setText("Current Player: " + gameLogic.getCurrentPlayer().getName());
        gameStatusLabel.setText("gameStatusLabel: ");
    }
    //tạo imageView
    private ImageView createCardImageView(Card card) {
        ImageView cardImageView = new ImageView(new Image(getClass().getResourceAsStream(card.getPathName())));

        cardImageView.setOnMouseClicked(mouseEvent -> toggleCardSelection(card, cardImageView));
        return cardImageView;
    }
    //xử lí sự kiện chọn bài
    private void toggleCardSelection(Card card, ImageView cardImageView) {
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
            cardImageView.setTranslateY(0);
        } else {
            selectedCards.add(card);
            cardImageView.setTranslateY(-15);
        }

    }
    public ArrayList<Card> getSelectedCards() {
        return new ArrayList<>(selectedCards);
    }
    /*public List<Card> getSelectedCardsFromUI() {
        List<Card> selectedCards = new ArrayList<>();

        for (CardImage cardImage : cardImages) {
            if (cardImage.isSlected()) {
                selectedCards.add(cardImage.getCard());
            }
        }

    }*/
    public void clearSelectedCard() {
        this.selectedCards.clear();
    }

}
