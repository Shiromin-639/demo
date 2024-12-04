package org.example.demo3.model;

import java.util.ArrayList;

public class TienlenGameLogic {

    public enum Play{

    }
    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> skippedPlayers;
    private int currentPlayerIndex;
    private ArrayList<Card> lastPlayedCards;
    private Player currentPlayer;
    private Player lastPlayerInRound;
    private boolean gameEnded;
    private Deck deck;
    public TienlenGameLogic(ArrayList<Player> players) {
        this.players = players;
        activePlayers = new ArrayList<>();
        lastPlayedCards = new ArrayList<>();
        currentPlayerIndex = 0;

    }

    public void dealCards() {
        deck = new Deck();
        for (Player player : players) {
            for (int i = 0; i < 13; i++) {
                player.drawCard(deck);
            }
        }
    }

    public void nextTurn() {
        if (activePlayers.size() == 1) {
            resetRound();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % activePlayers.size();
        }
    }

    public void skipTurn() {
        Player currentPlayer = getCurrentPlayer();

        // Thêm người chơi vào danh sách bỏ lượt
        skippedPlayers.add(currentPlayer);

        // Xóa người chơi khỏi danh sách đang chơi
        activePlayers.remove(currentPlayerIndex);

        // Điều chỉnh chỉ số người chơi hiện tại
        if (currentPlayerIndex >= activePlayers.size()) {
            currentPlayerIndex = 0; // Quay lại đầu vòng nếu vượt chỉ số
        }
    }


    public void resetRound() {
        activePlayers.addAll(skippedPlayers);
        skippedPlayers.clear();
        currentPlayerIndex = 0;
        lastPlayedCards.clear();
    }

    public void setLastPlayedCards(ArrayList<Card> lastPlayedCards) {
        this.lastPlayedCards.clear();
        this.lastPlayedCards = lastPlayedCards;
    }


    public ArrayList<Card> getLastPlayedCards() {
        return lastPlayedCards;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }





}
