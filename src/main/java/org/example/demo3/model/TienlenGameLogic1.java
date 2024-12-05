package org.example.demo3.model;

import java.util.ArrayList;
import java.util.List;

public class TienlenGameLogic1 {

    private final GameState gameState;

    public TienlenGameLogic1(ArrayList<Player> players) {
        this.gameState = new GameState(players);
    }

    public boolean playTurn(ArrayList<Card> selectedCards) {
        /*getCurrentPlayer().getHand().removeAll(selectedCards);
        if (gameState.isGameEnded())
        gameState.setLastPlayedCards(selectedCards);
        gameState.playTurn(); */
        boolean isValid = canBeat(selectedCards, gameState.getLastPlayedCards());
        if (isValid) {
            gameState.setLastPlayedCards(selectedCards);
            getCurrentPlayer().getHand().removeAll(selectedCards);
            gameState.playTurn();
        }

        return isValid;
    }

    public void skipTurn() {
        gameState.skipTurn();
    }
    boolean validatePlay(ArrayList<Card> selectedCards, ArrayList<Card> lastPlayedCards) {
        return true;
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
        CardGroup.CardGroupType selectedType = selectedGroup.getCardGroupType();
        if (selectedType == CardGroup.CardGroupType.INVALID)
            return false;
        if (lastPlayedCards.isEmpty())
            return true;
        CardGroup lastPlayedGroup = new CardGroup(lastPlayedCards);
        CardGroup.CardGroupType lastPlayedType = lastPlayedGroup.getCardGroupType();

        if (selectedType == lastPlayedType) {
            if (selectedType == CardGroup.CardGroupType.STRAIGHT && selectedCards.size() != lastPlayedCards.size())
                return false;
            return (selectedGroup.getHighesCard()).compareTo(lastPlayedGroup.getHighesCard()) > 0;
        }

        return selectedType == CardGroup.CardGroupType.BOMB;
    }


}
