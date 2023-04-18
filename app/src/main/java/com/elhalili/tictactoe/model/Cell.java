package com.elhalili.tictactoe.model;

public class Cell {
    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || player.getValue() == null || player.getValue().isEmpty();
    }
}
