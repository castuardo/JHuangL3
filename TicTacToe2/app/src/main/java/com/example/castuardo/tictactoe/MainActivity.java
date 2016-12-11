package com.example.castuardo.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String DEFAULT_TEXT = "PLAY!";
    private static final String TEXT_P1 = "X";
    private static final String TEXT_P2 = "O";
    private static final String P1_PLAYING = "Player one is playing";
    private static final String P2_PLAYING = "Player two is playing";
    private static final int P1 = 1;
    private static final int P2 = 2;
    private static final int NP = 0;

    private static int CURRENT_PLAYER = 1;

    private ToggleButton btn1 = null;
    private ToggleButton btn2 = null;
    private ToggleButton btn3 = null;
    private ToggleButton btn4 = null;
    private ToggleButton btn5 = null;
    private ToggleButton btn6 = null;
    private ToggleButton btn7 = null;
    private ToggleButton btn8 = null;
    private ToggleButton btn9 = null;

    private Button restartBtn = null;
    private Button closeBtn = null;

    private TextView textPlayer = null;

    private int [][] plays = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void play(ToggleButton btn, int id){
        boolean win = false;
        if(CURRENT_PLAYER == 1){
            btn.setText(TEXT_P1);
            CURRENT_PLAYER = 2;
            textPlayer.setText(P2_PLAYING);
            mapButtonById(id, P1);
            win = checkWin(P1);
        }
        else if(CURRENT_PLAYER == 2){
            btn.setText(TEXT_P2);
            CURRENT_PLAYER = 1;
            textPlayer.setText(P1_PLAYING);
            mapButtonById(id, P2);
            win = checkWin(P2);
        }
        else{
            // error here...
        }
        btn.setEnabled(false);
        if(win){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Win")
                    .setMessage(CURRENT_PLAYER == 1? "Player 2 won!. Restart?" : "Player 1 won!. Restart?")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restart();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }

    private void mapButtonById(int id, int player){
        switch(id){
            case R.id.btn1: plays[0][0] = player; break;
            case R.id.btn2: plays[0][1] = player; break;
            case R.id.btn3: plays[0][2] = player; break;
            case R.id.btn4: plays[1][0] = player; break;
            case R.id.btn5: plays[1][1] = player; break;
            case R.id.btn6: plays[1][2] = player; break;
            case R.id.btn7: plays[2][0] = player; break;
            case R.id.btn8: plays[2][1] = player; break;
            case R.id.btn9: plays[2][2] = player; break;
        }
    }

    private void init(){
        btn1 = (ToggleButton) this.findViewById(R.id.btn1);
        btn2 = (ToggleButton) this.findViewById(R.id.btn2);
        btn3 = (ToggleButton) this.findViewById(R.id.btn3);
        btn4 = (ToggleButton) this.findViewById(R.id.btn4);
        btn5 = (ToggleButton) this.findViewById(R.id.btn5);
        btn6 = (ToggleButton) this.findViewById(R.id.btn6);
        btn7 = (ToggleButton) this.findViewById(R.id.btn7);
        btn8 = (ToggleButton) this.findViewById(R.id.btn8);
        btn9 = (ToggleButton) this.findViewById(R.id.btn9);
        initToggleButton(btn1, R.id.btn1);
        initToggleButton(btn2, R.id.btn2);
        initToggleButton(btn3, R.id.btn3);
        initToggleButton(btn4, R.id.btn4);
        initToggleButton(btn5, R.id.btn5);
        initToggleButton(btn6, R.id.btn6);
        initToggleButton(btn7, R.id.btn7);
        initToggleButton(btn8, R.id.btn8);
        initToggleButton(btn9, R.id.btn9);
        textPlayer = (TextView) this.findViewById(R.id.textPlayer);
        initTextView(textPlayer, P1_PLAYING);
        restartBtn = (Button) this.findViewById(R.id.restartBtn);
        restartBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        // your click actions go here
                        restart();
                    }
                });
        closeBtn = (Button) this.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        // your click actions go here
                        close();
                    }
                });

    }

    private void initTextView(TextView txt, String defaultText){
        txt.setText(defaultText);
    }

    private void initToggleButton(final ToggleButton btn, final int id){
        btn.setText(DEFAULT_TEXT);
        btn.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // your click actions go here
                    play(btn, id);

                }
        });
        btn.setEnabled(true);
        btn.setChecked(false);
    }


    private boolean checkWin(int player){
        if(plays[0][0] == player && plays[0][1] == player && plays[0][2] == player) return true;
        if(plays[1][0] == player && plays[1][1] == player && plays[1][2] == player) return true;
        if(plays[2][0] == player && plays[2][1] == player && plays[2][2] == player) return true;
        if(plays[0][0] == player && plays[1][0] == player && plays[2][0] == player) return true;
        if(plays[0][1] == player && plays[1][1] == player && plays[2][1] == player) return true;
        if(plays[0][2] == player && plays[1][2] == player && plays[2][2] == player) return true;
        if(plays[0][0] == player && plays[1][1] == player && plays[2][2] == player) return true;
        if(plays[0][2] == player && plays[1][1] == player && plays[2][0] == player) return true;
        return false;
    }

    private void close(){
        System.exit(0);
    }

    private void restart(){
        initToggleButton(btn1, R.id.btn1);
        initToggleButton(btn2, R.id.btn2);
        initToggleButton(btn3, R.id.btn3);
        initToggleButton(btn4, R.id.btn4);
        initToggleButton(btn5, R.id.btn5);
        initToggleButton(btn6, R.id.btn6);
        initToggleButton(btn7, R.id.btn7);
        initToggleButton(btn8, R.id.btn8);
        initToggleButton(btn9, R.id.btn9);
        initTextView(textPlayer, P1_PLAYING);
        for(int i = 0; i < plays.length; ++i)
            for(int j = 0; j < plays[i].length; ++j)
                plays[i][j] = NP;
    }

}
