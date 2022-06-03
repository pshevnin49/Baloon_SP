package com.example.sp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static boolean leftPressed = false; // left button is pressed
    public static boolean rightPressed = false; // right button is pressed
    public static Display display = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        display = getWindowManager().getDefaultDisplay();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

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
            case MotionEvent.ACTION_UP:
                if(x < display.getWidth()/2){
                    leftPressed = false;
                }
                else if(x > display.getWidth()/2){
                    rightPressed = false;
                }

        }

        return false;
    }
}