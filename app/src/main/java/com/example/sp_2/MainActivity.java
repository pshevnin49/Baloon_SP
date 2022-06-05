package com.example.sp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnTouchListener {

    public static boolean leftPressed = false; // left button is pressed
    public static boolean rightPressed = false; // right button is pressed
    public static Display display = null;
    private boolean gameOver = false;

    public static final String APP_PREFERENCES = "gameScore";
    public static final String APP_PREFERENCES_SCORE = "score";
    private SharedPreferences scorePreferences;

    GameView gameView = null;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scorePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        instance = this;
        display = getWindowManager().getDefaultDisplay();
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        gameView = new GameView(this);
        gameLayout.addView(gameView);
        gameView.setOnTouchListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        leftPressed = false;
        rightPressed = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        gameView.setRunning(false);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        gameView.setRunning(true);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int)event.getX();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(x < display.getWidth()/2 && !rightPressed){
                    leftPressed = true;
                }
                else if(x > display.getWidth()/2 && !leftPressed){
                    rightPressed = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(x < display.getWidth()/2){
                    leftPressed = false;
                    rightPressed = false;
                }
                else if(x > display.getWidth()/2){
                    rightPressed = false;
                    leftPressed = false;
                }
                break;
        }
        return true;
    }

    public static MainActivity getInstance() {
        return instance;
    }


    public void gameOver(int newScore){

        if(!gameOver){
            Intent intent = new Intent();
            System.out.println("Gameover Class");

            int actuallScore = scorePreferences.getInt(APP_PREFERENCES_SCORE, 0);

            if(newScore > actuallScore){
                SharedPreferences.Editor editor = scorePreferences.edit();
                editor.putInt(APP_PREFERENCES_SCORE, newScore);
                editor.apply();
            }

            intent.setClass(this, GameOverActivity.class);
            startActivity(intent);
            gameOver = true;
            finish();
        }

    }
}