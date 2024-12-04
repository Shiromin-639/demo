package org.example.demo3.model;

import java.util.ArrayList;

public class BlackjackGameLogic {
    private Player dealer;
    private ArrayList<Player> players;
    private Deck deck;
    private static final int DEALER_THRESHOLD = 17;

    public BlackjackGameLogic(ArrayList<Player> players) {
        this.players = players;
        this.dealer = new Player("Dealer");


    }

    public void dealInitialCards() {
        this.deck = new Deck();
        for (Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }
        dealer.drawCard(deck);
        dealer.drawCard(deck);
    }

    public void playerHit(Player player) {
        player.drawCard(deck);
    }

    public void dealerTurn() {
        while (calculateScore(dealer) < 17) {
            dealer.drawCard(deck);
        }
    }

    public int calculateScore(Player player) {
        int score = 0;
        int aceCount = 0;

        for (Card card: player.getHand()) {
            int cardValue = card.getValue();
            if (cardValue > 10) cardValue = 10;
            score += cardValue;
            if (cardValue == 1) aceCount++;
            // Xử lí quân

        }
        while (aceCount > 0 && score + 10 <= 21) {
            score += 10;
            aceCount--;
        }

        return score;

    }

    public boolean isBust(Player player) {
        return calculateScore(player) > 21;
    }

    public String checkResult() {
        int dealerScore = calculateScore(dealer);
        int playerScore = calculateScore(players.getFirst()); // Chỉ có một người chơi

        if (isBust(players.getFirst())) return "Bạn thua, bị quắc!";
        if (isBust(dealer)) return "Bạn thắng! Dealer bị quắc!";
        if (playerScore > dealerScore) return "Bạn thắng!";
        if (playerScore < dealerScore) return "Bạn thua!";
        return "Hòa!";
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getCurrentPlayer() {
        return players.getFirst(); // Chỉ có một người chơi
    }
}
