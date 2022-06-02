package com.example.sp_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);
        //setContentView(new GameView(this,null));

//        Button startButton = (Button)findViewById(R.id.button);
//        startButton.setOnClickListener(this);
//
//        Button scoreButton = (Button)findViewById(R.id.button1);
//        startButton.setOnClickListener(this);
//
//        Button exitButton = (Button)findViewById(R.id.button2);
//        exitButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.start: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
            }break;
        }

//    /** Обработка нажатия кнопок */
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.button: {
//
//                System.out.println("Start");
//                Intent intent = new Intent();
//                intent.setClass(this, MainActivity.class);
//                startActivity(intent);
//
//            }break;
//
//            //выход
//            case R.id.button2: {
//                finish();
//            }break;
//
//            default:
//                break;
//        }
    }
}
