package org.example.demo3.models;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public static Deck deck;
    private List<Card> cards;

    private Deck() {
        cards = new ArrayList<>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        for (String suit : suits) {
            for (int rank = 3; rank <= 15; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }
    public static synchronized Deck getInstance() {
        if (deck == null) {
            deck = new Deck();
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }


}

