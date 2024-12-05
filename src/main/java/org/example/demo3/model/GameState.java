package org.example.demo3.model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    private ArrayList<Player> players;
    private ArrayList<Card> lastPlayedCards;
    private Player lastPlayerToPlay;
    private int currentPlayerIndex;
    private boolean[] skipFlags;
    private boolean newRound = true;
    public boolean isFirstRound = true;

    public GameState(ArrayList<Player> players) {
        this.players = players;
        this.lastPlayedCards = new ArrayList<>();
        this.lastPlayerToPlay = null;
        this.currentPlayerIndex = 0;
        this.skipFlags = new boolean[players.size()];
        resetSkipFlags();
    }


    public void moveToNextPlayer() {
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } while (!skipFlags[currentPlayerIndex]);
    }
    public void skipCurrentPlayer() {
        skipFlags[currentPlayerIndex] = false;
        moveToNextPlayer();
    }
    public void resetSkipFlags() {
        Arrays.fill(skipFlags, true);
    }

    public boolean isRoundEnded() {
        int activePlayers = 0;
        for (boolean flag : skipFlags) {
            if (flag) activePlayers++;
        }
        return activePlayers == 1;
    }

    public void startNewRound() {
        currentPlayerIndex = players.indexOf(lastPlayerToPlay);
        resetSkipFlags();
        clearLastPlayedCards();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public ArrayList<Card> getLastPlayedCards() {
        return lastPlayedCards;
    }
    public void setNewRound(boolean newRound) {
        this.newRound = newRound;
    }

    public boolean isNewRound() {
        return newRound;
    }
    public void setLastPlayerToPlay(Player lastPlayerToPlay) {
        this.lastPlayerToPlay = lastPlayerToPlay;
    }
    public void clearLastPlayedCards() {
        this.lastPlayedCards.clear();
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setLastPlayedCards(ArrayList<Card> lastPlayedCards) {
        this.lastPlayedCards.clear();
        this.lastPlayedCards.addAll(lastPlayedCards);
    }
}
