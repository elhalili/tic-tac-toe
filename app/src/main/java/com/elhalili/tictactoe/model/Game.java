package com.elhalili.tictactoe.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class Game {
    private static final String TAG = Game.class.getSimpleName();
    private static int BOARD_SIZE = 3;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Cell[][] cells;
    public MutableLiveData<Player> winner = new MutableLiveData<>();
    public boolean hasGameEnded;

    public Game(Player player1, Player player2) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        hasGameEnded = false;
    }

    public boolean isBoardFull() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                if (cell == null || cell.isEmpty())
                    return false;
        return true;
    }
    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;

            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0]);
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }
    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Cell cell : cells)
            if (cell == null || (cell.player.getValue() == null) || (cell.player.getValue().isEmpty()))
                return false;

        Cell comparisonBase = cells[0];
        for (int i = 1; i < cells.length; i++)
            if (!comparisonBase.player.getValue().equals(cells[i].player.getValue()))
                return false;

        return true;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean play(int position) {
        return false;
    }

    public boolean hasGameEnded() {
        return (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells() || isBoardFull());
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
