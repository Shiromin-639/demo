package org.example.demo3.model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    private ArrayList<Player> players;
    private ArrayList<Card> lastPlayedCards;
    private Player lastPlayerToPlay;
    private int currentPlayerIndex;
    private boolean[] skipFlags;
    public static int numOfActivePlayers;
    public boolean newRound;
    public GameState(ArrayList<Player> players) {
        this.players = players;
        this.lastPlayedCards = new ArrayList<>();
        this.lastPlayerToPlay = null;
        this.currentPlayerIndex = 0;
        this.skipFlags = new boolean[players.size()];
        this.newRound = true;
        numOfActivePlayers = players.size();
        resetSkipFlags();
    }


    public void moveToNextPlayer() {
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } while (!skipFlags[currentPlayerIndex]);
    }

    public void skipTurn() {
        skipFlags[currentPlayerIndex] = false;
        numOfActivePlayers--;
        if (isRoundEnded()) {
            startNewRound();
        } else {
            moveToNextPlayer();
        }
    }

    public void playTurn() {
        if (isNewRound()) {
            newRound = false;
        }
        lastPlayerToPlay = getCurrentPlayer();
        moveToNextPlayer();
    }
    public boolean isRoundEnded() {
       return numOfActivePlayers == 1;
    }

    public void startNewRound() {
        numOfActivePlayers = players.size();
        currentPlayerIndex = players.indexOf(lastPlayerToPlay);
        resetSkipFlags();
        lastPlayedCards.clear();
        newRound = true;
    }

    public void resetSkipFlags() {
        Arrays.fill(skipFlags, true);
    }

    public boolean isNewRound() {
        return newRound;
    }

    public boolean isGameEnded() {
        return getCurrentPlayer().getHand().isEmpty();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public ArrayList<Card> getLastPlayedCards() {
        return lastPlayedCards;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setLastPlayedCards(ArrayList<Card> lastPlayedCards) {
        this.lastPlayedCards.clear();
        this.lastPlayedCards.addAll(lastPlayedCards);
    }
}
