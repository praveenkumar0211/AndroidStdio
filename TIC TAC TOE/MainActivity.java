package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //0=x,1=o;
    int activeplayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //2 means uplayed
    int[][] winningpos={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
boolean gameisActive= true;
    public void dropin(View view) {

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameisActive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.xx);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for (int[] win : winningpos) {
                if (gamestate[win[0]] == gamestate[win[1]] &&
                        gamestate[win[1]] == gamestate[win[2]] &&
                        gamestate[win[0]] != 2) {
                    String winner = "O";
                    if (gamestate[win[0]] == 0) {
                        winner = "X";
                    }
                    //someone won
                    gameisActive=false;
                    TextView winnerMessage = (TextView) findViewById(R.id.winner);
                    winnerMessage.setText(winner + "  has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }
                else {
                    boolean gameisover = true;
for (int counterState : gamestate) {
    if(counterState ==2) gameisover = false;
}
if(gameisover){
    gameisActive=false;
    TextView winnerMessage = (TextView) findViewById(R.id.winner);
    winnerMessage.setText("Its DRAW!!!!!!!!!!");
    LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgainLayout);
    layout.setVisibility(View.VISIBLE);
}
                }
            }
        }
    }

    public void PlayAgain(View view) {
        gameisActive=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer = 0;
        for (int i= 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }
        GridLayout gridlayout = (GridLayout) findViewById(R.id.gid);
        for (int i = 0; i < gridlayout.getChildCount(); i++) {
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
