package com.example.sp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

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

        System.out.println("Action");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.println("Action1");
                if(x < display.getWidth()/2){
                    leftPressed = true;

                }
                else if(x > display.getWidth()/2){
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