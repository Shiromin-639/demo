package org.example.demo3.models;

import java.util.ArrayList;
import java.util.List;

public class TienlenGame {

    private final GameState gameState;
    private boolean gameOver;
    public TienlenGame(ArrayList<Player> players) {
        this.gameState = new GameState(players);
        gameOver = false;
    }
    // thao tác đánh bài
    public boolean playTurn(ArrayList<Card> selectedCards) {
        boolean isValid = canBeat(selectedCards, gameState.getLastPlayedCards());
        if (isValid) {
            gameState.setLastPlayedCards(selectedCards);
            gameState.setLastPlayerToPlay(getCurrentPlayer());
            getCurrentPlayer().getHand().removeAll(selectedCards);

            if (getCurrentPlayer().getHand().isEmpty()) {
                gameOver = true;
                return true;
            }
            //if (getCurrentPlayer().getHand().isEmpty()) {}
            gameState.moveToNextPlayer();
            gameState.setNewRound(false);
        }
        return isValid;
    }
    // thao tác bỏ lượt
    public void skipTurn() {
        if (gameState.isNewRound()) return;
        gameState.skipCurrentPlayer();
        if (gameState.isRoundEnded()) {
            gameState.startNewRound();
            gameState.setNewRound(true);
        }
    }
    // kiểm tra game đã kết thúc chưa
    public boolean isGameOver() {
        return gameOver;
    }
    // kiểm tra tổ hợp bài đánh hợp lệ
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
        if (selectedType == CardGroup.CardGroupType.FOUR_PAIRS) {
            if (lastPlayedType == CardGroup.CardGroupType.SINGLE || lastPlayedType == CardGroup.CardGroupType.PAIR) {
                if (selectedCards.getFirst().getRank() == 15) return true;
            }
            if (lastPlayedType == CardGroup.CardGroupType.BOMB || lastPlayedType == CardGroup.CardGroupType.THREE_PAIRS)
                return true;
        }
        if (selectedType == CardGroup.CardGroupType.THREE_PAIRS &&
                lastPlayedType == CardGroup.CardGroupType.SINGLE &&
                lastPlayedCards.getFirst().getRank() == 15)
        return true;
        if (selectedType == CardGroup.CardGroupType.BOMB) {
            if (lastPlayedType == CardGroup.CardGroupType.SINGLE || lastPlayedType == CardGroup.CardGroupType.PAIR) {
                if (selectedCards.getFirst().getRank() == 15)
                    return true;
            }
            if (lastPlayedType == CardGroup.CardGroupType.THREE_PAIRS &&
                    selectedCards.getFirst().getRank() > lastPlayedGroup.getHighestRank())
                return true;
        }
        return false;
    }
    // chia bài
    public void dealCards() {
        Deck deck = new Deck();
        int playerIndex = 0;
        for (int i = 0; i < getPlayers().size(); i++) {
            Player player = gameState.getPlayers().get(i);
            for (int j = 0; j < 13; j++) {
                player.drawCard(deck);
            }
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
    public ArrayList<Player> getPlayers() {
        return gameState.getPlayers();
    }





}
