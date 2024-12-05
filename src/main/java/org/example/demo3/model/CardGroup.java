package org.example.demo3.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CardGroup {
    public enum CardGroupType {
        SINGLE,     // Đơn
        PAIR,       // Đôi
        TRIPLE,     // Sám
        STRAIGHT,   // Sảnh
        BOMB,       // Tứ quý
        INVALID
    }

    private List<Card> cards;

    public CardGroup(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.cards.sort(Comparator.comparingInt(Card::getRank));
    }

    public CardGroupType getCardGroupType() {
        int size = cards.size();
        if (size == 1) return CardGroupType.SINGLE;
        if (isPair()) return CardGroupType.PAIR;
        if (isTriple()) return CardGroupType.TRIPLE;
        if (isStraight()) return CardGroupType.STRAIGHT;
        if (isBomb()) return CardGroupType.BOMB;
        return CardGroupType.INVALID;
    }

    public boolean isPair() {
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }

    public boolean isTriple() {
        return cards.size() == 3 &&
                cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(0).getRank() == cards.get(2).getRank();
    }

    public boolean isStraight() {
        if (cards.getLast().getRank() == 15) return false;

        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank() + 1 != cards.get(i + 1).getRank()) return false;
        }

        return true;
    }

    public boolean isBomb() {
        return cards.size() == 4 &&
                cards.get(0).getRank() == cards.get(1).getRank() &&
                cards.get(1).getRank() == cards.get(2).getRank() &&
                cards.get(2).getRank() == cards.get(3).getRank();
    }

    public int getHighestRank() {
        return cards.getLast().getRank();
    }
}