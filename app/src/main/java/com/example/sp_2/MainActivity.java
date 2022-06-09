package com.example.sp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener {

    public static boolean leftPressed = false; // left button is pressed
    public static boolean rightPressed = false; // right button is pressed
    public static Display display = null;
    private boolean gameOver = false;
    MediaPlayer mPlayer;

    public static final String APP_PREFERENCES = "gameScore";
    public static final String APP_PREFERENCES_SCORE = "score";

    public static final String SETTING_PREFERENCES = "gameSettings";
    public static final String APP_PREFERENCES_SETTINGS = "settings";

    boolean actuallPosition;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gameView = new GameView(this);
        gameLayout.addView(gameView);
        gameView.setOnTouchListener(this);

        SharedPreferences scorePreferences = getSharedPreferences(SETTING_PREFERENCES, Context.MODE_PRIVATE);
        actuallPosition = scorePreferences.getBoolean(APP_PREFERENCES_SETTINGS, true);

        mPlayer = MediaPlayer.create(this, R.raw.music);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayer.stop();
            }
        });
        startPlay();
    }

    private void stopPlay(){
        if(actuallPosition){
            mPlayer.stop();

            try {
                mPlayer.prepare();
                mPlayer.seekTo(0);

            }
            catch (Throwable t) {

            }
        }
    }

    private void startPlay(){
        if(actuallPosition){
            mPlayer.start();
        }
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
        stopPlay();
        gameView.setRunning(false);
    }

    @Override
    public void onRestart(){
        startPlay();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
    }


}