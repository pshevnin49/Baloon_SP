package com.example.sp_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements OnTouchListener {

    public static boolean leftPressed = false; // left button is pressed
    public static boolean rightPressed = false; // right button is pressed
    public static Display display = null;
    GameView gameView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        display = getWindowManager().getDefaultDisplay();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        gameView = new GameView(this);
        gameView.setOnTouchListener(this);
        setContentView(gameView);
    }

    @Override
    public void onPause(){
        super.onPause();
        leftPressed = false;
        rightPressed = false;
        gameView.setRunning(false);
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
                }
                else if(x > display.getWidth()/2){
                    rightPressed = false;
                }
                break;
        }
        return true;
    }
}