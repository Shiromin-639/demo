package org.example.demo3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Label gameStatusLabel;
    @FXML
    private Button play;
    @FXML
    private Button skip;
    @FXML
    private Button backToMenuButton;

    private TienlenGameLogic1 gameLogic;
    private final ArrayList<Card> selectedCards = new ArrayList<>();
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

    // xử lí gui khi click đánh bài
    @FXML
    private void onPlay() {
        ArrayList<Card> selectedCards = getSelectedCards();
        if (selectedCards.isEmpty()) {
            gameStatusLabel.setText("Bạn phải chọn bài để đánh");
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
            gameStatusLabel.setText("Tổ hợp không hợp lệ, vui lòng chọn lại");
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
        renderHand(gameLogic.getCurrentPlayer(), currentPlayerHandFlowPane);
        renderLastPlayedCards();
        renderGameState();
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
        renderCards(gameLogic.getLastPlayedCards(), lastPlayedCardsFlowPane);
    }
    //hiển thị bài đang được chọn
    private void renderSelectedCards() {
        renderCards(selectedCards, selectedCardsFlowPane);
    }

    //hiển thị bài trên tay của người chơi hiện tại
    private void renderHand(Player currentPlayer, FlowPane currentPlayerHand) {
        if (currentPlayer.getHand().isEmpty())
            return;
        currentPlayerHand.getChildren().clear();
        for (Card card : currentPlayer.getHand()) {
            ImageView cardImageView = createCardImageView(card);
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

    //hiển thị các thành phần biểu tượng, thông báo của game
    private void renderGameState() {
        currentPlayerLabel.setText("Current Player: " + gameLogic.getCurrentPlayer().getName());
        countLabel.setText("count: " + Integer.toString(count));
        gameStatusLabel.setText("gameStatusLabel: ");
    }

    //tạo imageView
    private ImageView createCardImageView(Card card) {
        ImageView cardImageView = new ImageView(new Image(Objects.requireNonNull(
                getClass().getResourceAsStream(card.getPathName())
        )));
        cardImageView.setFitWidth(100);
        cardImageView.setFitHeight(145.2);
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
        renderSelectedCards();
    }
    public ArrayList<Card> getSelectedCards() {
        return new ArrayList<>(selectedCards);
    }

    public void clearSelectedCard() {
        this.selectedCards.clear();
        selectedCardsFlowPane.getChildren().clear();
    }
}
