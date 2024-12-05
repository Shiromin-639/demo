package org.example.demo3.model;

import java.util.ArrayList;
import java.util.List;

public class TienlenGameLogic1 {
    public enum PlayType {
        SINGLE,
        PAIR,
        TRIPLE,
        STRAIGHT,
        BOMB,
        INVALID
    }

    private final GameState gameState;

    public TienlenGameLogic1(ArrayList<Player> players) {
        this.gameState = new GameState(players);
    }

    public void playTurn(ArrayList<Card> selectedCards) {
        getCurrentPlayer().getHand().removeAll(selectedCards);
        gameState.setLastPlayedCards(selectedCards);
        gameState.playTurn();
    }
    public void skipTurn() {
        gameState.skipTurn();
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

    public GameState getGameState() {
        return gameState;
    }
    public Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }
    public ArrayList<Card> getLastPlayedCards() {
        return gameState.getLastPlayedCards();
    }


    public static boolean canBeat(List<Card> selectedCards, List<Card> lastPlayedCards) {
        CardGroup selectedGroup = new CardGroup(selectedCards);
        CardGroup lastPlayedGroup = new CardGroup(lastPlayedCards);

        CardGroup.CardGroupType selectedType = selectedGroup.getCardGroupType();
        CardGroup.CardGroupType lastPlayedType = lastPlayedGroup.getCardGroupType();

        if (selectedType == CardGroup.CardGroupType.INVALID ||
            lastPlayedType == CardGroup.CardGroupType.INVALID)
            return false;

        if (selectedType == lastPlayedType) {

        }
    }


}
