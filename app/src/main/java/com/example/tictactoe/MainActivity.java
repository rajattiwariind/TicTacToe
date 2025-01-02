package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView[] board;
    private boolean isXTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = new TextView[9];
        board[0] = findViewById(R.id.tile1);
        board[1] = findViewById(R.id.tile2);
        board[2] = findViewById(R.id.tile3);
        board[3] = findViewById(R.id.tile4);
        board[4] = findViewById(R.id.tile5);
        board[5] = findViewById(R.id.tile6);
        board[6] = findViewById(R.id.tile7);
        board[7] = findViewById(R.id.tile8);
        board[8] = findViewById(R.id.tile9);

        for (TextView tile : board) {
            tile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView clickedTile = (TextView) v;
                    if (clickedTile.getText().toString().isEmpty()) {
                        if (isXTurn) {
                            clickedTile.setText("X");
                        } else {
                            clickedTile.setText("O");
                        }
                        isXTurn = !isXTurn;
                        checkWinner();
                    }
                }
            });
        }

        // Reset button logic
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    private void checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (!board[i * 3].getText().toString().isEmpty() &&
                    board[i * 3].getText().toString().equals(board[i * 3 + 1].getText().toString()) &&
                    board[i * 3 + 1].getText().toString().equals(board[i * 3 + 2].getText().toString())) {
                declareWinner(board[i * 3].getText().toString());
                return;
            }

            if (!board[i].getText().toString().isEmpty() &&
                    board[i].getText().toString().equals(board[i + 3].getText().toString()) &&
                    board[i + 3].getText().toString().equals(board[i + 6].getText().toString())) {
                declareWinner(board[i].getText().toString());
                return;
            }
        }

        // Check diagonals
        if (!board[0].getText().toString().isEmpty() &&
                board[0].getText().toString().equals(board[4].getText().toString()) &&
                board[4].getText().toString().equals(board[8].getText().toString())) {
            declareWinner(board[0].getText().toString());
            return;
        }

        if (!board[2].getText().toString().isEmpty() &&
                board[2].getText().toString().equals(board[4].getText().toString()) &&
                board[4].getText().toString().equals(board[6].getText().toString())) {
            declareWinner(board[2].getText().toString());
            return;
        }
    }

    private void declareWinner(String winner) {
        Toast.makeText(this, winner + " wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard() {
        for (TextView tile : board) {
            tile.setText("");
        }
        isXTurn = true;
    }
}



