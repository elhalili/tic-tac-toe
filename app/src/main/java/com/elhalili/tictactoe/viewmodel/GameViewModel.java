package com.elhalili.tictactoe.viewmodel;

import android.graphics.Color;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elhalili.tictactoe.model.Cell;
import com.elhalili.tictactoe.model.Game;
import com.elhalili.tictactoe.model.Player;

public class GameViewModel extends ViewModel {
    public ObservableArrayMap<String, Player> cells;
    private Game game;
    public MutableLiveData<Boolean> status;
    public MutableLiveData<Integer> firstPlayerColor;
    public MutableLiveData<Integer> secondPlayerColor;

    public void init(String player1Name, String player2Name) {
        Player player1 = new Player(player1Name, "X");
        Player player2 = new Player(player2Name, "O");

        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
        status = new MutableLiveData<>();

        firstPlayerColor = new MutableLiveData<>();
        secondPlayerColor = new MutableLiveData<>();

        firstPlayerColor.setValue(Color.parseColor("#00ff11"));
        secondPlayerColor.setValue(Color.parseColor("#aaaaaa"));
    }

    public void onClickedCellAt(Integer row, Integer column) {
        if (game.getCells()[row][column] == null) {
            game.getCells()[row][column] = new Cell(game.getCurrentPlayer());
            cells.put(row.toString() + column.toString(), game.getCurrentPlayer());
            if (game.hasGameEnded()) {
                status.setValue(true);
            }
            else {
                game.switchPlayer();
                Integer temp = firstPlayerColor.getValue();
                firstPlayerColor.setValue(secondPlayerColor.getValue());
                secondPlayerColor.setValue(temp);
            }
        }
    }
    public Game getGame() {
        return game;
    }
}
