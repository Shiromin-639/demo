package org.example.demo3.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class CardView extends StackPane {
    private final Card card;
    private boolean isSelected = false;
    private final ImageView cardImageView;

    public CardView(Card card) {
        this.card = card;

        cardImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                card.getPathName()
        ))));
        cardImageView.setFitWidth(50);
        cardImageView.setFitHeight(75);

        this.setOnMouseClicked(mouseEvent -> {
            if (isSelected) {
                deselect();
            } else {
                select();
            }
        });

        getChildren().add(cardImageView);
    }

    public void select() {
        isSelected = true;
        this.setTranslateY(-15); // Di chuyển lá bài lên một chút
    }

    public void deselect() {
        isSelected = false;
        this.setTranslateY(0); // Trả lá bài về vị trí ban đầu
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Card getCard() {
        return card;
    }
}
