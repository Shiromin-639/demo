package org.example.demo3.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(){}
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }


    public ArrayList<Card> getHand() {
        return (ArrayList<Card>) hand;
    }

    public void drawCard() {
        hand.add(Deck.getInstance().drawCard());
    }

    public String getName() {
        return name;
    }


}
