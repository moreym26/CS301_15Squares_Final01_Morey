package com.example.cs301_15squares_final01_morey;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private NewGame newGame;
    private TextView moveText;
    private Button[] buttons;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = new NewGame(4);
        moveText = findViewById(R.id.movesText);
        buttons = new Button[16];

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);
        buttons[11] = findViewById(R.id.button12);
        buttons[12] = findViewById(R.id.button13);
        buttons[13] = findViewById(R.id.button14);
        buttons[14] = findViewById(R.id.button15);
        buttons[15] = findViewById(R.id.button16);

        createGridLayout();

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    int position = Integer.parseInt(getResources().getResourceEntryName(id).substring(6));
                    int x = position / newGame.getSize();
                    int y = position % newGame.getSize();
                    int emptyX = newGame.getVacantX();
                    int emptyY = newGame.getVacantY();
                    if ((x == emptyX && Math.abs(y - emptyY) == 1) || (y == emptyY && Math.abs(x - emptyX) == 1)) {
                        newGame.swap(x, y); //emptyX, emptyY);
                        updateLayout();
                        if (newGame.isWon()) {
                            Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            });
        }

        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame.shuffle();
                updateLayout();
                //createGrid();
            }
        });

    }
    void createGridLayout() {
        for (int i = 0; i < 15; i++) {
            buttons[i].setText(String.valueOf(i + 1));
        }
        // set the text of button16 to empty string and show it
        buttons[15].setText("");
        buttons[15].setVisibility(View.GONE);

        // shuffle the game
        newGame.shuffle();
        updateLayout();
    }

    void updateLayout() {
        moveText.setText("Moves: " + newGame.getMoves());

        for (int i = 0; i < 15; i++) {
            Button button = buttons[i];
            int x = i / newGame.getSize();
            int y = i % newGame.getSize();
            Box box = newGame.getBox(x, y);
            button.setText(box.returnText());
        }
    }



}