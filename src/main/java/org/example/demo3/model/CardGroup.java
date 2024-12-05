package org.example.demo3.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CardGroup {
    public enum CardGroupType {
        SINGLE,         // Đơn
        PAIR,           // Đôi
        TRIPLE,         // BA
        STRAIGHT,       // Sảnh
        BOMB,           // Tứ quý
        THREE_PAIRS,    // Ba đôi thông
        FOUR_PAIRS,     // Bốn đôi thông
        INVALID
    }

    private List<Card> cards;

    public CardGroup(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.cards.sort((c1, c2) -> {
            if (c1.getRank() != c2.getRank()) {
                return Integer.compare(c1.getRank(), c2.getRank());
            }
            return Integer.compare(c1.getSuitRank(), c2.getSuitRank());
        });
    }

    public CardGroupType getCardGroupType() {
        if (cards.size() == 1) return CardGroupType.SINGLE;
        if (isPair()) return CardGroupType.PAIR;
        if (isTriple()) return CardGroupType.TRIPLE;
        if (cards.size() >= 3 && isStraight()) return CardGroupType.STRAIGHT;
        if (isBomb()) return CardGroupType.BOMB;
        if (isThreePairs()) return CardGroupType.THREE_PAIRS;
        if (isFourPairs()) return CardGroupType.FOUR_PAIRS;
        return CardGroupType.INVALID;
    }
    // kiểm tra loại tổ hợp bài
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
    public boolean isThreePairs(){
        if (cards.size() != 6) return false;
        return isConsecutivePairs(3);
    }
    public boolean isFourPairs(){
        if (cards.size() != 8) return false;
        return isConsecutivePairs(4);
    }

    boolean isConsecutivePairs(int numPairs) {
        for (int i = 0; i < numPairs; i++) {
            int index = i * 2;
            if (cards.get(index).getRank() != cards.get(index + 1).getRank()) return false;
            if (i > 0 && cards.get(index).getValue() != cards.get(index-2).getValue() + 1) return false;
        }
        return true;
    }
    public int getHighestRank() {
        return cards.getLast().getRank();
    }

    public Card getHighesCard() {
        return cards.getLast();
    }
}
