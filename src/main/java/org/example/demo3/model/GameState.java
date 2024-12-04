package org.example.demo3.model;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> skippedPlayers;
    private ArrayList<Card> lastPlayedCards;
    private int currentPlayerIndex;
    private boolean gameEnded;


    public GameState(ArrayList<Player> players) {
        this.players = players;
        activePlayers = new ArrayList<>(players);
        skippedPlayers = new ArrayList<>();
        lastPlayedCards = new ArrayList<>();
        currentPlayerIndex = 0;
        gameEnded = false;
    }

    public void nextTurn() {
        if (activePlayers.size() == 1) {
            resetRound();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % activePlayers.size();
        }
    }

    public void skipTurn() {
        skippedPlayers.add(getCurrentPlayer());
        activePlayers.remove(currentPlayerIndex);
        if (currentPlayerIndex >= activePlayers.size()) {
            currentPlayerIndex = 0;
        }
    }

    public void resetRound() {
        activePlayers.addAll(skippedPlayers);
        skippedPlayers.clear();
        currentPlayerIndex = 0;
        lastPlayedCards.clear();
    }
    public Player getCurrentPlayer() {
        return activePlayers.get(currentPlayerIndex);
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void endGame() {
        this.gameEnded = true;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setLastPlayedCards(ArrayList<Card> lastPlayedCards) {
        this.lastPlayedCards.clear();
        this.lastPlayedCards = lastPlayedCards;
    }

    public ArrayList<Card> getLastPlayedCards() {
        return lastPlayedCards;
    }

    public boolean hasActivePlayers() {
        return activePlayers.size() > 1;
    }
}
