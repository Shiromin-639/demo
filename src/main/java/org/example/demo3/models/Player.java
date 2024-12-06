package org.example.demo3.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }


    public ArrayList<Card> getHand() {
        return (ArrayList<Card>) hand;
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public String getName() {
        return name;
    }


}
