package com.example.sp_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static boolean leftPressed = false; // left button is pressed
    public static boolean rightPressed = false; // right button is pressed
    public static Display display = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        display = getWindowManager().getDefaultDisplay();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

    }

}