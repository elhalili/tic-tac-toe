package com.elhalili.tictactoe.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.elhalili.tictactoe.R;
import com.elhalili.tictactoe.databinding.ActivityGameActivityBinding;
import com.elhalili.tictactoe.model.Game;
import com.elhalili.tictactoe.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity {
    private GameViewModel gameViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding("player 1", "player 2");
    }

    private void initDataBinding(String player1, String player2) {
        ActivityGameActivityBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game_activity);
        gameViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(GameViewModel.class);
        gameViewModel.init(player1, player2);
        activityGameBinding.setGameViewModel(gameViewModel);

        // observers
        gameViewModel.status.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // TODO: pop the winner + reset and suggest to replay
                Toast toast = Toast.makeText(GameActivity.this, "game ended", Toast.LENGTH_LONG);
                toast.show();

                GameActivity.this.finish();
            }
        });
        gameViewModel.firstPlayerColor.observe(this, (c) -> {
            findViewById(R.id.phoneOwner_imageView).setBackgroundColor(c);
        });
        gameViewModel.secondPlayerColor.observe(this, (c) -> {
            findViewById(R.id.opponent_imageView).setBackgroundColor(c);
        });
    }


    // Confirmation of cancel the game
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Cancel");
        builder.setMessage("Do you want really cancel the game ?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameActivity.super.onBackPressed();
                        GameActivity.this.finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}