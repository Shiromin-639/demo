package org.example.demo3.model;



import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class Card {
    private int rank;   // 2-14 (11=J, 12=Q, 13=K, 14=A)
    private String suit; // "♠", "♣", "♥", "♦"
    private boolean selected = false;
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        if(rank == 15)
            return 2;
        else if(rank == 14)
            return 1;
        else if(rank >= 11 && rank <= 13)
            return 10;
        else
            return rank;
    }

    @Override
    public String toString() {
        String rankString;
        switch (rank) {
            case 11 -> rankString = "jack";
            case 12 -> rankString = "queen";
            case 13 -> rankString = "king";
            case 14 -> rankString = "ace";
            case 15 -> rankString = "2";
            default -> rankString = String.valueOf(rank);
        }
        return rankString + "_of_" + suit;
    }

    public static ArrayList<Card> createDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        for (String suit : suits) {
            for (int rank = 3; rank <= 15; rank++) {
                deck.add(new Card(rank, suit));
            }
        }
        return deck;
    }

    public Image getImage() throws NullPointerException {
        String pathName = "images/" + this.toString() + ".png";

        return new Image(Objects.requireNonNull(Card.class.getResourceAsStream(pathName)));
    }

    public String getPathName() {
        return "images/" + this.toString() + ".png";
    }

    public Image getBackOfCardImage() throws NullPointerException {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("org/example/demo2/back_of_card.png")));
    }

}

