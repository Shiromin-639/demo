package org.example.demo3.model;

import java.util.ArrayList;

public class TienlenGameLogic1 {
    private GameState gameState;

    public TienlenGameLogic1(ArrayList<Player> players) {
        this.gameState = new GameState(players);
    }

    public void skipTurn() {
        gameState.skipTurn();

        if (!gameState.hasActivePlayers()) {
            gameState.resetRound();
        }
    }

    public void checkRoundEnd() {
        if (!gameState.hasActivePlayers()) {
            gameState.resetRound();
        }
    }

    public void dealCards() {
        Deck deck = new Deck();
        int playerIndex = 0;
        while (!deck.isEmpty()) {
            Player currentPlayer = gameState.getPlayers().get(playerIndex);
            currentPlayer.drawCard(deck);
            playerIndex = (playerIndex + 1) % gameState.getPlayers().size();
        }
    }
    public Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }

    public ArrayList<Card> getLastPlayedCards() {
        return gameState.getLastPlayedCards();
    }



}
